package com.ecommerce.catalog.service.service.impl;

import com.ecommerce.catalog.service.dto.request.CreateProductRequestDTO;
import com.ecommerce.catalog.service.entity.Product;
import com.ecommerce.catalog.service.model.Response;
import com.ecommerce.catalog.service.repository.CatalogRepository;
import com.ecommerce.catalog.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CatalogRepository catalogRepository;

    @Override
    public Response getAllCategories() {
        List<Product> products = catalogRepository.findAll();
        return new Response(products, true);
    }

    @Override
    public Response getProduct(Long id) {
        Product product = catalogRepository.findById(id).orElse(null);
        return new Response(product, true);
    }

    @Override
    public Response saveProduct(CreateProductRequestDTO catalog) {
        Product product = new Product(catalog.getProductName(),catalog.getPrice(),catalog.getVendor(),catalog.getCategory());
        catalogRepository.save(product);
        return new Response(product,true);
    }
}
