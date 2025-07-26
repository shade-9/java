package myPackage;

public class myMath {

    // Method to compute x^y (x raised to the power y)
    public static double power(double x, int y) {
        double result = 1;
        for (int i = 0; i < y; i++) {
            result *= x;
        }
        return result;
    }

    // Method to compute factorial of x
    public static long fact(int x) {
        long result = 1;
        for (int i = 2; i <= x; i++) {
            result *= i;
        }
        return result;
    }
}
