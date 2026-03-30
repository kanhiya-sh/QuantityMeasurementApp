package org.example;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS,
    FAHRENHEIT;

    @Override
    public double getConversionFactor() {
        return 1;
    }

    @Override
    public double convertToBaseUnit(double value) {
        if(this == CELSIUS) {
            return value;
        }
        return (value - 32) * 5 / 9;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        if(this == CELSIUS) {
            return baseValue;
        }
        return (baseValue * 9 / 5) + 32;
    }
    @Override
    public String getUnitName() {
        return name();
    }
    @Override
    public boolean supportsArithmetic() {
        return false;
    }
    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException("Temperature does not support " + operation + " operation");
    }
}
