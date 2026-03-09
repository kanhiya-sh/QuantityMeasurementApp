package com.example.QuantityApp;

public class YardMeasurement {

    public enum LengthUnit {

        FEET(1.0),                     // base unit
        INCH(1.0 / 12.0),              // 1 inch = 1/12 feet
        YARD(3.0),                     // 1 yard = 3 feet
        CENTIMETER(0.0328084);         // 1 cm = 0.0328084 feet

        private final double conversionFactorToFeet;

        LengthUnit(double conversionFactorToFeet) {
            this.conversionFactorToFeet = conversionFactorToFeet;
        }

        public double toFeet(double value) {
            return value * conversionFactorToFeet;
        }
    }

    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if(unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            if(this == obj) {
            	return true;
            }

            if(obj == null || getClass() != obj.getClass()) {
                return false;
            }
            
            QuantityLength other = (QuantityLength) obj;

            return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < 0.0001;
        }


        @Override
        public int hashCode() {
            return Double.hashCode(toBaseUnit());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.YARD);

        QuantityLength q2 =
                new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println(q1 + " equals " + q2 + " → " + q1.equals(q2));

        QuantityLength q3 =
                new QuantityLength(1.0, LengthUnit.CENTIMETER);

        QuantityLength q4 =
                new QuantityLength(0.393701, LengthUnit.INCH);

        System.out.println(q3 + " equals " + q4 + " → " + q3.equals(q4));
    }
}
