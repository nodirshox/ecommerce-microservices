package com.ecommerce.creditcardservice.service.impl;

import com.ecommerce.creditcardservice.model.CreditCardAcceptDTO;
import com.ecommerce.creditcardservice.model.Response;
import com.ecommerce.creditcardservice.service.CreditCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreditCardServiceImpl implements CreditCardService {
    @Override
    public Response acceptPayment(CreditCardAcceptDTO amount) {
        log.info("CC Payment received: {}", amount);
        return new Response(amount, true);
    }
}
