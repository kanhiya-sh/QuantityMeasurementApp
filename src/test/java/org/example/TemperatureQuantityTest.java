package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TemperatureQuantityTest {

    @Test
    public void testCelsiusToFahrenheitConversion() {
        Quantity<TemperatureUnit> temp = new Quantity<>(100, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> result = temp.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(212.0, result.getValue(), 0.01);
    }

    @Test
    public void testFahrenheitToCelsiusConversion() {
        Quantity<TemperatureUnit> temp = new Quantity<>(32, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> result = temp.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(0.0, result.getValue(), 0.01);
    }

    @Test
    public void testCelsiusEqualsFahrenheit() {
        Quantity<TemperatureUnit> c = new Quantity<>(0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> f = new Quantity<>(32, TemperatureUnit.FAHRENHEIT);
        assertTrue(c.equals(f));
    }

    @Test
    public void testTemperatureAdditionNotSupported() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        try {
            t1.add(t2);
            fail("Expected UnsupportedOperationException");
        }
        catch (UnsupportedOperationException e) {
            assertEquals("Temperature does not support addition operation", e.getMessage());
        }
    }

    @Test
    public void testTemperatureSubtractionNotSupported() {
        Quantity<TemperatureUnit> temp1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> temp2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        try {
            temp1.subtract(temp2);
            fail("Expected UnsupportedOperationException");
        }
        catch (UnsupportedOperationException e) {
            assertEquals("Temperature does not support subtraction operation", e.getMessage());
        }
    }

    @Test
    public void testTemperatureDivisionNotSupported() {
        Quantity<TemperatureUnit> temp1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> temp2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        try {
            temp1.divide(temp2);
            fail("Expected UnsupportedOperationException");
        }
        catch (UnsupportedOperationException e) {
            assertEquals("Temperature does not support division operation", e.getMessage());
        }
    }
}

