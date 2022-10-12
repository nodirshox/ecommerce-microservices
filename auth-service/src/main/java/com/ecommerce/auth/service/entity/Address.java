package com.ecommerce.auth.service.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String state;
    private String street;
    private String zipcode;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
}
