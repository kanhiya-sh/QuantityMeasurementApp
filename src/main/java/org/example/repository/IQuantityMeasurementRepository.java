package org.example.repository;

import org.example.model.QuantityMeasurementEntity;
import java.util.*;

public interface IQuantityMeasurementRepository {
    void save(QuantityMeasurementEntity entity);
    List<QuantityMeasurementEntity> findAll();
}
