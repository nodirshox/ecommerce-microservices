package com.ecommerce.stock.service.service;

import com.ecommerce.stock.service.dto.request.CreateStockRequestDTO;
import com.ecommerce.stock.service.dto.request.UpdateStockRequestDTO;
import com.ecommerce.stock.service.dto.response.CreateStockResponseDTO;
import com.ecommerce.stock.service.dto.response.UpdateStockResponseDTO;

public interface StockService {
    CreateStockResponseDTO create(CreateStockRequestDTO createStockRequestDTO);
    UpdateStockResponseDTO update(UpdateStockRequestDTO updateStockRequestDTO);
}
