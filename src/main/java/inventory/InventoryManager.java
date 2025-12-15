package inventory;

import java.util.*;

/**
 * Main business logic class that manages inventory operations.
 */
public class InventoryManager {

    // Collection to store products (key = product id)
    private Map<String, Product> products;

    /**
     * Constructor
     */
    public InventoryManager() {
        products = new HashMap<>();
    }

    /**
     * Add product using Factory pattern
     */
    public void addProduct(String id, String name, String type, double price, int quantity) {
        try {
            if (products.containsKey(id)) {
                System.out.println("Product with ID " + id + " already exists.");
                return;
            }

            Product product = ProductFactory.createProduct(id, name, type, price, quantity);
            products.put(id, product);
            System.out.println("Product added successfully: " + product);

        } catch (IllegalArgumentException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    /**
     * Sell product using Strategy pattern for discounts
     */
    public void sellProduct(String id, int quantity, String discountType) {

        Product product = products.get(id);

        if (product == null) {
            System.out.println("Product not found: " + id);
            return;
        }

        if (quantity <= 0) {
            System.out.println("Invalid quantity to sell.");
            return;
        }

        if (product.getQuantity() < quantity) {
            System.out.println("Insufficient stock for product: " + id);
            return;
        }

        // Calculate discount
        DiscountCalculator.DiscountResult discountResult =
                DiscountCalculator.calculateDiscount(product, quantity, discountType);

        double originalPrice = product.getPrice() * quantity;
        double finalPrice = originalPrice - discountResult.getDiscountAmount();

        // Update stock
        product.sell(quantity);

        // Display sale info
        System.out.println("Sale completed:");
        System.out.println("Product: " + product.getName());
        System.out.println("Quantity: " + quantity);
        System.out.println("Original price: $" + originalPrice);
        System.out.println("Discount: $" + discountResult.getDiscountAmount());
        System.out.println("Final price: $" + finalPrice);
        System.out.println(discountResult.getDescription());
    }

    /**
     * Add stock to existing product
     */
    public void addStock(String id, int quantity) {
        Product product = products.get(id);

        if (product == null) {
            System.out.println("Product not found: " + id);
            return;
        }

        if (quantity <= 0) {
            System.out.println("Invalid stock quantity.");
            return;
        }

        product.addStock(quantity);
        System.out.println("Stock updated. New quantity: " + product.getQuantity());
    }

    /**
     * Display all products
     */
    public void viewInventory() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("Inventory:");
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }

    /**
     * Calculate total inventory value
     */
    public double getInventoryValue() {
        double total = 0.0;

        for (Product product : products.values()) {
            total += product.getPrice() * product.getQuantity();
        }

        return total;
    }

    /**
     * Get products with low stock
     */
    public List<Product> getLowStockProducts(int threshold) {
        List<Product> lowStock = new ArrayList<>();

        for (Product product : products.values()) {
            if (product.getQuantity() <= threshold) {
                lowStock.add(product);
            }
        }

        return lowStock;
    }

    /**
     * Display inventory statistics
     */
    public void viewStatistics() {
        System.out.println("Inventory statistics:");
        System.out.println("Total products: " + products.size());
        System.out.println("Total inventory value: $" + getInventoryValue());

        List<Product> lowStock = getLowStockProducts(5);
        if (lowStock.isEmpty()) {
            System.out.println("No low stock alerts.");
        } else {
            System.out.println("Low stock products:");
            for (Product product : lowStock) {
                System.out.println(product);
            }
        }
    }
}
