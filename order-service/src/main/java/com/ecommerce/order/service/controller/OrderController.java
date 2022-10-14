package com.ecommerce.order.service.controller;

import com.ecommerce.order.service.dto.OrderCreateDTO;
import com.ecommerce.order.service.model.Response;
import com.ecommerce.order.service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/order/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @Value("${config.secret-key}")
    private String SECRET_KEY;

    @PostMapping
    public Response placeOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        return orderService.placeOrder(orderCreateDTO);
    }

    @GetMapping("/user/{id}")
    public Response getUserOrders(@PathVariable Long id) {
        return orderService.getUserOrders(id);
    }

    @GetMapping("/{id}")
    public Response getOrder(@PathVariable Long id,
                             @RequestParam(required = false) String prop,
                             @RequestHeader(required = false) HttpHeaders headers) {
        if (headers.get("key") == null || !headers.get("key").contains(SECRET_KEY)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Credentials");
        } else {
            log.info("Order with ID: {} received. Key: {}",id, headers.get("key").toString());
            return orderService.getOrder(id, prop);
        }
    }
}
