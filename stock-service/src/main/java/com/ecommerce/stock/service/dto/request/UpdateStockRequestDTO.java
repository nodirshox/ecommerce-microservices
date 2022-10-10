package com.ecommerce.stock.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStockRequestDTO {
    private Long productId;
    private Integer quantity;
}
