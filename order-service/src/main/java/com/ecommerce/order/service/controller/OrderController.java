package com.ecommerce.order.service.controller;

import com.ecommerce.order.service.dto.OrderCreateDTO;
import com.ecommerce.order.service.model.Response;
import com.ecommerce.order.service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Response placeOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        return orderService.placeOrder(orderCreateDTO);
    }

    @GetMapping("user/{id}")
    public Response getUserOrders(@PathVariable Long id) {
        return orderService.getUserOrders(id);
    }

    @GetMapping("/{id}")
    public Response getOrder(@PathVariable Long id, @RequestParam(required = false) String prop) {
        return orderService.getOrder(id, prop);
    }
}
