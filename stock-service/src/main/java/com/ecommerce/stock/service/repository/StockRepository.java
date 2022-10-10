package com.ecommerce.stock.service.repository;

import com.ecommerce.stock.service.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock getStockByProductId(Long productId);
}
