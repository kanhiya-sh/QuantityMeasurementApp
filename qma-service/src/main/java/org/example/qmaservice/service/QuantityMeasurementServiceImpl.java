package org.example.qmaservice.service;

import org.example.qmaservice.util.*;
import org.example.qmaservice.dto.QuantityDTO;
import org.example.qmaservice.exception.QuantityMeasurementException;
import org.example.qmaservice.model.QuantityMeasurementEntity;
import org.example.qmaservice.repository.QuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repo;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String CACHE_KEY = "quantities_all";

    // ================== COMPARE ==================
    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        Quantity<IMeasurable> a = new Quantity<>(q1.getValue(), UnitFactory.getUnit(q1.getType(), q1.getUnit()));
        Quantity<IMeasurable> b = new Quantity<>(q2.getValue(), UnitFactory.getUnit(q2.getType(), q2.getUnit()));

        return a.equals(b);
    }

    // ================== ADD ==================
    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2, String username) {
        try {
            Quantity<IMeasurable> a = new Quantity<>(q1.getValue(), UnitFactory.getUnit(q1.getType(), q1.getUnit()));
            Quantity<IMeasurable> b = new Quantity<>(q2.getValue(), UnitFactory.getUnit(q2.getType(), q2.getUnit()));

            Quantity<IMeasurable> result = a.add(b);

            repo.save(new QuantityMeasurementEntity(null, "ADD", result.toString(), username));

            // CLEAR CACHE
            redisTemplate.delete(CACHE_KEY);
            redisTemplate.delete("history_" + username);

            return new QuantityDTO(
                    result.getValue(),
                    result.getUnit().getUnitName(),
                    q1.getType());

        } catch (Exception e) {
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // ========================= SUBTRACT =========================
    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, String username) {
        try {
            Quantity<IMeasurable> a = new Quantity<>(q1.getValue(),
                    UnitFactory.getUnit(q1.getType(), q1.getUnit()));
            Quantity<IMeasurable> b = new Quantity<>(q2.getValue(),
                    UnitFactory.getUnit(q2.getType(), q2.getUnit()));

            Quantity<IMeasurable> result = a.subtract(b);

            repo.save(new QuantityMeasurementEntity(null, "SUBTRACT", result.toString(), username));

            safeRedisDelete(CACHE_KEY);
            safeRedisDelete("history_" + username);

            return new QuantityDTO(
                    result.getValue(),
                    result.getUnit().getUnitName(),
                    q1.getType());

        } catch (QuantityMeasurementException e) {
            throw e;
        } catch (Exception e) {
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // ========================= DIVIDE =========================
    @Override
    public QuantityDTO divide(QuantityDTO q1, QuantityDTO q2, String username) {
        try {
            Quantity<IMeasurable> a = new Quantity<>(q1.getValue(),
                    UnitFactory.getUnit(q1.getType(), q1.getUnit()));
            Quantity<IMeasurable> b = new Quantity<>(q2.getValue(),
                    UnitFactory.getUnit(q2.getType(), q2.getUnit()));

            // divide() returns a plain double (ratio), not a Quantity with a unit
            double result = a.divide(b);

            repo.save(new QuantityMeasurementEntity(null, "DIVIDE",
                    String.valueOf(result), username));

            safeRedisDelete(CACHE_KEY);
            safeRedisDelete("history_" + username);

            // Result is a dimensionless ratio — return with same type, no unit
            return new QuantityDTO(result, "ratio", q1.getType());

        } catch (QuantityMeasurementException e) {
            throw e;
        } catch (Exception e) {
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

     private void safeRedisDelete(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            System.err.println("Failed to delete Redis key: " + key + " - " + e.getMessage());
        }
    }

    // ================== CONVERT ==================
    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit, String username) {
        try {
            Quantity<IMeasurable> a = new Quantity<>(q.getValue(),
                    UnitFactory.getUnit(q.getType(), q.getUnit()));

            Quantity<IMeasurable> result =
                    a.convertTo(UnitFactory.getUnit(q.getType(), targetUnit));

            repo.save(new QuantityMeasurementEntity(null, "CONVERT", result.toString(), username));

            // CLEAR CACHE
            redisTemplate.delete(CACHE_KEY);
            redisTemplate.delete("history_" + username);

            return new QuantityDTO(
                    result.getValue(),
                    targetUnit,
                    q.getType());

        } catch (Exception e) {
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // ================== GET ALL ==================
    @Override
    public List<QuantityMeasurementEntity> getAll() {
        List<QuantityMeasurementEntity> cachedData =
                (List<QuantityMeasurementEntity>) redisTemplate.opsForValue().get(CACHE_KEY);

        if (cachedData != null) {
            System.out.println("FROM REDIS");
            return cachedData;
        }

        List<QuantityMeasurementEntity> dbData = repo.findAll();

        redisTemplate.opsForValue().set(CACHE_KEY, dbData);

        System.out.println("FROM DB");

        return dbData;
    }

    // ================== USER HISTORY ==================
    @Override
    public List<QuantityMeasurementEntity> getUserHistory(String username) {

        String key = "history_" + username;

        List<QuantityMeasurementEntity> cachedData =
                (List<QuantityMeasurementEntity>) redisTemplate.opsForValue().get(key);

        if (cachedData != null) {
            System.out.println("FROM REDIS (USER HISTORY)");
            return cachedData;
        }

        List<QuantityMeasurementEntity> dbData = repo.findByUsername(username);

        redisTemplate.opsForValue().set(key, dbData);

        System.out.println("FROM DB (USER HISTORY)");

        return dbData;
    }

    // ================== GET BY ID ==================
    @Override
    public QuantityMeasurementEntity getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new QuantityMeasurementException("Data not found"));
    }

    // ================== DELETE ==================
    @Override
    public void delete(Long id) {

        QuantityMeasurementEntity entity = repo.findById(id)
                .orElseThrow(() -> new QuantityMeasurementException("Data not found"));

        repo.deleteById(id);

        // CLEAR CACHE
        redisTemplate.delete(CACHE_KEY);
        redisTemplate.delete("history_" + entity.getUsername());
    }

    // ================== UPDATE ==================
    @Override
    public QuantityMeasurementEntity update(Long id, QuantityMeasurementEntity updated) {

        QuantityMeasurementEntity existing = repo.findById(id)
                .orElseThrow(() -> new QuantityMeasurementException("Data not found"));

        existing.setOperation(updated.getOperation());
        existing.setResult(updated.getResult());

        QuantityMeasurementEntity saved = repo.save(existing);

        // CLEAR CACHE
        redisTemplate.delete(CACHE_KEY);
        redisTemplate.delete("history_" + existing.getUsername());

        return saved;
    }
}