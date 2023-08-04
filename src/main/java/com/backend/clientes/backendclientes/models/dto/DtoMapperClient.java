package com.backend.clientes.backendclientes.models.dto;

import com.backend.clientes.backendclientes.models.entities.Cliente;

/**
 * Se encarga de mapear los datos de la clase cliente a la clase clienteDTO
 * 
 * @param builder    -> Método estático que va a recibir la instancia del objeto
 * @param setCliente -> Setea el entity cliente, lo asignamos y devolvemos la
 *                   misma instancia.
 * 
 * @param build      -> Retorna el ClienteDTO con los datos poblados de la clase
 *                   entity
 */

public class DtoMapperClient {

    private Cliente cliente;

    private DtoMapperClient() {
    }

    public static DtoMapperClient builder() {
        return new DtoMapperClient();
    }

    public DtoMapperClient setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public ClienteDTO build() {
        if (cliente == null) {
            new RuntimeException("Debe de pasar el entity Cliente");
        }

        return new ClienteDTO(
                this.cliente.getId(),
                this.cliente.getName(),
                this.cliente.getEmail(),
                this.cliente.getPassword(),
                this.cliente.getPhone());
    }

}
