package com.ecommerce.creditcardservice.controller;

import com.ecommerce.creditcardservice.model.CreditCardAcceptDTO;
import com.ecommerce.creditcardservice.model.Response;
import com.ecommerce.creditcardservice.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/credit-card/payments")
@Slf4j
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @Value("${config.secret-key}")
    private String SECRET_KEY;

    @PostMapping
    public Response acceptBankPayment(@RequestBody CreditCardAcceptDTO amount, @RequestHeader(required = false) HttpHeaders headers) {
        if (headers.get("key") == null || !headers.get("key").contains(SECRET_KEY)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Credentials");
        } else {
            log.info("CC payment receieved with key: {}", headers.get("key").toString());
            return creditCardService.acceptPayment(amount);
        }
    }
}
