package com.ecommerce.order.service.dto;

import com.ecommerce.order.service.entity.Address;
import com.ecommerce.order.service.entity.Order;
import com.ecommerce.order.service.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class OrderCreateDTO {
    private Long userId;
    private Address address;
    private List<Product> products;
    private Order.PaymentMethod paymentMethod;
}
