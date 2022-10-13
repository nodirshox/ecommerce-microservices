package com.ecommerce.payment.service.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "payment_service_payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Double amount;
    private PaymentMethod paymentMethod;

    public static enum PaymentMethod {
        CC, PAYPAL, BANK
    }
}
