package com.example.QuantityApp;

public class UnitAddition {

    enum LengthUnit {
        INCHES(1.0),
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double factorToBase;

        LengthUnit(double factorToBase) {
            this.factorToBase = factorToBase;
        }

        public double toBase(double value) {
            return value * factorToBase;
        }

        public double fromBase(double baseValue) {
            return baseValue / factorToBase;
        }
    }

    public static final class QuantityLength {

        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-6;

        public QuantityLength(double value, LengthUnit unit) {
            validate(value, unit);
            this.value = value;
            this.unit = unit;
        }

        public static double convert(double value, LengthUnit source, LengthUnit target) {
            validate(value, source);
            
            if(target == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double base = source.toBase(value);
            return target.fromBase(base);
        }

        public QuantityLength convertTo(LengthUnit target) {
            return new QuantityLength(convert(this.value, this.unit, target), target);
        }

        public QuantityLength add(QuantityLength other) {

            if(other == null) {
                throw new IllegalArgumentException("Second operand cannot be null");
            }

            // Converting both to base
            double base1 = this.unit.toBase(this.value);
            double base2 = other.unit.toBase(other.value);

            // Adding in base
            double sumBase = base1 + base2;

            // Converting back to first operand unit
            double result = this.unit.fromBase(sumBase);

            return new QuantityLength(result, this.unit);
        }

        private static void validate(double value, LengthUnit unit) {
            if(unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            if(!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite");
            }
        }

        public double getValue() { return value; }
        public LengthUnit getUnit() { return unit; }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) {
            	return true;
            }
            
            if(!(obj instanceof QuantityLength other)) {
            	return false;
            }

            double base1 = unit.toBase(value);
            double base2 = other.unit.toBase(other.value);

            return Math.abs(base1 - base2) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(unit.toBase(value));
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println(q1.add(q2)); 
    }
}
