package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quantity_measurements")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operation;
    private String result;

    public QuantityMeasurementEntity() {}

    public QuantityMeasurementEntity(Long id, String operation, String result) {
        this.id = id;
        this.operation = operation;
        this.result = result;
    }

    public Long getId() {
        return id;
    }
    public String getOperation() {
        return operation;
    }
    public String getResult() {
        return result;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }
    public void setResult(String result) {
        this.result = result;
    }
}