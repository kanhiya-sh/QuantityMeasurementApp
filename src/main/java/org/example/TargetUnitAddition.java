package org.example;

public class TargetUnitAddition {
    private final double value;
    private final LengthUnit unit;
    private static final double EPSILON = 0.0001;
    public TargetUnitAddition(double value, LengthUnit unit) {
        if(unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value or unit");
        }
        this.value = value;
        this.unit = unit;
    }
    public double getValue() {
        return value;
    }
    public LengthUnit getUnit() {
        return unit;
    }

    public TargetUnitAddition add(TargetUnitAddition unitAdd) {
        if(unitAdd == null) {
            throw new IllegalArgumentException("Second operand cannot be a null");
        }
        return add(this, unitAdd, this.unit);
    }

    // ----- UC7 ------
    // Add with explicit target unit
    public static TargetUnitAddition add(TargetUnitAddition q1, TargetUnitAddition q2, LengthUnit targetUnit) {
        if(q1 == null || q2 == null || targetUnit == null) {
            throw new IllegalArgumentException("Operands and Target unit must not be null");
        }
        double base1 = q1.unit.toFeet(q1.value);
        double base2 = q2.unit.toFeet(q2.value);
        double sumInFeet = base1 + base2;
        double resultValue = targetUnit.fromFeet(sumInFeet);
        return new TargetUnitAddition(resultValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof TargetUnitAddition)) {
        	return false;
        }
        TargetUnitAddition unitAdd = (TargetUnitAddition) obj;
        return this.unit == unitAdd.unit && Math.abs(this.value - unitAdd.value) < EPSILON;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}

enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeetFactor;
    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }
    public double toFeet(double value) {
        return value * toFeetFactor;
    }
    public double fromFeet(double feet) {
        return feet / toFeetFactor;
    }
}
