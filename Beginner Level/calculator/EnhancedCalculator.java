import java.util.Scanner;

public class EnhancedCalculator {

    static Scanner sc = new Scanner(System.in);

    public static void basicOperations() {
        System.out.println("\nBasic Operations:");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.print("Choose operation (1-4): ");
        int choice = sc.nextInt();

        System.out.print("Enter first number: ");
        double a = sc.nextDouble();
        System.out.print("Enter second number: ");
        double b = sc.nextDouble();

        switch (choice) {
            case 1 -> System.out.println("Result: " + (a + b));
            case 2 -> System.out.println("Result: " + (a - b));
            case 3 -> System.out.println("Result: " + (a * b));
            case 4 -> {
                if (b != 0)
                    System.out.println("Result: " + (a / b));
                else
                    System.out.println("Error: Division by zero");
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    public static void scientificOperations() {
        System.out.println("\nScientific Operations:");
        System.out.println("1. Square Root");
        System.out.println("2. Exponentiation");
        System.out.print("Choose operation (1-2): ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter number: ");
                double num = sc.nextDouble();
                if (num >= 0)
                    System.out.println("Result: " + Math.sqrt(num));
                else
                    System.out.println("Error: Negative input");
            }
            case 2 -> {
                System.out.print("Enter base: ");
                double base = sc.nextDouble();
                System.out.print("Enter exponent: ");
                double exp = sc.nextDouble();
                System.out.println("Result: " + Math.pow(base, exp));
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    public static void temperatureConversion() {
        System.out.println("\nTemperature Conversion:");
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        System.out.print("Choose conversion (1-2): ");
        int choice = sc.nextInt();

        System.out.print("Enter temperature: ");
        double temp = sc.nextDouble();

        switch (choice) {
            case 1 -> System.out.println("Result: " + ((temp * 9 / 5) + 32) + " °F");
            case 2 -> System.out.println("Result: " + ((temp - 32) * 5 / 9) + " °C");
            default -> System.out.println("Invalid choice!");
        }
    }

    public static void currencyConversion() {
        final double USD_TO_EUR = 0.92;
        final double EUR_TO_USD = 1.09;

        System.out.println("\nCurrency Conversion (Fixed Rates):");
        System.out.println("1. USD to EUR");
        System.out.println("2. EUR to USD");
        System.out.print("Choose conversion (1-2): ");
        int choice = sc.nextInt();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        switch (choice) {
            case 1 -> System.out.println("Result: " + (amount * USD_TO_EUR) + " EUR");
            case 2 -> System.out.println("Result: " + (amount * EUR_TO_USD) + " USD");
            default -> System.out.println("Invalid choice!");
        }
    }

    public static void unitConversions() {
        System.out.println("\nUnit Conversions:");
        System.out.println("1. Temperature");
        System.out.println("2. Currency");
        System.out.print("Choose conversion type (1-2): ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1 -> temperatureConversion();
            case 2 -> currencyConversion();
            default -> System.out.println("Invalid choice!");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Enhanced Console-based Calculator ---");
            System.out.println("1. Basic Arithmetic Operations");
            System.out.println("2. Scientific Calculations");
            System.out.println("3. Unit Conversions");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> basicOperations();
                case 2 -> scientificOperations();
                case 3 -> unitConversions();
                case 4 -> {
                    System.out.println("Exiting calculator. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please enter 1-4.");
            }
        }
    }
}