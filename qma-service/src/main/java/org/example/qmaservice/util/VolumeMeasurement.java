package org.example.qmaservice.util;

public class VolumeMeasurement {
	public static void main(String[] args) {
        Quantity<VolumeUnit> vol1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> vol2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> vol3 = new Quantity<>(1.0, VolumeUnit.GALLON);
        System.out.println("Equality : ");
        System.out.println(vol1 + " == " + vol2 + " : " + vol1.equals(vol2));

        System.out.println("\nConversion : ");
        System.out.println(vol1 + " -> " + vol1.convertTo(VolumeUnit.MILLILITRE));
        System.out.println(vol3 + " -> " + vol3.convertTo(VolumeUnit.LITRE));

        System.out.println("\nAddition : ");
        System.out.println(vol1 + " + " + vol2 + " = " + vol1.add(vol2));
        System.out.println(vol1 + " + " + vol3 + " (in mL) = " + vol1.add(vol3, VolumeUnit.MILLILITRE));
    }
}
