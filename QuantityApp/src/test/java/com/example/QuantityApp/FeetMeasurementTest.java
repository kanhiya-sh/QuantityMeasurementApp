package com.example.QuantityApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FeetMeasurementTest {

    @Test
    void testEquality_SameValue() {
        FeetMeasurement.Feet f1 = new FeetMeasurement.Feet(1.0);
        FeetMeasurement.Feet f2 = new FeetMeasurement.Feet(1.0);

        assertTrue(f1.equals(f2), "1.0 ft should equal 1.0 ft");
    }

    @Test
    void testEquality_DifferentValue() {
    	FeetMeasurement.Feet f1 = new FeetMeasurement.Feet(1.0);
    	FeetMeasurement.Feet f2 = new FeetMeasurement.Feet(2.0);

        assertFalse(f1.equals(f2), "1.0 ft should not equal 2.0 ft");
    }

    @Test
    void testEquality_NullComparison() {
    	FeetMeasurement.Feet f1 = new FeetMeasurement.Feet(1.0);

        assertFalse(f1.equals(null), "Value should not be equal to null");
    }

    @Test
    void testEquality_SameReference() {
    	FeetMeasurement.Feet f1 = new FeetMeasurement.Feet(1.0);

        assertTrue(f1.equals(f1), "Same reference should return true");
    }

    @Test
    void testEquality_NonNumericInput() {
    	FeetMeasurement.Feet f1 = new FeetMeasurement.Feet(1.0);
        String str = "Not a Feet object";

        assertFalse(f1.equals(str), "Feet should not equal non-Feet object");
    }
}
