package com.ecommerce.payment.service.controller;

import com.ecommerce.payment.service.model.CreatePaymentDTO;
import com.ecommerce.payment.service.model.Response;
import com.ecommerce.payment.service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payment/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public Response placePayment(@RequestBody CreatePaymentDTO paymentDTO) {
        return paymentService.placePayment(paymentDTO);
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
