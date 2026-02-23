package com.example.QuantityApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class YardMeasurementTest {

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        var q1 = new YardMeasurement.QuantityLength(1.0, YardMeasurement.LengthUnit.YARD);

        var q2 = new YardMeasurement.QuantityLength(3.0,  YardMeasurement.LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        var q1 = new YardMeasurement.QuantityLength(1.0, YardMeasurement.LengthUnit.YARD);

        var q2 = new YardMeasurement.QuantityLength(36.0, YardMeasurement.LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_CentimeterToInch_EquivalentValue() {
        var q1 = new YardMeasurement.QuantityLength(1.0, YardMeasurement.LengthUnit.CENTIMETER);

        var q2 = new YardMeasurement.QuantityLength(0.393701, YardMeasurement.LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        var yard = new YardMeasurement.QuantityLength(1.0, YardMeasurement.LengthUnit.YARD);

        var feet = new YardMeasurement.QuantityLength(3.0, YardMeasurement.LengthUnit.FEET);

        var inch = new YardMeasurement.QuantityLength(36.0, YardMeasurement.LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> new YardMeasurement.QuantityLength(1.0, null));
    }
}

