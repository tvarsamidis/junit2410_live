package gr.codehub.tests.util;

import gr.codehub.tests.functional.HypotenuseCalculator;

public class HypotenuseExample {


    public static HypotenuseCalculator standardHypotenuse() {
        HypotenuseCalculator standardHypotenuse = (a, b) -> Math.sqrt(a * a + b * b);
        return standardHypotenuse;
    }

    public static HypotenuseCalculator roundedHypotenuse() {
        HypotenuseCalculator roundedHypotenuse = (a, b) -> Math.round(Math.sqrt(a * a + b * b) * 100.0) / 100.0;
        return roundedHypotenuse;
    }

    public static HypotenuseCalculator loggedHypotenuse() {
        HypotenuseCalculator loggedHypotenuse = (a, b) -> {
            double result = Math.sqrt(a * a + b * b);
            System.out.println("Calculating hypotenuse for a = " + a + ", b = " + b + ": " + result);
            return result;
        };
        return loggedHypotenuse;
    }

    public static void main(String[] args) {
        HypotenuseCalculator hypotenuse = standardHypotenuse();
        double result = hypotenuse.calculate(3, 4);
        System.out.println("Hypotenuse: " + result);
    }
}
