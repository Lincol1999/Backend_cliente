package com.backend.clientes.backendclientes.services.Impls;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.clientes.backendclientes.models.dto.ClienteDTO;
import com.backend.clientes.backendclientes.models.dto.DtoMapperClient;
import com.backend.clientes.backendclientes.models.entities.Cliente;
import com.backend.clientes.backendclientes.models.request.ClienteRequest;
import com.backend.clientes.backendclientes.respositories.ClienteRepository;
import com.backend.clientes.backendclientes.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();

        return clientes.stream()
                .map(cliente -> DtoMapperClient.builder().setCliente(cliente).build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> findById(Long id) {

        return clienteRepository.findById(id)
                .map(client -> DtoMapperClient
                        .builder()
                        .setCliente(client)
                        .build());
    }

    @Override
    @Transactional
    public ClienteDTO save(Cliente cliente) {

        return DtoMapperClient.builder().setCliente(clienteRepository.save(cliente)).build();
    }

    @Override
    @Transactional
    public Optional<ClienteDTO> update(Long id, ClienteRequest cliente) {

        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        Cliente clientOptional = null;
        if (clienteOptional.isPresent()) {
            Cliente clienteDB = clienteOptional.orElseThrow();

            clienteDB.setName(cliente.getName());
            clienteDB.setEmail(cliente.getEmail());
            clienteDB.setPassword(cliente.getPassword());
            clienteDB.setPhone(cliente.getPhone());

            clientOptional = clienteRepository.save(clienteDB);
        }

        return Optional.ofNullable(DtoMapperClient.builder().setCliente(clientOptional).build());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

}
