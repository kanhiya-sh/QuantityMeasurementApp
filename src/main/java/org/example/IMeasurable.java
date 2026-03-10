package org.example;

public interface IMeasurable {

    double getConversionFactor();
    default double convertToBaseUnit(double value) {
        return value * getConversionFactor();
    }
    default double convertFromBaseUnit(double baseValue) {
        return baseValue / getConversionFactor();
    }
    String getUnitName();
    default boolean supportsArithmetic() {
        return true;
    }
    default void validateOperationSupport(String operation) {
        if(!supportsArithmetic()) {
            throw new UnsupportedOperationException(getUnitName() + " does not support " + operation + " operation");
        }
    }
}

