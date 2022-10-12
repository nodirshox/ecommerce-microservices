package com.ecommerce.payment.service.service.impl;

import com.ecommerce.payment.service.entity.Payment;
import com.ecommerce.payment.service.model.CreatePaymentDTO;
import com.ecommerce.payment.service.model.Response;
import com.ecommerce.payment.service.repository.PaymentRepository;
import com.ecommerce.payment.service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper mapper;
    @Override
    public Response placePayment(CreatePaymentDTO paymentDTO) {
        Payment payment = mapper.map(paymentDTO,Payment.class);
        paymentRepository.save(payment);
        return new Response(payment,true);
    }

    @Override
    public Response getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return new Response(payments,true);
    }

    @Override
    public Response getPayment(Long id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        return new Response(payment,true);
    }
}
