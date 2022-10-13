package com.ecommerce.shipping.service.service.impl;

import com.ecommerce.shipping.service.model.Address;
import com.ecommerce.shipping.service.model.Response;
import com.ecommerce.shipping.service.service.ShipmentService;
import org.springframework.stereotype.Service;

@Service
public class ShipmentServiceImpl implements ShipmentService {
    @Override
    public Response saveShipment(Address address) {
        return new Response(address,true);
    }
}
