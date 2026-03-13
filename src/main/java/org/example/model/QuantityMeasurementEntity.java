package org.example.model;

import java.io.Serializable;
public class QuantityMeasurementEntity implements Serializable {
    private String operation;
    private String result;

    public QuantityMeasurementEntity(String operation, String result) {
        this.operation = operation;
        this.result = result;
    }
    public String getOperation(){
        return operation;
    }
    public String getResult(){
        return result;
    }
}
