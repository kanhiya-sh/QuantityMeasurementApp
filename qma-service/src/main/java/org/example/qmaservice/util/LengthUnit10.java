package org.example.qmaservice.util;

public enum LengthUnit10 implements IMeasurable {
    FEET(0.3048),
    INCHES(0.0254),
    YARDS(0.9144),
    CENTIMETERS(0.01),
    METER(1.0),
    KILOMETER(1000.0);

    private final double conversionFactor;

    LengthUnit10(double f) {
        this.conversionFactor = f;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public String getUnitName() {
        return name();
    }
}