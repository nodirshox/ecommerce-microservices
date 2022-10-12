package com.ecommerce.creditcardservice.service;


import com.ecommerce.creditcardservice.model.CreditCardAcceptDTO;
import com.ecommerce.creditcardservice.model.Response;

public interface CreditCardService {
    Response acceptPayment(CreditCardAcceptDTO amount);
}
