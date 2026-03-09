package com.example.QuantityApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnitConversionTest {

    private static final double EPS = 1e-6;

    @Test
    void testFeetToInches() {
        assertEquals(12.0,
        		UnitConversion.QuantityLength
                        .convert(1.0, UnitConversion.LengthUnit.FEET, UnitConversion.LengthUnit.INCHES), EPS);
    }

    @Test
    void testYardsToFeet() {
        assertEquals(9.0,
        		UnitConversion.QuantityLength
                        .convert(3.0, UnitConversion.LengthUnit.YARDS, UnitConversion.LengthUnit.FEET), EPS);
    }

    @Test
    void testCentimetersToInches() {
        assertEquals(0.393701,
        		UnitConversion.QuantityLength
                        .convert(1.0, UnitConversion.LengthUnit.CENTIMETERS, UnitConversion.LengthUnit.INCHES), EPS);
    }

    @Test
    void testRoundTripConversion() {

        double original = 5.0;

        double converted = UnitConversion.QuantityLength.convert(original, UnitConversion.LengthUnit.FEET, UnitConversion.LengthUnit.YARDS);

        double back = UnitConversion.QuantityLength.convert(converted, UnitConversion.LengthUnit.YARDS, UnitConversion.LengthUnit.FEET);

        assertEquals(original, back, EPS);
    }

    @Test
    void testZeroValue() {
        assertEquals(0.0,
        		UnitConversion.QuantityLength
                        .convert(0.0, UnitConversion.LengthUnit.FEET, UnitConversion.LengthUnit.INCHES), EPS);
    }

    @Test
    void testNegativeValue() {
        assertEquals(-12.0,
        		UnitConversion.QuantityLength
                        .convert(-1.0, UnitConversion.LengthUnit.FEET, UnitConversion.LengthUnit.INCHES), EPS);
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () ->
        UnitConversion.QuantityLength
                        .convert(1.0, null, UnitConversion.LengthUnit.FEET));
    }

    @Test
    void testNaNValue() {
        assertThrows(IllegalArgumentException.class, () ->
        UnitConversion.QuantityLength
                        .convert(Double.NaN, UnitConversion.LengthUnit.FEET, UnitConversion.LengthUnit.INCHES));
    }
}



