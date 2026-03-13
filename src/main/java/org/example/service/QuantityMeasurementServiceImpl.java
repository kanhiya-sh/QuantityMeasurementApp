package org.example.service;

import org.example.*;
import org.example.dto.QuantityDTO;
import org.example.exception.QuantityMeasurementException;
import org.example.model.QuantityMeasurementEntity;
import org.example.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
    private IQuantityMeasurementRepository repo;
    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repo) {
        this.repo = repo;
    }
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        Quantity<IMeasurable> a = new Quantity<>(q1.getValue(),
                        UnitFactory.getUnit(q1.getType(), q1.getUnit()));

        Quantity<IMeasurable> b = new Quantity<>(q2.getValue(),
                        UnitFactory.getUnit(q2.getType(), q2.getUnit()));
        return a.equals(b);
    }
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        try {
            Quantity<IMeasurable> a = new Quantity<>(q1.getValue(),
                            UnitFactory.getUnit(q1.getType(), q1.getUnit()));

            Quantity<IMeasurable> b = new Quantity<>(q2.getValue(),
                            UnitFactory.getUnit(q2.getType(), q2.getUnit()));

            Quantity<IMeasurable> result = a.add(b);
            repo.save(new QuantityMeasurementEntity("ADD", result.toString()));
            return new QuantityDTO(
                    result.getValue(),
                    result.getUnit().getUnitName(),
                    q1.getType());

        }
        catch(Exception e) {
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    public QuantityDTO convert(QuantityDTO q, String targetUnit) {
        try {
            Quantity<IMeasurable> a = new Quantity<>(q.getValue(),
                            UnitFactory.getUnit(q.getType(), q.getUnit()));
            Quantity<IMeasurable> result = a.convertTo(UnitFactory.getUnit(q.getType(), targetUnit));
            repo.save(new QuantityMeasurementEntity("CONVERT", result.toString()));
            return new QuantityDTO(
                    result.getValue(),
                    targetUnit,
                    q.getType());

        }
        catch(Exception e) {
            throw new QuantityMeasurementException(e.getMessage());
        }
    }
}
