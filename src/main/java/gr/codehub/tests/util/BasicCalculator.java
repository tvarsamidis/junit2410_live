package gr.codehub.tests.util;

public class BasicCalculator {
    private double result = 0;

    public void plus(double i) {
        result += i;
    }

    public void minus(double i) {
        result -= i;
    }

    public void times(double i) {
        result *= i;
    }

    public void divideBy(double i) {
        if (i == 0) {
            throw new RuntimeException("Cannot divide by 0");
            // result = 0;
        } else {
            result /= i;
        }
    }

    public void reset() {
        result = 0;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
