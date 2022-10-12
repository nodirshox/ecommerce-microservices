package com.ecommerce.payment.service.service;

import com.ecommerce.payment.service.model.CreatePaymentDTO;
import com.ecommerce.payment.service.model.Response;

public interface PaymentService {
    Response placePayment(CreatePaymentDTO paymentDTO);

    Response getAllPayments();

    Response getPayment(Long id);
}
