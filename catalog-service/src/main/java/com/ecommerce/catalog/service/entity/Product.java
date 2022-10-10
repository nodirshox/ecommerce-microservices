package com.ecommerce.catalog.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String productName;
    private Double price;
    private String vendor;
    private String category;

    public Product(String name,Double price, String vendor, String category) {
        this.productName = name;
        this.price = price;
        this.vendor = vendor;
        this.category = category;
    }
}
