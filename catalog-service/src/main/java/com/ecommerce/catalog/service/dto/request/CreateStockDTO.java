package com.ecommerce.catalog.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class CreateStockDTO {
    private Long productId;
}
