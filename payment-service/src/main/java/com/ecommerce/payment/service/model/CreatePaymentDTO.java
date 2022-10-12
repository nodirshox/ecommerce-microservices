package com.ecommerce.payment.service.model;

import com.ecommerce.payment.service.entity.Payment;
import lombok.Data;

@Data
public class CreatePaymentDTO {
    private Long orderId;
    private Payment.PaymentMethod paymentMethod;
}
