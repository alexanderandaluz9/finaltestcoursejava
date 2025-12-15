package inventory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest {

    @Test
    void testCreateValidBook() {
        Product book = ProductFactory.createProduct(
                "B001", "Java Book", "BOOK", 20.0, 5
        );

        assertNotNull(book);
        assertEquals("B001", book.getId());
        assertEquals("Java Book", book.getName());
        assertEquals("BOOK", book.getType());
        assertEquals(20.0, book.getPrice());
        assertEquals(5, book.getQuantity());
    }

    @Test
    void testCreateValidElectronics() {
        Product electronics = ProductFactory.createProduct(
                "E001", "Mouse", "ELECTRONICS", 50.0, 3
        );

        assertNotNull(electronics);
        assertEquals("ELECTRONICS", electronics.getType());
    }

    @Test
    void testBookMinimumPriceValidation() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> ProductFactory.createProduct(
                        "B002", "Cheap Book", "BOOK", 3.0, 1
                )
        );

        assertTrue(ex.getMessage().contains("5"));
    }

    @Test
    void testElectronicsMinimumPriceValidation() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> ProductFactory.createProduct(
                        "E002", "Cheap Gadget", "ELECTRONICS", 5.0, 1
                )
        );

        assertTrue(ex.getMessage().contains("10"));
    }

    @Test
    void testInvalidProductType() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ProductFactory.createProduct(
                        "X001", "Unknown", "FOOD", 20.0, 1
                )
        );
    }

    @Test
    void testBoundaryConditions() {
        assertDoesNotThrow(() ->
                ProductFactory.createProduct("B003", "Min Book", "BOOK", 5.0, 1)
        );

        assertDoesNotThrow(() ->
                ProductFactory.createProduct("E003", "Min Elec", "ELECTRONICS", 10.0, 1)
        );
    }
}
