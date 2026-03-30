package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class SubtractionAndDivisionTest {
    @Test
    public void testSubtractionSameUnit() {
        Quantity<LengthUnit10> q1 = new Quantity<>(10, LengthUnit10.FEET);
        Quantity<LengthUnit10> q2 = new Quantity<>(5, LengthUnit10.FEET);
        Quantity<LengthUnit10> result = q1.subtract(q2);
        assertEquals(5.0, result.getValue(), 0.01);
    }

    @Test
    public void testSubtractionCrossUnit() {
        Quantity<LengthUnit10> q1 = new Quantity<>(10, LengthUnit10.FEET);
        Quantity<LengthUnit10> q2 = new Quantity<>(6, LengthUnit10.INCHES);
        Quantity<LengthUnit10> result = q1.subtract(q2);
        assertEquals(9.5, result.getValue(), 0.1);
    }

    @Test
    public void testDivision() {
        Quantity<LengthUnit10> q1 = new Quantity<>(10, LengthUnit10.FEET);
        Quantity<LengthUnit10> q2 = new Quantity<>(2, LengthUnit10.FEET);
        double result = q1.divide(q2);
        assertEquals(5.0, result, 0.01);
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
