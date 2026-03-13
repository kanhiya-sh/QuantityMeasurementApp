package org.example.app;

import org.example.controller.QuantityMeasurementController;
import org.example.dto.QuantityDTO;
import org.example.repository.QuantityMeasurementCacheRepository;
import org.example.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        QuantityMeasurementController controller =
                new QuantityMeasurementController
                        (new QuantityMeasurementServiceImpl
                                (QuantityMeasurementCacheRepository.getInstance()));
        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCHES","LENGTH");
        controller.compare(q1,q2);
        controller.add(q1,q2);
        controller.convert(q1,"INCHES");
    }
}
