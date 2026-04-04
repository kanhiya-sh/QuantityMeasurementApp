package org.example.qmaservice.dto;

public class QuantityDTO {
    private Double value;
    private String unit;
    private String type;

    public QuantityDTO() {
    }

    public QuantityDTO(Double value, String unit, String type) {
        this.value = value;
        this.unit = unit;
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getType() {
        return type;
    }

    // REQUIRED for Jackson deserialization
    public void setValue(Double value) {
        this.value = value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setType(String type) {
        this.type = type;
    }
}