package com.ecommerce.bankservice.controller;

import com.ecommerce.bankservice.model.BankAcceptDTO;
import com.ecommerce.bankservice.model.Response;
import com.ecommerce.bankservice.service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/bank/payments")
@RequiredArgsConstructor
@Slf4j
public class BankController {

    private final BankService bankService;

    @Value("${config.secret-key}")
    private String SECRET_KEY;

    @PostMapping
    public Response acceptBankPayment(@RequestBody BankAcceptDTO amount, @RequestHeader(required = false) HttpHeaders headers) {
        if (headers.get("key") == null || !headers.get("key").contains(SECRET_KEY)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Credentials");
        } else {
            log.info("Bank payment received with key: {}", headers.get("key").toString());
            return bankService.acceptPayment(amount);
        }
    }
}
