package com.backend.clientes.backendclientes.services;

import java.util.List;
import java.util.Optional;

import com.backend.clientes.backendclientes.models.dto.ClienteDTO;
import com.backend.clientes.backendclientes.models.entities.Cliente;
import com.backend.clientes.backendclientes.models.request.ClienteRequest;

public interface ClienteService {

    List<ClienteDTO> findAll();

    Optional<ClienteDTO> findById(Long id);

    ClienteDTO save(Cliente cliente);

    Optional<ClienteDTO> update(Long id, ClienteRequest cliente);

    void delete(Long id);
}
