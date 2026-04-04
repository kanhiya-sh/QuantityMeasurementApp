package org.example.qmaservice.util;

public class UnitFactory {
    public static IMeasurable getUnit(String type, String unit) {
        switch(type.toUpperCase()) {
            case "LENGTH":
                return LengthUnit10.valueOf(unit.toUpperCase());

            case "WEIGHT":
                return WeightUnit10.valueOf(unit.toUpperCase());

            case "VOLUME":
                return VolumeUnit.valueOf(unit.toUpperCase());

            case "TEMPERATURE":
                return TemperatureUnit.valueOf(unit.toUpperCase());

            default:
                throw new IllegalArgumentException("Invalid Measurement type");
        }
    }
}
