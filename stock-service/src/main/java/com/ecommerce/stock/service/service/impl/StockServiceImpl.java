package com.ecommerce.stock.service.service.impl;

import com.ecommerce.stock.service.dto.request.CreateStockRequestDTO;
import com.ecommerce.stock.service.dto.request.UpdateStockRequestDTO;
import com.ecommerce.stock.service.dto.response.CreateStockResponseDTO;
import com.ecommerce.stock.service.dto.response.GetStockResponseDTO;
import com.ecommerce.stock.service.dto.response.UpdateStockResponseDTO;
import com.ecommerce.stock.service.entity.Stock;
import com.ecommerce.stock.service.repository.StockRepository;
import com.ecommerce.stock.service.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
    public CreateStockResponseDTO create(CreateStockRequestDTO createStockRequestDTO) {
        Stock isProductExists = stockRepository.getStockByProductId(createStockRequestDTO.getProductId());

        if (isProductExists != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Stock with productId="
                            + createStockRequestDTO.getProductId()
                            + " is already exists");
        }

        Stock stock = mapper.map(createStockRequestDTO, Stock.class);
        stock.setQuantity(0);
        stock.setCreatedAt(LocalDateTime.now());
        return mapper.map(stockRepository.save(stock), CreateStockResponseDTO.class);
    }

    @Override
    @Transactional
    public UpdateStockResponseDTO update(UpdateStockRequestDTO updateStockRequestDTO) {
        Stock stock = stockRepository.getStockByProductId(updateStockRequestDTO.getProductId());

        if (stock == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Stock with productId="
                            + updateStockRequestDTO.getProductId()
                            + " not found");
        }

        int targetQuantity = stock.getQuantity() + updateStockRequestDTO.getQuantity();

        if (targetQuantity < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Stock with productId="
                            + stock.getProductId()
                            + " not enough to update");
        }

        // TODO: If targetQuantity is smaller than MIN, send notification (50)
        if (targetQuantity <= MINIMUM_STOCK_ALERT) {
            log.info("ALERT(low quantity): productId=" + stock.getProductId() + ", quantity=" + targetQuantity);
        }

        stock.setQuantity(targetQuantity);

        return mapper.map(stock, UpdateStockResponseDTO.class);
    }

    @Override
    public GetStockResponseDTO get(Long productId) {
        Stock stock = stockRepository.getStockByProductId(productId);

        if (stock == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Stock with productId="
                            + productId
                            + " not found");
        }

        return mapper.map(stock, GetStockResponseDTO.class);
    }
}
