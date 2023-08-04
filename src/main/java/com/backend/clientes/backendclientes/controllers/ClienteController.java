package com.backend.clientes.backendclientes.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.clientes.backendclientes.models.dto.ClienteDTO;
import com.backend.clientes.backendclientes.models.entities.Cliente;
import com.backend.clientes.backendclientes.models.request.ClienteRequest;
import com.backend.clientes.backendclientes.services.ClienteService;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "${REQUEST_MAPPING_CLIENT}")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> listar() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClientById(@PathVariable Long id) {

        try {
            Optional<ClienteDTO> clienteOptional = clienteService.findById(id);
            if (clienteOptional.isPresent()) {
                return ResponseEntity.ok(clienteOptional.orElseThrow());
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createClient(@Valid @RequestBody Cliente cliente, BindingResult bindingResult) {
        try {

            if (bindingResult.hasErrors()) {
                return validation(bindingResult);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@Valid @RequestBody ClienteRequest cliente, BindingResult bindingResult,
            @PathVariable Long id) {

        try {

            if (bindingResult.hasErrors()) {
                return validation(bindingResult);
            }

            Optional<ClienteDTO> clienteOptional = clienteService.update(id, cliente);
            if (clienteOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(clienteOptional.orElseThrow());
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        try {
            Optional<ClienteDTO> clienteOptional = clienteService.findById(id);

            if (clienteOptional.isPresent()) {
                clienteService.delete(id);
                return ResponseEntity.noContent().build();

            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * @param validation -> Metodo privado que usamos para validar los campos de las
     *                   entidades cada vez
     *                   que usamos @NotNull, NotBlack, NotEmpty
     */
    private ResponseEntity<?> validation(BindingResult bindingResult) {

        Map<String, String> errors = new HashMap<>();

        bindingResult.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);

    }

}
