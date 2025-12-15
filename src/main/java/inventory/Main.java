package inventory;

import java.util.Scanner;

/**
 * Main class providing command-line interface for the inventory system.
 */
public class Main {

    private static InventoryManager manager;
    private static Scanner scanner;

    public static void main(String[] args) {

        manager = new InventoryManager();
        scanner = new Scanner(System.in);

        System.out.println("Welcome to Inventory Management System!");
        loadSampleData();

        while (true) {
            showMenu();
            int choice = getChoice();
            handleChoice(choice);
        }
    }

    /**
     * Load sample data
     */
    private static void loadSampleData() {
        manager.addProduct("B001", "Java Programming", "BOOK", 29.99, 10);
        manager.addProduct("B002", "Design Patterns", "BOOK", 35.50, 8);
        manager.addProduct("E001", "Laptop", "ELECTRONICS", 999.99, 5);
        manager.addProduct("E002", "Mouse", "ELECTRONICS", 25.99, 15);
    }

    /**
     * Show menu
     */
    private static void showMenu() {
        System.out.println("\n==== MENU ====");
        System.out.println("1. Add Product");
        System.out.println("2. View Inventory");
        System.out.println("3. Sell Product");
        System.out.println("4. Add Stock");
        System.out.println("5. View Statistics");
        System.out.println("6. Exit");
        System.out.print("Choose an option (1-6): ");
    }

    /**
     * Get menu choice safely
     */
    private static int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number (1-6): ");
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    /**
     * Handle menu option
     */
    private static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                addProduct();
                break;
            case 2:
                manager.viewInventory();
                break;
            case 3:
                sellProduct();
                break;
            case 4:
                addStock();
                break;
            case 5:
                manager.viewStatistics();
                break;
            case 6:
                System.out.println("Exiting application. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please select 1-6.");
        }
    }

    /**
     * Add product flow
     */
    private static void addProduct() {
        String id = getStringInput("Enter product ID: ");
        String name = getStringInput("Enter product name: ");
        String type = getStringInput("Enter product type (BOOK / ELECTRONICS): ");
        double price = getDoubleInput("Enter product price: ");
        int quantity = getIntInput("Enter initial quantity: ");

        manager.addProduct(id, name, type, price, quantity);
    }

    /**
     * Sell product flow
     */
    private static void sellProduct() {
        String id = getStringInput("Enter product ID: ");
        int quantity = getIntInput("Enter quantity to sell: ");
        String discountType = getStringInput("Enter discount type (STUDENT / BULK / NONE): ");

        manager.sellProduct(id, quantity, discountType);
    }

    /**
     * Add stock flow
     */
    private static void addStock() {
        String id = getStringInput("Enter product ID: ");
        int quantity = getIntInput("Enter quantity to add: ");

        manager.addStock(id, quantity);
    }

    // ===== Helper input methods =====

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid number. Try again: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid number. Try again: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}
