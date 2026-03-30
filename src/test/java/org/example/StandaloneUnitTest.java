package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StandaloneUnitTest {
    private static final double EPSILON = 0.001;

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0,
                StandaloneLengthUnit.INCHES.convertToBaseUnit(12.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0,
        		StandaloneLengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0,
        		StandaloneLengthUnit.YARDS.convertToBaseUnit(1.0),
                EPSILON);
    }

    // ---- Equality Test ----

    @Test
    void testEquality_CrossUnit() {
    	StandaloneUnit q1 = new StandaloneUnit(1.0, StandaloneLengthUnit.FEET);
    	StandaloneUnit q2 = new StandaloneUnit(12.0, StandaloneLengthUnit.INCHES);
        assertEquals(q1, q2);
    }

    @Test
    void testConvertTo() {
    	StandaloneUnit q = new StandaloneUnit(1.0, StandaloneLengthUnit.FEET);
    	StandaloneUnit result = q.convertTo(StandaloneLengthUnit.INCHES);
        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_UC6() {
    	StandaloneUnit q1 = new StandaloneUnit(1.0, StandaloneLengthUnit.FEET);
    	StandaloneUnit q2 = new StandaloneUnit(12.0, StandaloneLengthUnit.INCHES);
        assertEquals(new StandaloneUnit(2.0, StandaloneLengthUnit.FEET), q1.add(q2));
    }

    @Test
    void testAddition_UC7_TargetUnit_Yards() {
    	StandaloneUnit q1 = new StandaloneUnit(1.0, StandaloneLengthUnit.FEET);
    	StandaloneUnit q2 = new StandaloneUnit(12.0, StandaloneLengthUnit.INCHES);
    	StandaloneUnit result = StandaloneUnit.add(q1, q2, StandaloneLengthUnit.YARDS);
        assertEquals(0.666, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_Commutativity() {
    	StandaloneUnit q1 = new StandaloneUnit(1.0, StandaloneLengthUnit.FEET);
    	StandaloneUnit q2 = new StandaloneUnit(12.0, StandaloneLengthUnit.INCHES);
    	StandaloneUnit r1 = StandaloneUnit.add(q1, q2, StandaloneLengthUnit.FEET);
    	StandaloneUnit r2 = StandaloneUnit.add(q2, q1, StandaloneLengthUnit.FEET);
        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new StandaloneUnit(1.0, null));
    }

    @Test
    void testNullTargetUnit() {
    	StandaloneUnit q1 = new StandaloneUnit(1.0, StandaloneLengthUnit.FEET);
    	StandaloneUnit q2 = new StandaloneUnit(12.0, StandaloneLengthUnit.INCHES);
        assertThrows(IllegalArgumentException.class, () -> StandaloneUnit.add(q1, q2, null));
    }
}

