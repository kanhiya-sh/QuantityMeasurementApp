package org.example;

public enum WeightUnit10 implements IMeasurable {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.45359237);

    private final double conversionFactor;

    WeightUnit10(double conversionFactor) {
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
