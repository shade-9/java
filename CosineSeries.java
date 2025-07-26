import myPackage.myMath;
import java.util.Scanner;

public class CosineSeries {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the value of x (angle in radians) and number of terms n
        System.out.print("Enter the value of x (in radians): ");
        double x = sc.nextDouble();

        System.out.print("Enter the number of terms (n): ");
        int n = sc.nextInt();

        // Calculate cos(x) using the series
        double cosX = 1;  // First term of the series is 1
        int sign = -1;    // Alternating signs for the terms

        for (int i = 2; i <= 2 * (n - 1); i += 2) {
            // Calculate next term of the series using myMath class methods
            double term = myMath.power(x, i) / myMath.fact(i);
            cosX += sign * term;
            sign *= -1;  // Alternate the sign
        }

        // Output the result
        System.out.println("cos(" + x + ") = " + cosX);
    }
}
