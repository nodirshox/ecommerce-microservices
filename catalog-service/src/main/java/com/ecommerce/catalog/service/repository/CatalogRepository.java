package com.ecommerce.catalog.service.repository;

import com.ecommerce.catalog.service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<Product,Long> {
}
