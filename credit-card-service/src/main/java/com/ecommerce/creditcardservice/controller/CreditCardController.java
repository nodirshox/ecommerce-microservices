package com.ecommerce.creditcardservice.controller;

import com.ecommerce.creditcardservice.model.CreditCardAcceptDTO;
import com.ecommerce.creditcardservice.model.Response;
import com.ecommerce.creditcardservice.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/credit-card/payments")
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @PostMapping
    public Response acceptBankPayment(@RequestBody CreditCardAcceptDTO amount) {
        return creditCardService.acceptPayment(amount);
    }
}
