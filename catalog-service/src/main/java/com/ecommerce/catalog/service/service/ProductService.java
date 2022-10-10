package com.ecommerce.catalog.service.service;

import com.ecommerce.catalog.service.dto.request.CreateProductRequestDTO;
import com.ecommerce.catalog.service.model.Response;

public interface ProductService {
    Response getAllCategories();

    Response getProduct(Long id);

    Response saveProduct(CreateProductRequestDTO catalog);
}
