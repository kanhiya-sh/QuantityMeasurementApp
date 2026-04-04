package org.example.qmaservice.service;

import org.example.qmaservice.dto.QuantityDTO;
import org.example.qmaservice.model.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityMeasurementService {

    boolean compare(QuantityDTO q1, QuantityDTO q2);

    QuantityDTO add(QuantityDTO q1, QuantityDTO q2, String username);

    // NEW
    QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, String username);

    // NEW
    QuantityDTO divide(QuantityDTO q1, QuantityDTO q2, String username);

    QuantityDTO convert(QuantityDTO q, String targetUnit, String username);

    List<QuantityMeasurementEntity> getAll();
    QuantityMeasurementEntity getById(Long id);
    void delete(Long id);
    QuantityMeasurementEntity update(Long id, QuantityMeasurementEntity entity);
    List<QuantityMeasurementEntity> getUserHistory(String username);
}