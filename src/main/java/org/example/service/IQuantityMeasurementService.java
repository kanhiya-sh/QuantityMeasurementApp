package org.example.service;

import org.example.dto.QuantityDTO;
import org.example.model.QuantityMeasurementEntity;
import java.util.*;

public interface IQuantityMeasurementService {
    boolean compare(QuantityDTO q1, QuantityDTO q2);
    QuantityDTO add(QuantityDTO q1, QuantityDTO q2);
    QuantityDTO convert(QuantityDTO q, String targetUnit);
    List<QuantityMeasurementEntity> getAll();
    QuantityMeasurementEntity getById(Long id);
    void delete(Long id);
    QuantityMeasurementEntity update(Long id, QuantityMeasurementEntity entity);
}
