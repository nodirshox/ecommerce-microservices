package com.ecommerce.order.service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;

    @Enumerated
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Product> products;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    public static enum PaymentMethod {
        CC, PAYPAL, BANK
    }
}
