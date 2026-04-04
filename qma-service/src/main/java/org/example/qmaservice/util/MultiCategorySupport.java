package org.example.qmaservice.util;

public class MultiCategorySupport {
	public static <U extends IMeasurable> void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        System.out.println(q1 + " equals " + q2 + " : " + q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> q, U targetUnit) {
        System.out.println("Converted : " + q.convertTo(targetUnit));
    }

    public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U unit) {
        System.out.println("Addition : " + q1.add(q2, unit));
    }

    public static void main(String[] args) {
        Quantity<LengthUnit10> q1 = new Quantity<>(1.0, LengthUnit10.FEET);
        Quantity<LengthUnit10> q2 = new Quantity<>(12.0, LengthUnit10.INCHES);
        demonstrateEquality(q1, q2);
        demonstrateConversion(q1, LengthUnit10.INCHES);
        demonstrateAddition(q1, q2, LengthUnit10.FEET);
        Quantity<WeightUnit10> w1 = new Quantity<>(1.0, WeightUnit10.KILOGRAM);
        Quantity<WeightUnit10> w2 = new Quantity<>(1000.0, WeightUnit10.GRAM);
        demonstrateEquality(w1, w2);
        demonstrateConversion(w1, WeightUnit10.GRAM);
        demonstrateAddition(w1, w2, WeightUnit10.KILOGRAM);
    }
}
