package com.ecommerce.catalog.service.controller;

import com.ecommerce.catalog.service.dto.request.CreateProductRequestDTO;
import com.ecommerce.catalog.service.model.Response;
import com.ecommerce.catalog.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Response getAllCatalogs() {
        return productService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Response getCatalog(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public Response saveCatalog(@RequestBody CreateProductRequestDTO product) {
        return productService.saveProduct(product);
    }
}
