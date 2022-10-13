package com.ecommerce.auth.service.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "auth_service_role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String role;
}