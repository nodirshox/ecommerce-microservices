package com.ecommerce.stock.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetStockResponseDTO {
    private Long productId;
    private Integer quantity;
}
