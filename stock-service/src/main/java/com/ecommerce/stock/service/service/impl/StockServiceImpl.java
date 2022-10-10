package com.ecommerce.stock.service.service.impl;

import com.ecommerce.stock.service.dto.request.CreateStockRequestDTO;
import com.ecommerce.stock.service.dto.request.UpdateStockRequestDTO;
import com.ecommerce.stock.service.dto.response.CreateStockResponseDTO;
import com.ecommerce.stock.service.dto.response.UpdateStockResponseDTO;
import com.ecommerce.stock.service.entity.Stock;
import com.ecommerce.stock.service.repository.StockRepository;
import com.ecommerce.stock.service.service.StockService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final ModelMapper mapper;
    @Override
    public CreateStockResponseDTO create(CreateStockRequestDTO createStockRequestDTO) {
        Stock stock = mapper.map(createStockRequestDTO, Stock.class);
        stock.setQuantity(0);
        stock.setCreateAt(LocalDateTime.now());
        CreateStockResponseDTO response = mapper.map(stockRepository.save(stock), CreateStockResponseDTO.class);
        return response;
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

        stock.setQuantity(stock.getQuantity() + updateStockRequestDTO.getQuantity());
        UpdateStockResponseDTO response = mapper.map(stock, UpdateStockResponseDTO.class);

        return response;
    }
}
