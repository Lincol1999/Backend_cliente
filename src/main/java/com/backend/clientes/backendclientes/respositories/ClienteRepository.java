package com.backend.clientes.backendclientes.respositories;

import org.springframework.data.repository.CrudRepository;

import com.backend.clientes.backendclientes.models.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    
}
