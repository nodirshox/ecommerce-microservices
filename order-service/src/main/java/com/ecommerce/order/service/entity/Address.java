package com.ecommerce.order.service.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "order_service_address")
@Data
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String state;
    private String street;
    private String zipcode;
}
