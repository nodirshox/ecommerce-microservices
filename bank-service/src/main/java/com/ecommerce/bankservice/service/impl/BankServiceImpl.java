package com.ecommerce.bankservice.service.impl;

import com.ecommerce.bankservice.model.BankAcceptDTO;
import com.ecommerce.bankservice.model.Response;
import com.ecommerce.bankservice.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BankServiceImpl implements BankService {
    @Override
    public Response acceptPayment(BankAcceptDTO amount) {
        log.info("Bank Payment received: {}", amount);
        return new Response(amount, true);
    }
}
