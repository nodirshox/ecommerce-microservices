package com.ecommerce.order.service.controller;

import com.ecommerce.order.service.dto.OrderCreateDTO;
import com.ecommerce.order.service.model.Response;
import com.ecommerce.order.service.model.TokenRequest;
import com.ecommerce.order.service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/order/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final RestTemplate restTemplate;

    @Value("${config.secret-key}")
    private String SECRET_KEY;

    @Value("${config.auth-service-host}")
    private String AUTH_SERVICE_HOST;

    @Value("${config.auth-service-port}")
    private Integer AUTH_SERVICE_PORT;

    @PostMapping
    public Response placeOrder(@RequestBody OrderCreateDTO orderCreateDTO,
                               @RequestHeader (name="Authorization") String token) {
        String AUTH_SERVICE = "http://"+ AUTH_SERVICE_HOST +":" + AUTH_SERVICE_PORT;
        TokenRequest tokenRequest = new TokenRequest(token);
        Response response = restTemplate.postForObject(AUTH_SERVICE + "/auth/validate-token",tokenRequest,Response.class);
        assert response != null;
        if (response.getSuccess()) {
            return orderService.placeOrder(orderCreateDTO);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Credentials");
        }
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
