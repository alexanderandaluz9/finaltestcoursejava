package inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class InventoryManagerTest {

    private InventoryManager manager;

    @BeforeEach
    void setUp() {
        manager = new InventoryManager();
    }

    @Test
    void testAddProduct() {
        assertDoesNotThrow(() ->
                manager.addProduct("B001", "Java Book", "BOOK", 20.0, 10)
        );
    }

    @Test
    void testAddInvalidProduct() {
        assertDoesNotThrow(() ->
                manager.addProduct("B002", "Cheap Book", "BOOK", 2.0, 5)
        );

        assertEquals(0.0, manager.getInventoryValue());
    }

    @Test
    void testSellProduct() {
        manager.addProduct("B001", "Java Book", "BOOK", 20.0, 10);

        assertDoesNotThrow(() ->
                manager.sellProduct("B001", 2, "STUDENT")
        );

        assertEquals(160.0, manager.getInventoryValue());
    }

    @Test
    void testSellInsufficientStock() {
        manager.addProduct("B001", "Java Book", "BOOK", 20.0, 1);

        assertDoesNotThrow(() ->
                manager.sellProduct("B001", 5, "NONE")
        );

        assertEquals(20.0, manager.getInventoryValue());
    }

    @Test
    void testAddStock() {
        manager.addProduct("B001", "Java Book", "BOOK", 20.0, 5);
        manager.addStock("B001", 5);

        assertEquals(200.0, manager.getInventoryValue());
    }

    @Test
    void testInventoryValue() {
        manager.addProduct("B001", "Book", "BOOK", 20.0, 5);
        manager.addProduct("E001", "Mouse", "ELECTRONICS", 50.0, 2);

        assertEquals(200.0, manager.getInventoryValue());
    }

    @Test
    void testLowStockProducts() {
        manager.addProduct("B001", "Book", "BOOK", 20.0, 3);
        manager.addProduct("E001", "Mouse", "ELECTRONICS", 50.0, 10);

        List<Product> lowStock = manager.getLowStockProducts(5);
        assertEquals(1, lowStock.size());
    }

    @Test
    void testNonExistentProduct() {
        assertDoesNotThrow(() ->
                manager.sellProduct("X001", 1, "NONE")
        );

        assertDoesNotThrow(() ->
                manager.addStock("X001", 5)
        );
    }

    @Test
    void testCompleteWorkflow() {
        manager.addProduct("B001", "Book", "BOOK", 20.0, 10);
        manager.sellProduct("B001", 5, "BULK");
        manager.addStock("B001", 10);

        assertEquals(300.0, manager.getInventoryValue());
    }
}
