package inventory;

/**
 * Factory class for creating Product objects with business rules.
 */
public class ProductFactory {

    public static final String BOOK_TYPE = "BOOK";
    public static final String ELECTRONICS_TYPE = "ELECTRONICS";

    public static Product createProduct(String id, String name, String type, double price, int quantity) {

        // Step 1: Validate product type
        if (!BOOK_TYPE.equals(type) && !ELECTRONICS_TYPE.equals(type)) {
            throw new IllegalArgumentException("Invalid product type: " + type);
        }

        // Step 2: Apply business rules based on product type
        if (BOOK_TYPE.equals(type) && price < 5.0) {
            throw new IllegalArgumentException("Books must have minimum price of $5.00");
        }

        if (ELECTRONICS_TYPE.equals(type) && price < 10.0) {
            throw new IllegalArgumentException("Electronics must have minimum price of $10.00");
        }

        // Step 3: Create and return Product
        return new Product(id, name, type, price, quantity);
    }
}
