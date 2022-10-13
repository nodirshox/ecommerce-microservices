package com.ecommerce.order.service.service;

import com.ecommerce.order.service.dto.OrderCreateDTO;
import com.ecommerce.order.service.model.Response;

public interface OrderService {
    Response placeOrder(OrderCreateDTO orderCreateDTO);

    Response getUserOrders(Long id);

    Response getOrder(Long id, String prop);
}
