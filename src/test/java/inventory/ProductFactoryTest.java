package inventory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ProductFactory class.
 */
class ProductFactoryTest {

    @Test
    void testCreateValidBook() {
        Product product = ProductFactory.createProduct(
                "B001",
                "Java Book",
                ProductFactory.BOOK_TYPE,
                20.0,
                5
        );

        assertNotNull(product);
        assertEquals("B001", product.getId());
        assertEquals("Java Book", product.getName());
        assertEquals(ProductFactory.BOOK_TYPE, product.getType());
        assertEquals(20.0, product.getPrice());
        assertEquals(5, product.getQuantity());
    }

    @Test
    void testCreateValidElectronics() {
        Product product = ProductFactory.createProduct(
                "E001",
                "Laptop",
                ProductFactory.ELECTRONICS_TYPE,
                1000.0,
                3
        );

        assertNotNull(product);
        assertEquals("E001", product.getId());
        assertEquals("Laptop", product.getName());
        assertEquals(ProductFactory.ELECTRONICS_TYPE, product.getType());
        assertEquals(1000.0, product.getPrice());
        assertEquals(3, product.getQuantity());
    }

    @Test
    void testBookMinimumPriceValidation() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ProductFactory.createProduct(
                        "B002",
                        "Cheap Book",
                        ProductFactory.BOOK_TYPE,
                        4.99,
                        5
                )
        );

        assertEquals("Books must have minimum price of $5.00", exception.getMessage());
    }

    @Test
    void testElectronicsMinimumPriceValidation() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ProductFactory.createProduct(
                        "E002",
                        "Cheap Gadget",
                        ProductFactory.ELECTRONICS_TYPE,
                        9.99,
                        5
                )
        );

        assertEquals("Electronics must have minimum price of $10.00", exception.getMessage());
    }

    @Test
    void testInvalidProductType() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ProductFactory.createProduct(
                        "X001",
                        "Unknown",
                        "FOOD",
                        20.0,
                        5
                )
        );

        assertEquals("Invalid product type: FOOD", exception.getMessage());
    }

    @Test
    void testBoundaryConditions() {
        Product book = ProductFactory.createProduct(
                "B003",
                "Boundary Book",
                ProductFactory.BOOK_TYPE,
                5.00,
                1
        );

        Product electronics = ProductFactory.createProduct(
                "E003",
                "Boundary Electronics",
                ProductFactory.ELECTRONICS_TYPE,
                10.00,
                1
        );

        assertNotNull(book);
        assertNotNull(electronics);
    }
}
