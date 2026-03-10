package org.example;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;
    private static final double EPSILON = 0.0001;

    public Quantity(double value, U unit) {
        if(unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if(Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        this.value = value;
        this.unit = unit;
    }
    public double getValue() {
        return value;
    }
    public U getUnit() {
        return unit;
    }
    public Quantity<U> convertTo(U targetUnit) {
        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(converted, targetUnit);
    }
    public Quantity<U> add(Quantity<U> quan) {
        return add(quan, this.unit);
    }
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        double sumBase = base1 + base2;
        double converted = targetUnit.convertFromBaseUnit(sumBase);
        return new Quantity<>(converted, targetUnit);
    }
    private void validate(Quantity<U> other) {
        if(other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        if(!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cross category operation not allowed");
        }
    }
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validate(other);
        if(targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseThis = unit.convertToBaseUnit(value);
        double baseOther = other.unit.convertToBaseUnit(other.value);
        double baseResult = baseThis - baseOther;
        double result = targetUnit.convertFromBaseUnit(baseResult);
        return new Quantity<>(round(result), targetUnit);
    }

    public double divide(Quantity<U> other) {
        validate(other);
        double baseThis = unit.convertToBaseUnit(value);
        double baseOther = other.unit.convertToBaseUnit(other.value);
        if(Math.abs(baseOther) < EPSILON) {
            throw new ArithmeticException("Division by zero");
        }
        return baseThis / baseOther;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
        	return true;
        }
        if(!(obj instanceof Quantity<?> other)) {
            return false;
        }
        if(!unit.getClass().equals(other.unit.getClass())) {
            return false;
        }
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
