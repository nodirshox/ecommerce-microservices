package com.ecommerce.shipping.service.service.impl;

import com.ecommerce.shipping.service.model.Address;
import com.ecommerce.shipping.service.model.Response;
import com.ecommerce.shipping.service.service.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShipmentServiceImpl implements ShipmentService {
    @Override
    public Response saveShipment(Address address) {
        log.info("Shipping with id={} has received", address.getId());
        return new Response(address,true);
    }
}
