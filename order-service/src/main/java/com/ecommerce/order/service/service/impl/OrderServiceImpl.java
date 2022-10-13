package com.ecommerce.order.service.service.impl;

import com.ecommerce.order.service.dto.OrderCreateDTO;
import com.ecommerce.order.service.entity.Order;
import com.ecommerce.order.service.entity.Product;
import com.ecommerce.order.service.model.Response;
import com.ecommerce.order.service.repository.OrderRepository;
import com.ecommerce.order.service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper mapper;
    @Override
    @Transactional
    public Response placeOrder(OrderCreateDTO orderCreateDTO) {
        Order order = new Order();
        order.setUserId(orderCreateDTO.getUserId());
        order.setPaymentMethod(orderCreateDTO.getPaymentMethod());
        order.setProducts(orderCreateDTO.getProducts());
        order.getProducts().forEach((el) -> {
            el.setOrder(order);
        });

        order.setAddress(orderCreateDTO.getAddress());
        orderRepository.save(order);
        return new Response(order,true);
    }

    @Override
    public Response getUserOrders(Long id) {
        List<Order> orders = orderRepository.findAllByUserId(id);
        return new Response(orders,true);
    }

    @Override
    public Response getOrder(Long id, String prop) {
        Order order = orderRepository.findById(id).orElse(null);
        if (prop == null) {
            return new Response(order, true);
        }
        if (prop.equals("address")) {
            return new Response(order.getAddress(), true);
        } else {
            return new Response(order, true);
        }
    }
}
