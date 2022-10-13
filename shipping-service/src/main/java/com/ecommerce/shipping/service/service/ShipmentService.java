package com.ecommerce.shipping.service.service;

import com.ecommerce.shipping.service.model.Address;
import com.ecommerce.shipping.service.model.Response;

public interface ShipmentService {
    Response saveShipment(Address address);
}
