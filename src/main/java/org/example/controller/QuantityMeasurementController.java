package org.example.controller;

import org.example.dto.QuantityDTO;
import org.example.service.IQuantityMeasurementService;

public class QuantityMeasurementController {
    private IQuantityMeasurementService service;
    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }
    public void compare(QuantityDTO aa, QuantityDTO bb) {
        System.out.println(service.compare(aa,bb));
    }
    public void add(QuantityDTO aa, QuantityDTO bb) {
        QuantityDTO result = service.add(aa,bb);
        System.out.println(result.getValue()+" "+result.getUnit());
    }
    public void convert(QuantityDTO aa, String target) {
        QuantityDTO result = service.convert(aa,target);
        System.out.println(result.getValue() + " " + result.getUnit());
    }
}
