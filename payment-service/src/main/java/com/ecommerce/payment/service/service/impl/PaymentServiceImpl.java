package com.ecommerce.payment.service.service.impl;

import com.ecommerce.payment.service.entity.Payment;
import com.ecommerce.payment.service.model.Address;
import com.ecommerce.payment.service.model.AmountRequestDTO;
import com.ecommerce.payment.service.model.CreatePaymentDTO;
import com.ecommerce.payment.service.model.Response;
import com.ecommerce.payment.service.repository.PaymentRepository;
import com.ecommerce.payment.service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Value("${config.bank-service-host}")
    private String BANK_SERVICE_HOST;

    @Value("${config.bank-service-port}")
    private Integer BANK_SERVICE_PORT;

    @Value("${config.credit-card-service-host}")
    private String CREDIT_CARD_SERVICE_HOST;

    @Value("${config.credit-card-service-port}")
    private Integer CREDIT_CARD_SERVICE_PORT;

    @Value("${config.shipment-service-host}")
    private String SHIPMENT_SERVICE_HOST;

    @Value("${config.shipment-service-port}")
    private Integer SHIPMENT_SERVICE_PORT;

    @Value("${config.order-service-host}")
    private String ORDER_SERVICE_HOST;

    @Value("${config.order-service-port}")
    private Integer ORDER_SERVICE_PORT;

    private final PaymentRepository paymentRepository;
    private final ModelMapper mapper;
    private final RestTemplate restTemplate;
    @Override
    @Transactional
    public Response placePayment(CreatePaymentDTO paymentDTO) {

        String BANK_SERVICE = "http://"+ BANK_SERVICE_HOST +":" + BANK_SERVICE_PORT;
        String CREDIT_CARD_SERVICE = "http://"+ CREDIT_CARD_SERVICE_HOST +":" + CREDIT_CARD_SERVICE_PORT;
        String SHIPMENT_SERVICE = "http://"+ SHIPMENT_SERVICE_HOST +":" + SHIPMENT_SERVICE_PORT;
        String ORDER_SERVICE = "http://"+ ORDER_SERVICE_HOST +":" + ORDER_SERVICE_PORT;

        Payment payment = mapper.map(paymentDTO,Payment.class);
        Response response = new Response();
        AmountRequestDTO amountRequestDTO = new AmountRequestDTO(payment.getAmount());

        if (payment.getPaymentMethod() == Payment.PaymentMethod.BANK) {
            response = restTemplate.postForObject(BANK_SERVICE + "/api/bank/payments",amountRequestDTO,Response.class);
        } else if (payment.getPaymentMethod() == Payment.PaymentMethod.CC) {
            response = restTemplate.postForObject(CREDIT_CARD_SERVICE + "/api/credit-card/payments",amountRequestDTO,Response.class);
        }

        Response addressResponse = restTemplate.getForObject(ORDER_SERVICE + "/api/orders/1?prop=address",Response.class);
        assert addressResponse != null;
        Address address = new Address();
        mapper.map(addressResponse.getData(),address);


        Response shipmentResponse = restTemplate.postForObject(SHIPMENT_SERVICE + "/api/shipment", address,Response.class);


        assert response != null;
        if (response.getSuccess()) {
            assert shipmentResponse != null;
            if (shipmentResponse.getSuccess()) {
                paymentRepository.save(payment);
                return new Response(payment, true);
            }
        }

        return new Response(null,false);
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
