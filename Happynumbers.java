import java.util.HashSet;
import java.util.Scanner;

public class HappyNumberChecker {

    // Function to calculate the sum of squares of digits of a number
    public static int sumOfSquaresOfDigits(int number) {
        int sum = 0;
        while (number != 0) {
            int digit = number % 10;
            sum += digit * digit;
            number /= 10;
        }
        return sum;
    }

    // Function to check if a number is a happy number
    public static boolean isHappyNumber(int number) {
        HashSet<Integer> seenNumbers = new HashSet<>();
        
        while (number != 1 && !seenNumbers.contains(number)) {
            seenNumbers.add(number);
            number = sumOfSquaresOfDigits(number);
        }

        return number == 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input a number
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        // Check if the number is happy or sad
        if (isHappyNumber(number)) {
            System.out.println(number + " is a happy number.");
        } else {
            System.out.println(number + " is a sad number.");
        }

        scanner.close();
    }
}
