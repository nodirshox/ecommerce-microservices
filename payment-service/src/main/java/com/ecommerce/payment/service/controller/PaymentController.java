package com.ecommerce.payment.service.controller;

import com.ecommerce.payment.service.model.CreatePaymentDTO;
import com.ecommerce.payment.service.model.Response;
import com.ecommerce.payment.service.model.TokenRequest;
import com.ecommerce.payment.service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("payment/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    private final RestTemplate restTemplate;

    @Value("${config.auth-service-host}")
    private String AUTH_SERVICE_HOST;

    @Value("${config.auth-service-port}")
    private Integer AUTH_SERVICE_PORT;

    @PostMapping
    public Response placePayment(@RequestBody CreatePaymentDTO paymentDTO,
                                 @RequestHeader (name="Authorization") String token) {
        String AUTH_SERVICE = "http://"+ AUTH_SERVICE_HOST +":" + AUTH_SERVICE_PORT;
        TokenRequest tokenRequest = new TokenRequest(token);
        Response response = restTemplate.postForObject(AUTH_SERVICE + "/auth/validate-token",tokenRequest,Response.class);
        assert response != null;
        if (response.getSuccess()) {
            return paymentService.placePayment(paymentDTO);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Credentials");
        }
    }

    @GetMapping
    public Response getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Response getPayment(@PathVariable Long id) {
        return paymentService.getPayment(id);
    }
}
