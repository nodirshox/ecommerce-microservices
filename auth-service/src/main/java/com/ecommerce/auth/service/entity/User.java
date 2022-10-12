package com.ecommerce.auth.service.entity;

import lombok.Data;
import lombok.Value;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String password;
    private String firstname;
    private String lastname;

    private PaymentMethod paymentMethod = PaymentMethod.CC;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<Role> roles;

    public static enum PaymentMethod {
        CC,PAYPAL,BANK
    }
}