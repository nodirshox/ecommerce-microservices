package com.ecommerce.stock.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStockResponseDTO {
    private Long productId;
    private Long quantity;
}
