import java.util.Random;
import java.util.Scanner;

// Thread to generate random integers every second
class NumberGenerator extends Thread {
    private SharedData sharedData;

    public NumberGenerator(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            for (int i = 0; i < 3; i++) { // Loop to generate 3 random numbers
                int num = rand.nextInt(100); // Generate a random integer between 0 and 99
                System.out.println("Generated Number: " + num);
                sharedData.setNumber(num);

                // Wait for 1 second
                Thread.sleep(1000);
            }
            // Stop the program after generating three numbers
            System.out.println("Number generation completed.");
            sharedData.stopThreads(); // Signal other threads to stop

        } catch (InterruptedException e) {
            System.out.println("Number Generator interrupted.");
        }
    }
}

// Thread to compute the square of even numbers
class SquareCalculator extends Thread {
    private SharedData sharedData;

    public SquareCalculator(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (sharedData) {
                    if (sharedData.shouldStop()) {
                        break; // Stop thread if signaled
                    }
                    int num = sharedData.getNumber();
                    if (num % 2 == 0) {
                        System.out.println("Square of " + num + " is " + (num * num));
                    }
                    sharedData.wait();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Square Calculator interrupted.");
        }
        System.out.println("Square Calculator stopped.");
    }
}

// Thread to compute the cube of odd numbers
class CubeCalculator extends Thread {
    private SharedData sharedData;

    public CubeCalculator(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (sharedData) {
                    if (sharedData.shouldStop()) {
                        break; // Stop thread if signaled
                    }
                    int num = sharedData.getNumber();
                    if (num % 2 != 0) {
                        System.out.println("Cube of " + num + " is " + (num * num * num));
                    }
                    sharedData.wait();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Cube Calculator interrupted.");
        }
        System.out.println("Cube Calculator stopped.");
    }
}

// Shared data class for storing and sharing numbers between threads
class SharedData {
    private int number;
    private boolean stop = false;

    public synchronized void setNumber(int number) {
        this.number = number;
        notifyAll(); // Notify other threads when a new number is generated
    }

    public synchronized int getNumber() throws InterruptedException {
        wait(); // Wait for a new number to be generated
        return number;
    }

    public synchronized void stopThreads() {
        stop = true;
        notifyAll(); // Notify all threads to stop
    }

    public synchronized boolean shouldStop() {
        return stop;
    }
}

public class MultiThreadedMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SharedData sharedData = new SharedData();
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Start Multi-Threaded Application");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // Start the threads
                    NumberGenerator generator = new NumberGenerator(sharedData);
                    SquareCalculator squareCalculator = new SquareCalculator(sharedData);
                    CubeCalculator cubeCalculator = new CubeCalculator(sharedData);

                    generator.start();
                    squareCalculator.start();
                    cubeCalculator.start();

                    // Ensure all threads finish before returning to the menu
                    try {
                        generator.join();
                        squareCalculator.join();
                        cubeCalculator.join();
                    } catch (InterruptedException e) {
                        System.out.println("Threads interrupted.");
                    }

                    System.out.println("All threads completed their execution.");
                    break;

                case 2:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 2);
    }
}
