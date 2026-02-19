package com.example.QuantityApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FeetInchesTest {

    @Test
    void testFeetEquality_SameValue() {
        assertTrue(FeetInchesMeasurement.checkFeetEquality(1.0, 1.0));
    }

    @Test
    void testFeetEquality_DifferentValue() {
        assertFalse(FeetInchesMeasurement.checkFeetEquality(1.0, 2.0));
    }

    @Test
    void testFeetEquality_SameReference() {
    	FeetInchesMeasurement.Feet f = new FeetInchesMeasurement.Feet(1.0);
        assertTrue(f.equals(f));
    }

    @Test
    void testFeetEquality_NullComparison() {
    	FeetInchesMeasurement.Feet f =new  FeetInchesMeasurement.Feet(1.0);
        assertFalse(f.equals(null));
    }

    @Test
    void testFeetEquality_NonNumericInput() {
    	FeetInchesMeasurement.Feet f = new FeetInchesMeasurement.Feet(1.0);
        assertFalse(f.equals("invalid"));
    }


    @Test
    void testInchesEquality_SameValue() {
        assertTrue(FeetInchesMeasurement.checkInchesEquality(1.0, 1.0));
    }

    @Test
    void testInchesEquality_DifferentValue() {
        assertFalse(FeetInchesMeasurement.checkInchesEquality(1.0, 2.0));
    }

    @Test
    void testInchesEquality_SameReference() {
    	FeetInchesMeasurement.Inches i = new FeetInchesMeasurement.Inches(1.0);
        assertTrue(i.equals(i));
    }

    @Test
    void testInchesEquality_NullComparison() {
    	FeetInchesMeasurement.Inches i = new FeetInchesMeasurement.Inches(1.0);
        assertFalse(i.equals(null));
    }

    @Test
    void testInchesEquality_NonNumericInput() {
    	FeetInchesMeasurement.Inches i = new FeetInchesMeasurement.Inches(1.0);
        assertFalse(i.equals("invalid"));
    }
}

