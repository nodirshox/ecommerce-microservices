package com.ecommerce.shipping.service.controller;

import com.ecommerce.shipping.service.model.Address;
import com.ecommerce.shipping.service.model.Response;
import com.ecommerce.shipping.service.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/shipment")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping
    public Response saveShipment(@RequestBody Address address) {
        return shipmentService.saveShipment(address);
    }
}
