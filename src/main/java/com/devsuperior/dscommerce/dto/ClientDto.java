package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.User;

public class ClientDto {

    private Long id;
    private String name;


    public ClientDto(User entity) {
        id = entity.getId();
        name = entity.getName();
    }


    public ClientDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
