package com.ecommerce.bankservice.service;


import com.ecommerce.bankservice.model.BankAcceptDTO;
import com.ecommerce.bankservice.model.Response;

public interface BankService {
    Response acceptPayment(BankAcceptDTO amount);
}
