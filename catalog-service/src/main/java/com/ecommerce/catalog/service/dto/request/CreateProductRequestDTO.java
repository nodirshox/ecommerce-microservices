package com.ecommerce.catalog.service.dto.request;

import lombok.Data;

@Data
public class CreateProductRequestDTO {
    private String productName;
    private Double price;
    private String vendor;
    private String category;
}
