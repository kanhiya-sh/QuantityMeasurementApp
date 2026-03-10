package org.example;

public class CentralizedArithematicLogicOperations {
    public static void main(String[] args) {
        Quantity<LengthUnit10> quan1 = new Quantity<>(10, LengthUnit10.FEET);
        Quantity<LengthUnit10> quan2 = new Quantity<>(6, LengthUnit10.INCHES);
        System.out.println("Addition : " + quan1.add(quan2));
        System.out.println("Subtraction : " + quan1.subtract(quan2));
        System.out.println("Division : " + quan1.divide(new Quantity<>(2, LengthUnit10.FEET)));
        Quantity<WeightUnit10> w1 = new Quantity<>(10, WeightUnit10.KILOGRAM);
        Quantity<WeightUnit10> w2 = new Quantity<>(5000, WeightUnit10.GRAM);

        System.out.println("Weight Addition : " + w1.add(w2));
        Quantity<VolumeUnit> vol1 = new Quantity<>(5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> vol2 = new Quantity<>(500, VolumeUnit.MILLILITRE);

        System.out.println("Volume Subtraction : " + vol1.subtract(vol2));
    }
}
