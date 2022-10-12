package com.ecommerce.order.service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productId;
    private Integer quantity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Order order;
}
