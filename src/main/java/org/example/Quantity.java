package org.example;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if(unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value or unit");
        }
        this.value = value;
        this.unit = unit;
    }

    public Quantity<U> convertTo(U targetUnit) {
        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        convertedValue = Math.round(convertedValue * 100.0) / 100.0;
        return new Quantity<>(convertedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        double resultBase = base1 + base2;
        double result = unit.convertFromBaseUnit(resultBase);
        return new Quantity<>(result, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        double resultBase = base1 + base2;
        double result = targetUnit.convertFromBaseUnit(resultBase);
        return new Quantity<>(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
        	return true;
        }
        if(!(obj instanceof Quantity<?>)) {
        	return false;
        }
        Quantity<?> other = (Quantity<?>) obj;
        if(this.unit.getClass() != other.unit.getClass()) {
        	return false;
        }
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        double epsilon = 0.0001;
        return Math.abs(base1 - base2) < epsilon;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}

