package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeightMeasurementTest {
    private static final double EPSILON = 1e-4;

    @Test
    void testEquality_KgToKg() {
        assertEquals(new WeightMeasurement(1.0, WeightUnit.KILOGRAM), new WeightMeasurement(1.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    void testEquality_KgToGram() {
        assertEquals(new WeightMeasurement(1.0, WeightUnit.KILOGRAM), new WeightMeasurement(1000.0, WeightUnit.GRAM)
        );
    }

    @Test
    void testConversion_KgToPound() {
        WeightMeasurement result = new WeightMeasurement(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.POUND);

        // as 1 kg = 2.20462262185 pounds
        assertEquals(2.20462, result.getValue(), 1e-4);
    }

    @Test
    void testEquality_Null() {
        assertFalse(new WeightMeasurement(1.0, WeightUnit.KILOGRAM).equals(null));
    }

    @Test
    void testEquality_DifferentValue() {
        assertNotEquals(new WeightMeasurement(1.0, WeightUnit.KILOGRAM), new WeightMeasurement(2.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    void testConversion_KgToGram() {
        WeightMeasurement result = new WeightMeasurement(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_PoundToKg() {
        WeightMeasurement result = new WeightMeasurement(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 0.01);
    }

    @Test
    void testConversion_RoundTrip() {
        WeightMeasurement original = new WeightMeasurement(1.5, WeightUnit.KILOGRAM);

        WeightMeasurement roundTrip = original.convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(original.getValue(), roundTrip.getValue(), EPSILON);
    }

    @Test
    void testAddition_SameUnit() {
        WeightMeasurement result = new WeightMeasurement(1.0, WeightUnit.KILOGRAM)
                .add(new WeightMeasurement(2.0, WeightUnit.KILOGRAM));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit() {
        WeightMeasurement result = new WeightMeasurement(1.0, WeightUnit.KILOGRAM)
                .add(new WeightMeasurement(1000.0, WeightUnit.GRAM));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTarget() {
        WeightMeasurement result =
                WeightMeasurement.add(new WeightMeasurement(1.0, WeightUnit.KILOGRAM), new WeightMeasurement(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_Commutativity() {
        WeightMeasurement w1 = new WeightMeasurement(1.0, WeightUnit.KILOGRAM);
        WeightMeasurement w2 = new WeightMeasurement(1000.0, WeightUnit.GRAM);

        WeightMeasurement r1 = WeightMeasurement.add(w1, w2, WeightUnit.KILOGRAM);
        WeightMeasurement r2 = WeightMeasurement.add(w2, w1, WeightUnit.KILOGRAM);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    @Test
    void testAddition_Negative() {
        WeightMeasurement result = new WeightMeasurement(5.0, WeightUnit.KILOGRAM)
                .add(new WeightMeasurement(-2000.0, WeightUnit.GRAM));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_Zero() {
        WeightMeasurement result = new WeightMeasurement(5.0, WeightUnit.KILOGRAM)
                .add(new WeightMeasurement(0.0, WeightUnit.GRAM));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new WeightMeasurement(1.0, null));
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new WeightMeasurement(Double.NaN, WeightUnit.KILOGRAM));
    }
}