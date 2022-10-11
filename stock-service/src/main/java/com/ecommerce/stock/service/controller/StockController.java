package com.ecommerce.stock.service.controller;

import com.ecommerce.stock.service.dto.request.CreateStockRequestDTO;
import com.ecommerce.stock.service.dto.request.UpdateStockRequestDTO;
import com.ecommerce.stock.service.dto.response.CreateStockResponseDTO;
import com.ecommerce.stock.service.dto.response.GetStockResponseDTO;
import com.ecommerce.stock.service.dto.response.UpdateStockResponseDTO;
import com.ecommerce.stock.service.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @PostMapping
    public CreateStockResponseDTO create(@RequestBody CreateStockRequestDTO createStockRequestDTO) {
        return stockService.create(createStockRequestDTO);
    }

    @PutMapping
    public UpdateStockResponseDTO update(@RequestBody UpdateStockRequestDTO updateStockRequestDTO) {
        return stockService.update(updateStockRequestDTO);
    }

    @GetMapping("/products/{productId}")
    public GetStockResponseDTO get(@PathVariable Long productId) {
        return stockService.get(productId);
    }
}
