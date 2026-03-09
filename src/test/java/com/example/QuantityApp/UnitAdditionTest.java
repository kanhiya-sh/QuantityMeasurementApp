package com.example.QuantityApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnitAdditionTest {

    private static final double EPS = 1e-6;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        var a = new UnitAddition.QuantityLength(1.0, UnitAddition.LengthUnit.FEET);

        var b = new UnitAddition.QuantityLength(2.0, UnitAddition.LengthUnit.FEET);

        assertEquals(3.0, a.add(b).getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        var a = new UnitAddition.QuantityLength(1.0, UnitAddition.LengthUnit.FEET);

        var b = new UnitAddition.QuantityLength(12.0, UnitAddition.LengthUnit.INCHES);

        assertEquals(2.0, a.add(b).getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        var a = new UnitAddition.QuantityLength(12.0, UnitAddition.LengthUnit.INCHES);

        var b = new UnitAddition.QuantityLength(1.0, UnitAddition.LengthUnit.FEET);

        assertEquals(24.0, a.add(b).getValue(), EPS);
    }

    @Test
    void testAddition_YardPlusFeet() {
        var a = new UnitAddition.QuantityLength(1.0, UnitAddition.LengthUnit.YARDS);

        var b = new UnitAddition.QuantityLength(3.0, UnitAddition.LengthUnit.FEET);

        assertEquals(2.0, a.add(b).getValue(), EPS);
    }

    @Test
    void testAddition_CentimeterPlusInch() {
        var a = new UnitAddition.QuantityLength(2.54, UnitAddition.LengthUnit.CENTIMETERS);

        var b = new UnitAddition.QuantityLength(1.0, UnitAddition.LengthUnit.INCHES);

        assertEquals(5.08, a.add(b).getValue(), 1e-2);
    }

    @Test
    void testAddition_Commutativity() {
        var a = new UnitAddition.QuantityLength(1.0, UnitAddition.LengthUnit.FEET);

        var b = new UnitAddition.QuantityLength(12.0, UnitAddition.LengthUnit.INCHES);

        assertEquals(a.add(b), b.add(a));
    }

    @Test
    void testAddition_WithZero() {
        var a = new UnitAddition.QuantityLength(5.0, UnitAddition.LengthUnit.FEET);

        var zero = new UnitAddition.QuantityLength(0.0, UnitAddition.LengthUnit.INCHES);

        assertEquals(5.0, a.add(zero).getValue(), EPS);
    }

    @Test
    void testAddition_NegativeValues() {
        var a = new UnitAddition.QuantityLength(5.0, UnitAddition.LengthUnit.FEET);

        var b = new UnitAddition.QuantityLength(-2.0, UnitAddition.LengthUnit.FEET);

        assertEquals(3.0, a.add(b).getValue(), EPS);
    }

    @Test
    void testAddition_NullOperand() {
        var a = new UnitAddition.QuantityLength(1.0, UnitAddition.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> a.add(null));
    }

    @Test
    void testAddition_LargeValues() {
        var a = new UnitAddition.QuantityLength(1e6, UnitAddition.LengthUnit.FEET);

        var b = new UnitAddition.QuantityLength(1e6, UnitAddition.LengthUnit.FEET);

        assertEquals(2e6, a.add(b).getValue(), EPS);
    }

    @Test
    void testAddition_SmallValues() {
        var a = new UnitAddition.QuantityLength(0.001, UnitAddition.LengthUnit.FEET);

        var b = new UnitAddition.QuantityLength(0.002, UnitAddition.LengthUnit.FEET);

        assertEquals(0.003, a.add(b).getValue(), EPS);
    }
}
