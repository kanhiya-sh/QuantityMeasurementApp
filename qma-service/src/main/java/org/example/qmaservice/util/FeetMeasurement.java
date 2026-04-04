package org.example.qmaservice.util;

public class FeetMeasurement {
    public static class Feet {
        private final double value;
        public Feet(double value) {
            this.value = value;
        }
        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Feet ft = (Feet) obj;
            return Double.compare(this.value, ft.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static void main(String[] args) {
        Feet value1 = new Feet(1.0);
        Feet value2 = new Feet(1.0);
        boolean result = value1.equals(value2);
        System.out.println("Are Values Equal ? - " + result);
    }
}

