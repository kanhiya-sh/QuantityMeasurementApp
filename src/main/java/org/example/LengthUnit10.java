package org.example;

public enum LengthUnit10 implements IMeasurable {
    FEET(0.3048),
    INCHES(0.0254),
    YARDS(0.9144),
    CENTIMETERS(0.01);

    private final double conversionFactor;
    LengthUnit10(double conversionFactor) {
        this.conversionFactor = conversionFactor;
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

