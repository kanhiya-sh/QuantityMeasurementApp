package com.example.QuantityApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GenericQuantityMeasurementTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {
        var q1 = new GenericQuantityMeasurement.QuantityLength(1.0, GenericQuantityMeasurement.LengthUnit.FEET);
        var q2 = new GenericQuantityMeasurement.QuantityLength(1.0, GenericQuantityMeasurement.LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        var q1 = new GenericQuantityMeasurement.QuantityLength(1.0, GenericQuantityMeasurement.LengthUnit.INCH);
        var q2 = new GenericQuantityMeasurement.QuantityLength(1.0, GenericQuantityMeasurement.LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        var q1 = new GenericQuantityMeasurement.QuantityLength(1.0, GenericQuantityMeasurement.LengthUnit.FEET);
        var q2 = new GenericQuantityMeasurement.QuantityLength(12.0, GenericQuantityMeasurement.LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_DifferentValue() {
        var q1 = new GenericQuantityMeasurement.QuantityLength(1.0, GenericQuantityMeasurement.LengthUnit.FEET);
        var q2 = new GenericQuantityMeasurement.QuantityLength(2.0, GenericQuantityMeasurement.LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_SameReference() {
        var q1 = new GenericQuantityMeasurement.QuantityLength(1.0, GenericQuantityMeasurement.LengthUnit.FEET);

        assertTrue(q1.equals(q1));
    }

    @Test
    void testEquality_NullComparison() {
        var q1 = new GenericQuantityMeasurement.QuantityLength(1.0, GenericQuantityMeasurement.LengthUnit.FEET);

        assertFalse(q1.equals(null));
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> new GenericQuantityMeasurement.QuantityLength(1.0, null));
    }
}
