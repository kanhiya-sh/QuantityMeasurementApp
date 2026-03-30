package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TargetUnitAdditionTest {

    private static final double EPSILON = 0.001;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        TargetUnitAddition q1 = new TargetUnitAddition(1.0, LengthUnit.FEET);
        TargetUnitAddition q2 = new TargetUnitAddition(2.0, LengthUnit.FEET);

        assertEquals(new TargetUnitAddition(3.0, LengthUnit.FEET), q1.add(q2));
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        TargetUnitAddition q1 = new TargetUnitAddition(1.0, LengthUnit.FEET);
        TargetUnitAddition q2 = new TargetUnitAddition(12.0, LengthUnit.INCHES);

        assertEquals(new TargetUnitAddition(2.0, LengthUnit.FEET), q1.add(q2));
    }

    @Test
    void testAddition_Commutativity_UC6() {
        TargetUnitAddition q1 = new TargetUnitAddition(1.0, LengthUnit.FEET);
        TargetUnitAddition q2 = new TargetUnitAddition(12.0, LengthUnit.INCHES);

        TargetUnitAddition r1 = q1.add(q2);
        TargetUnitAddition r2 = q2.add(q1);

        // Converting both to common unit (feet)
        double r1Feet = r1.getUnit().toFeet(r1.getValue());
        double r2Feet = r2.getUnit().toFeet(r2.getValue());

        assertEquals(r1Feet, r2Feet, 0.001);
    }


    @Test
    void testAddition_WithZero() {
        TargetUnitAddition q1 = new TargetUnitAddition(5.0, LengthUnit.FEET);
        TargetUnitAddition zero = new TargetUnitAddition(0.0, LengthUnit.INCHES);

        assertEquals(new TargetUnitAddition(5.0, LengthUnit.FEET), q1.add(zero));
    }

    @Test
    void testAddition_NegativeValues() {
        TargetUnitAddition q1 = new TargetUnitAddition(5.0, LengthUnit.FEET);
        TargetUnitAddition q2 = new TargetUnitAddition(-2.0, LengthUnit.FEET);

        assertEquals(new TargetUnitAddition(3.0, LengthUnit.FEET), q1.add(q2));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        TargetUnitAddition q1 = new TargetUnitAddition(1.0, LengthUnit.FEET);
        TargetUnitAddition q2 = new TargetUnitAddition(12.0, LengthUnit.INCHES);

        TargetUnitAddition result = TargetUnitAddition.add(q1, q2, LengthUnit.FEET);

        assertEquals(new TargetUnitAddition(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        TargetUnitAddition q1 = new TargetUnitAddition(1.0, LengthUnit.FEET);
        TargetUnitAddition q2 = new TargetUnitAddition(12.0, LengthUnit.INCHES);

        TargetUnitAddition result = TargetUnitAddition.add(q1, q2, LengthUnit.INCHES);

        assertEquals(new TargetUnitAddition(24.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        TargetUnitAddition q1 = new TargetUnitAddition(1.0, LengthUnit.FEET);
        TargetUnitAddition q2 = new TargetUnitAddition(12.0, LengthUnit.INCHES);

        TargetUnitAddition result = TargetUnitAddition.add(q1, q2, LengthUnit.YARDS);

        assertEquals(0.666, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        TargetUnitAddition q1 = new TargetUnitAddition(1.0, LengthUnit.FEET);
        TargetUnitAddition q2 = new TargetUnitAddition(12.0, LengthUnit.INCHES);

        TargetUnitAddition r1 = TargetUnitAddition.add(q1, q2, LengthUnit.YARDS);
        TargetUnitAddition r2 = TargetUnitAddition.add(q2, q1, LengthUnit.YARDS);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        TargetUnitAddition q1 = new TargetUnitAddition(1.0, LengthUnit.FEET);
        TargetUnitAddition q2 = new TargetUnitAddition(12.0, LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class, () -> TargetUnitAddition.add(q1, q2, null));
    }

    @Test
    void testAddition_LargeValues() {
        TargetUnitAddition q1 = new TargetUnitAddition(1e6, LengthUnit.FEET);
        TargetUnitAddition q2 = new TargetUnitAddition(1e6, LengthUnit.FEET);

        TargetUnitAddition result = TargetUnitAddition.add(q1, q2, LengthUnit.FEET);

        assertEquals(2e6, result.getValue(), EPSILON);
    }
}

