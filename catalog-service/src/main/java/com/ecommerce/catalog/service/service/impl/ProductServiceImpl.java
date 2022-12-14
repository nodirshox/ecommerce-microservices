package com.ecommerce.catalog.service.service.impl;

import com.ecommerce.catalog.service.dto.request.CreateProductRequestDTO;
import com.ecommerce.catalog.service.dto.request.CreateStockDTO;
import com.ecommerce.catalog.service.entity.Product;
import com.ecommerce.catalog.service.model.Response;
import com.ecommerce.catalog.service.repository.CatalogRepository;
import com.ecommerce.catalog.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Value("${config.stock-service-host}")
    private String STOCK_SERVICE_HOST;

    @Value("${config.stock-service-port}")
    private Integer STOCK_SERVICE_PORT;

    @Value("${config.secret-key}")
    private String SECRET_KEY;

    private final CatalogRepository catalogRepository;
    private final RestTemplate restTemplate;

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
        String STOCK_SERVICE = "http://"+ STOCK_SERVICE_HOST +":" + STOCK_SERVICE_PORT;
        Product product = new Product(catalog.getProductName(),catalog.getPrice(),catalog.getVendor(),catalog.getCategory());
        catalogRepository.save(product);
        CreateStockDTO stockDTO = new CreateStockDTO(product.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("key",SECRET_KEY);
        HttpEntity<CreateStockDTO> httpEntity = new HttpEntity<>(stockDTO,headers);
        Response response = restTemplate.postForObject(STOCK_SERVICE + "/stock/stocks", httpEntity, Response.class);
        assert response != null;
        if (response.getSuccess()) {
            return new Response(product,true);
        }
        return new Response(false,"Error while saving product",null);
    }
}
