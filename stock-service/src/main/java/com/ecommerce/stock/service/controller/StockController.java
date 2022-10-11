package com.ecommerce.stock.service.controller;

import com.ecommerce.stock.service.dto.request.CreateStockRequestDTO;
import com.ecommerce.stock.service.dto.request.UpdateStockRequestDTO;
import com.ecommerce.stock.service.dto.response.Response;
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
    public Response create(@RequestBody CreateStockRequestDTO createStockRequestDTO) {
        return stockService.create(createStockRequestDTO);
    }

    @PutMapping
    public Response update(@RequestBody UpdateStockRequestDTO updateStockRequestDTO) {
        return stockService.update(updateStockRequestDTO);
    }

    @GetMapping("/products/{productId}")
    public Response get(@PathVariable Long productId) {
        return stockService.get(productId);
    }
}
