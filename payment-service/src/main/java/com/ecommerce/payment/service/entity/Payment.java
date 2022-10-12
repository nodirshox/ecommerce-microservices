package com.ecommerce.payment.service.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private PaymentMethod paymentMethod;

    public static enum PaymentMethod {
        CC, PAYPAL, BANK
    }
}
