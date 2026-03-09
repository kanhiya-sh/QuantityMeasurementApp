package com.example.QuantityApp;

public class UnitConversion {

    enum LengthUnit {
        INCHES(1.0),
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double factorToBase;

        LengthUnit(double factorToBase) {
            this.factorToBase = factorToBase;
        }

        double toBase(double value) {
            return value * factorToBase;
        }

        double fromBase(double baseValue) {
            return baseValue / factorToBase;
        }
    }

    static final class QuantityLength {

        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-6;

        public QuantityLength(double value, LengthUnit unit) {
            validate(value, unit);
            this.value = value;
            this.unit = unit;
        }

        // STATIC CONVERT METHOD (UC5)
        public static double convert(double value, LengthUnit source, LengthUnit target) {

            validate(value, source);
            
            if(target == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            
            if(source == target) {
            	return value;
            }

            double baseValue = source.toBase(value);
            return target.fromBase(baseValue);
        }

        public QuantityLength convertTo(LengthUnit target) {
            double convertedValue = convert(this.value, this.unit, target);
            return new QuantityLength(convertedValue, target);
        }

        private static void validate(double value, LengthUnit unit) {
            if(unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            if(!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite");
            }
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) {
            	return true;
            }
            
            if(!(obj instanceof QuantityLength other)) {
            	return false;
            }

            double thisBase = unit.toBase(value);
            double otherBase = other.unit.toBase(other.value);

            return Math.abs(thisBase - otherBase) < EPSILON;
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

        System.out.println("Feet to Inches: " + QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES));

        System.out.println("Yards to Feet: " + QuantityLength.convert(3.0, LengthUnit.YARDS, LengthUnit.FEET));

        System.out.println("Centimeters to Inches: " + QuantityLength.convert(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES));

        QuantityLength length = new QuantityLength(3.0, LengthUnit.YARDS);
        
        System.out.println("Instance Convert: " + length.convertTo(LengthUnit.FEET));
    }
}

