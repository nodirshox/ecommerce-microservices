package com.ecommerce.payment.service.service.impl;

import com.ecommerce.payment.service.entity.Payment;
import com.ecommerce.payment.service.model.Address;
import com.ecommerce.payment.service.model.AmountRequestDTO;
import com.ecommerce.payment.service.model.CreatePaymentDTO;
import com.ecommerce.payment.service.model.Response;
import com.ecommerce.payment.service.repository.PaymentRepository;
import com.ecommerce.payment.service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Value("${config.bank-service-key}")
    private String BANK_SERVICE_SECRET_KEY;

    @Value("${config.credit-card-service-key}")
    private String CARD_SERVICE_SECRET_KEY;

    @Value("${config.shipment-service-key}")
    private String SHIPPING_SERVICE_SECRET_KEY;

    @Value("${config.order-service-key}")
    private String ORDER_SERVICE_SECRET_KEY;

    static enum SERVICES {
        ORDER_SERVICE, BANK_SERVICE, CREDIT_CARD_SERVICE, SHIPPING_SERVICE
    }

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

        // Checking the order
        log.info("Sending order service method");
        HttpEntity<Void> orderEntity = new HttpEntity<>(getHeader(SERVICES.ORDER_SERVICE));
        Response addressResponse = restTemplate.exchange(
                ORDER_SERVICE + "/order/orders/"+ paymentDTO.getOrderId() +"?prop=address",
                HttpMethod.GET,
                orderEntity,
                Response.class
        ).getBody();
        log.info("Received response from order service method", addressResponse);

        if (!addressResponse.getSuccess()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order with id=" + paymentDTO.getOrderId() + " not found");
        }

        assert addressResponse != null;
        Address address = new Address();
        mapper.map(addressResponse.getData(),address);

        log.info("Sending payment method");
        if (payment.getPaymentMethod() == Payment.PaymentMethod.BANK) {
            log.info("Sending payment method to bank service");
            HttpEntity<AmountRequestDTO> httpEntity = new HttpEntity<>(amountRequestDTO,getHeader(SERVICES.BANK_SERVICE));
            response = restTemplate.postForObject(BANK_SERVICE + "/bank/payments",httpEntity,Response.class);
            log.info("Received response from bank service", response);

        } else if (payment.getPaymentMethod() == Payment.PaymentMethod.CC) {
            log.info("Sending payment method to credit card service");
            HttpEntity<AmountRequestDTO> httpEntity = new HttpEntity<>(amountRequestDTO,getHeader(SERVICES.CREDIT_CARD_SERVICE));
            response = restTemplate.postForObject(CREDIT_CARD_SERVICE + "/card/credit-card/payments",httpEntity,Response.class);
            log.info("Received response from credit card service", response);
        }

        log.info("Sending shipping service method");
        HttpEntity<Address> addressHttpEntity = new HttpEntity<>(address,getHeader(SERVICES.SHIPPING_SERVICE));
        Response shipmentResponse = restTemplate.postForObject(SHIPMENT_SERVICE + "/shipping/shipment", addressHttpEntity,Response.class);
        log.info("Received response from shipping service method");

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
    private HttpHeaders getHeader(SERVICES type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        if (type == SERVICES.SHIPPING_SERVICE) {
            headers.set("key", SHIPPING_SERVICE_SECRET_KEY);
        } else if (type == SERVICES.ORDER_SERVICE) {
            headers.set("key", ORDER_SERVICE_SECRET_KEY);
        } else if (type == SERVICES.BANK_SERVICE) {
            headers.set("key", BANK_SERVICE_SECRET_KEY);
        } else if (type == SERVICES.CREDIT_CARD_SERVICE) {
            headers.set("key", CARD_SERVICE_SECRET_KEY);
        }

        return headers;
    }
}
