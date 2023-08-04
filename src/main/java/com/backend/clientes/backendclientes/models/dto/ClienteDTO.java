package com.backend.clientes.backendclientes.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String phone;

    // public ClienteDTO() {
    // }

    // public ClienteDTO(Long id, String name, String email, String password, Long
    // phone) {
    // this.id = id;
    // this.name = name;
    // this.email = email;
    // this.password = password;
    // this.phone = phone;
    // }

    // public Long getId() {
    // return id;
    // }

    // public void setId(Long id) {
    // this.id = id;
    // }

    // public String getName() {
    // return name;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

    // public String getEmail() {
    // return email;
    // }

    // public void setEmail(String email) {
    // this.email = email;
    // }

    // public String getPassword() {
    // return password;
    // }

    // public void setPassword(String password) {
    // this.password = password;
    // }

    // public Long getPhone() {
    // return phone;
    // }

    // public void setPhone(Long phone) {
    // this.phone = phone;
    // }

}
