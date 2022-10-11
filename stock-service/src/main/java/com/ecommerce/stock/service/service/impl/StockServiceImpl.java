package com.ecommerce.stock.service.service.impl;

import com.ecommerce.stock.service.dto.request.CreateStockRequestDTO;
import com.ecommerce.stock.service.dto.request.UpdateStockRequestDTO;
import com.ecommerce.stock.service.dto.response.CreateStockResponseDTO;
import com.ecommerce.stock.service.dto.response.GetStockResponseDTO;
import com.ecommerce.stock.service.dto.response.Response;
import com.ecommerce.stock.service.dto.response.UpdateStockResponseDTO;
import com.ecommerce.stock.service.entity.Stock;
import com.ecommerce.stock.service.repository.StockRepository;
import com.ecommerce.stock.service.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    @Value("${config.min_stock_alert}")
    private Integer MINIMUM_STOCK_ALERT;
    private final ModelMapper mapper;
    @Override
    public Response create(CreateStockRequestDTO createStockRequestDTO) {
        Stock isProductExists = stockRepository.getStockByProductId(createStockRequestDTO.getProductId());

        if (isProductExists != null) {
            log.info("Stock with productId=" + createStockRequestDTO.getProductId() + " is already exists");
            return new Response(null, false);
        }

        Stock stock = mapper.map(createStockRequestDTO, Stock.class);
        stock.setQuantity(0);
        stock.setCreatedAt(LocalDateTime.now());
        return new Response(mapper.map(stockRepository.save(stock), CreateStockResponseDTO.class), true);
    }

    @Override
    @Transactional
    public Response update(UpdateStockRequestDTO updateStockRequestDTO) {
        Stock stock = stockRepository.getStockByProductId(updateStockRequestDTO.getProductId());

        if (stock == null) {
            log.info("Stock with productId=" + updateStockRequestDTO.getProductId() + " not found");
            return new Response(null, false);
        }

        int targetQuantity = stock.getQuantity() + updateStockRequestDTO.getQuantity();

        if (targetQuantity < 0) {
            log.info("Stock with productId=" + stock.getProductId() + " not enough to update");
            return new Response(null, false);
        }

        // TODO: If targetQuantity is smaller than MIN, send notification (50)
        if (targetQuantity <= MINIMUM_STOCK_ALERT) {
            log.info("ALERT(low quantity): productId=" + stock.getProductId() + ", quantity=" + targetQuantity);
        }

        stock.setQuantity(targetQuantity);

        return new Response(mapper.map(stock, UpdateStockResponseDTO.class), true);
    }

    @Override
    public Response get(Long productId) {
        Stock stock = stockRepository.getStockByProductId(productId);

        if (stock == null) {
            log.info("Stock with productId=" + productId + " not found");
            return new Response(null, false);
        }

        return new Response(mapper.map(stock, GetStockResponseDTO.class), true);
    }
}
