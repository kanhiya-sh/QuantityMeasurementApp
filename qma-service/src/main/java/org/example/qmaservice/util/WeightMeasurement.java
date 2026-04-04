package org.example.qmaservice.util;

enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.45359237);

    private final double toKilogramFactor;

    WeightUnit(double toKilogramFactor) {
        this.toKilogramFactor = toKilogramFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * toKilogramFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toKilogramFactor;
    }

    public double getConversionFactor() {
        return toKilogramFactor;
    }
}

public class WeightMeasurement {
    private final double value;
    private final WeightUnit unit;
    private static final double EPSILON = 1e-6;
    public WeightMeasurement(double value, WeightUnit unit) {
        if(unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value or unit");
        }
        this.value = value;
        this.unit = unit;
    }
    public double getValue() {
        return value;
    }
    public WeightUnit getUnit() {
        return unit;
    }
    public WeightMeasurement convertTo(WeightUnit targetUnit) {
        if(targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be a null");
        }
        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);
        return new WeightMeasurement(converted, targetUnit);
    }

    public WeightMeasurement add(WeightMeasurement wMeasure) {
        if(wMeasure == null) {
            throw new IllegalArgumentException("Other weight cannot be a null");
        }
        return add(this, wMeasure, this.unit);
    }

    public static WeightMeasurement add(WeightMeasurement w1, WeightMeasurement w2, WeightUnit targetUnit) {
        if(w1 == null || w2 == null || targetUnit == null) {
            throw new IllegalArgumentException("Operands and Target unit must not be null");
        }
        double base1 = w1.unit.convertToBaseUnit(w1.value);
        double base2 = w2.unit.convertToBaseUnit(w2.value);
        double sumBase = base1 + base2;
        double result = targetUnit.convertFromBaseUnit(sumBase);
        return new WeightMeasurement(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WeightMeasurement wMeasure = (WeightMeasurement) obj;
        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = wMeasure.unit.convertToBaseUnit(wMeasure.value);
        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Double.hashCode(base);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}