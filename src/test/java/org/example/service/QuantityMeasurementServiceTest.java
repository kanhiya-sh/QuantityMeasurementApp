package org.example.service;

import org.example.dto.QuantityDTO;
import org.example.repository.QuantityMeasurementCacheRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementServiceTest {
    private QuantityMeasurementServiceImpl service;

    @BeforeEach
    public void setup() {
        service = new QuantityMeasurementServiceImpl(QuantityMeasurementCacheRepository.getInstance());
    }

    @Test
    public void given1FeetAnd12Inch_ShouldBeEqual() {
        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "LENGTH");
        assertTrue(service.compare(q1, q2));
    }

    @Test
    public void given1FeetAnd12Inch_WhenAdded_ShouldReturn2Feet() {
        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "LENGTH");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(2.0, result.getValue(), 0.01);
        assertEquals("FEET", result.getUnit());
    }

    @Test
    public void given1Feet_WhenConvertedToInch_ShouldReturn12() {
        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LENGTH");
        QuantityDTO result = service.convert(q1, "INCHES");
        assertEquals(12.0, result.getValue(), 0.01);
    }

    @Test
    public void given100Celsius_WhenConvertedToFahrenheit_ShouldReturn212() {
        QuantityDTO temp = new QuantityDTO(100, "CELSIUS", "TEMPERATURE");
        QuantityDTO result = service.convert(temp, "FAHRENHEIT");
        assertEquals(212.0, result.getValue(), 0.01);
    }

    @Test
    public void givenInvalidType_ShouldThrowException() {
        try {
            QuantityDTO q = new QuantityDTO(10, "XYZ", "LENGTH");
            service.convert(q, "INCH");
            fail("Expected Exception");
        }
        catch (Exception e) {
            assertTrue(e.getMessage().contains("No enum"));
        }
    }
}
