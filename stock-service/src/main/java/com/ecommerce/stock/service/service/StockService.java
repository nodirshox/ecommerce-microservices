package com.ecommerce.stock.service.service;

import com.ecommerce.stock.service.dto.request.CreateStockRequestDTO;
import com.ecommerce.stock.service.dto.request.UpdateStockRequestDTO;
import com.ecommerce.stock.service.dto.response.Response;

public interface StockService {
    Response create(CreateStockRequestDTO createStockRequestDTO);
    Response update(UpdateStockRequestDTO updateStockRequestDTO);
    Response get(Long productId);
}
