import java.util.*;

public class PocketMoneyManager {
    abstract class Transaction {
        protected double amount;
        protected String description;
        protected Date date;

        public Transaction(double amount, String description, Date date) {
            this.amount = amount;
            this.description = description;
            this.date = date;
        }

        public double getAmount() {
            return amount;
        }

        public String getDescription() {
            return description;
        }

        public Date getDate() {
            return date;
        }

        public abstract String getDetails();
    }

    class Income extends Transaction {
        public Income(double amount, String description, Date date) {
            super(amount, description, date);
        }

        @Override
        public String getDetails() {
            return "Income: $" + amount + " | " + description + " | Date: " + date;
        }
    }

    class Expense extends Transaction {
        public Expense(double amount, String description, Date date) {
            super(amount, description, date);
        }

        @Override
        public String getDetails() {
            return "Expense: $" + amount + " | " + description + " | Date: " + date;
        }
    }

    class User {
        private String name;
        private String emailID;
        private double budget;
        private List<Transaction> transactions = new ArrayList<>();

        public String getName() {
            return name;
        }

        public String getEmailID() {
            return emailID;
        }

        public double getBudget() {
            return budget;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmailID(String emailID) {
            this.emailID = emailID;
        }

        public void setBudget(double budget) {
            this.budget = budget;
        }

        User() {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Name: ");
            name = sc.nextLine();
            System.out.println("Enter EmailID: ");
            emailID = sc.nextLine();
            System.out.println("Enter Budget: ");
            budget = sc.nextDouble();
        }

        public void addExpense(double amount, String description) {
            Expense expense = new Expense(amount, description, new Date());
            transactions.add(expense);
        }

        public void addIncome(double amount, String description) {
            Income income = new Income(amount, description, new Date());
            transactions.add(income);
        }

        public double getTotalExpenses() {
            double total = 0;
            for (Transaction t : transactions) {
                if (t instanceof Expense) {
                    total += t.getAmount();
                }
            }
            return total;
        }

        public double getRemainingBudget() {
            double totalExpenses = getTotalExpenses();
            return budget - totalExpenses;
        }

        public void transactionHistory() {
            System.out.println("Transaction Report for " + name);
            for (Transaction t : transactions) {
                System.out.println(t.getDetails());
            }
            System.out.println("Remaining Budget: $" + getRemainingBudget());
        }
    }

    public static void main(String[] args) {
        PocketMoneyManager manager = new PocketMoneyManager();
        PocketMoneyManager.User user = manager.new User();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Pocket Money Manager Menu ---");
            System.out.println("1. Add Expense");
            System.out.println("2. Add Income");
            System.out.println("3. Display User Details");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount spent: ");
                    double expenseAmount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter expense description: ");
                    String expenseDesc = scanner.nextLine();
                    user.addExpense(expenseAmount, expenseDesc);
                    System.out.println("Expense added.");
                    break;
                case 2:
                    System.out.print("Enter income amount: ");
                    double incomeAmount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter income description: ");
                    String incomeDesc = scanner.nextLine();
                    user.addIncome(incomeAmount, incomeDesc);
                    System.out.println("Income added.");
                    break;
                case 3:
                    System.out.println("\n--- User Details ---");
                    System.out.println("Name: " + user.getName());
                    System.out.println("Email: " + user.getEmailID());
                    System.out.println("Budget: $" + user.getBudget());
                    System.out.println("Remaining Budget: $" + user.getRemainingBudget());
                    break;
                case 4:
                    System.out.println("\n--- Transaction History ---");
                    user.transactionHistory();
                    break;
                case 5:
                    System.out.println("Exiting Pocket Money Manager...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 5);

        scanner.close();  // Close the scanner after the loop ends
    }
}
