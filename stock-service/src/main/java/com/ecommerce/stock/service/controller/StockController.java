package com.ecommerce.stock.service.controller;

import com.ecommerce.stock.service.dto.request.CreateStockRequestDTO;
import com.ecommerce.stock.service.dto.request.UpdateStockRequestDTO;
import com.ecommerce.stock.service.dto.response.Response;
import com.ecommerce.stock.service.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @Value("${config.secret-key}")
    private String SECRET_KEY;

    @PostMapping
    public Response create(@RequestBody CreateStockRequestDTO createStockRequestDTO, @RequestHeader(required = false) HttpHeaders headers) {
        if (headers.get("key") == null || !headers.get("key").contains(SECRET_KEY)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Credentials");
        } else {
            return stockService.create(createStockRequestDTO);
        }
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
