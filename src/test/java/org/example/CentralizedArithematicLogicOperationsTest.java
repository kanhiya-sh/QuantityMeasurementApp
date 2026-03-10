package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class CentralizedArithematicLogicOperationsTest {
    @Test
    public void testConvertFeetToInches() {
        Quantity<LengthUnit10> q = new Quantity<>(1, LengthUnit10.FEET);
        Quantity<LengthUnit10> result = q.convertTo(LengthUnit10.INCHES);
        assertEquals(12.0, result.getValue(), 0.01);
    }

    @Test
    public void testAddLength() {
        Quantity<LengthUnit10> q1 = new Quantity<>(1, LengthUnit10.FEET);
        Quantity<LengthUnit10> q2 = new Quantity<>(12, LengthUnit10.INCHES);
        Quantity<LengthUnit10> result = q1.add(q2);
        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    public void testSubtractLength() {
        Quantity<LengthUnit10> q1 = new Quantity<>(10, LengthUnit10.FEET);
        Quantity<LengthUnit10> q2 = new Quantity<>(6, LengthUnit10.INCHES);
        Quantity<LengthUnit10> result = q1.subtract(q2);
        assertEquals(9.5, result.getValue(), 0.01);
    }

    @Test
    public void testDivide() {
        Quantity<LengthUnit10> q1 = new Quantity<>(10, LengthUnit10.FEET);
        Quantity<LengthUnit10> q2 = new Quantity<>(2, LengthUnit10.FEET);
        double result = q1.divide(q2);
        assertEquals(5.0, result, 0.01);
    }

    @Test
    public void testAddWeightWithTargetUnit() {
        Quantity<WeightUnit10> q1 = new Quantity<>(10, WeightUnit10.KILOGRAM);
        Quantity<WeightUnit10> q2 = new Quantity<>(5000, WeightUnit10.GRAM);
        Quantity<WeightUnit10> result = q1.add(q2, WeightUnit10.GRAM);
        assertEquals(15000.0, result.getValue(), 0.01);
    }

    @Test
    public void testSubtractVolume() {
        Quantity<VolumeUnit> q1 = new Quantity<>(5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(2, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = q1.subtract(q2);
        assertEquals(3.0, result.getValue(), 0.01);
    }

    @Test
    public void testDivisionByZero() {
        Quantity<LengthUnit10> q1 = new Quantity<>(10, LengthUnit10.FEET);
        Quantity<LengthUnit10> q2 = new Quantity<>(0, LengthUnit10.FEET);
        try {
            q1.divide(q2);
            fail("Expected ArithmeticException");
        }
        catch (ArithmeticException e) {
            assertEquals("Division by zero", e.getMessage());
        }
    }
}
