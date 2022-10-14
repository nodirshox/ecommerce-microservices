package com.ecommerce.shipping.service.controller;

import com.ecommerce.shipping.service.model.Address;
import com.ecommerce.shipping.service.model.Response;
import com.ecommerce.shipping.service.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/shipping/shipment")
@RequiredArgsConstructor
@Slf4j
public class ShipmentController {

    private final ShipmentService shipmentService;

    @Value("${config.secret-key}")
    private String SECRET_KEY;

    @PostMapping
    public Response saveShipment(@RequestBody Address address,@RequestHeader(required = false) HttpHeaders headers) {
        if (headers.get("key") == null || !headers.get("key").contains(SECRET_KEY)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Credentials");
        } else{
            log.info("Shipment received with key: {}", headers.get("key").toString());
            return shipmentService.saveShipment(address);
        }
    }
}
