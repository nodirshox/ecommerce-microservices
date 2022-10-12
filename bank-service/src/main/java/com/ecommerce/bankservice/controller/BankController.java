package com.ecommerce.bankservice.controller;

import com.ecommerce.bankservice.model.BankAcceptDTO;
import com.ecommerce.bankservice.model.Response;
import com.ecommerce.bankservice.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bank/payments")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    @PostMapping
    public Response acceptBankPayment(@RequestBody BankAcceptDTO amount) {
        return bankService.acceptPayment(amount);
    }
}
