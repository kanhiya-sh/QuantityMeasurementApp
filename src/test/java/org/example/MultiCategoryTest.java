package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MultiCategoryTest {

    @Test
    void testLengthEquality() {
        Quantity<LengthUnit10> q1 = new Quantity<>(1.0, LengthUnit10.FEET);
        Quantity<LengthUnit10> q2 = new Quantity<>(12.0, LengthUnit10.INCHES);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testWeightEquality() {
        Quantity<WeightUnit10> w1 = new Quantity<>(1.0, WeightUnit10.KILOGRAM);
        Quantity<WeightUnit10> w2 = new Quantity<>(1000.0, WeightUnit10.GRAM);
        assertTrue(w1.equals(w2));
    }

    @Test
    void testLengthConversion() {
        Quantity<LengthUnit10> q = new Quantity<>(1.0, LengthUnit10.FEET);
        Quantity<LengthUnit10> result = new Quantity<>(12.0, LengthUnit10.INCHES);
        assertTrue(q.convertTo(LengthUnit10.INCHES).equals(result));

    }
}

