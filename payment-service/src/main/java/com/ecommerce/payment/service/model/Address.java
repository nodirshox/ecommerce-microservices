package com.ecommerce.payment.service.model;

import lombok.Data;

@Data
public class Address {
    private Long id;
    private Long orderId;
    private String state;
    private String street;
    private String zipcode;
}
