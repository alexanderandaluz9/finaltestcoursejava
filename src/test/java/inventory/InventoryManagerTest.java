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
        Product book = ProductFactory.createProduct(
                "B001",
                "Java Book",
                ProductFactory.BOOK_TYPE,
                20.0,
                10
        );

        manager.addProduct(book);

        assertEquals(1, manager.getProducts().size());
        assertEquals(book, manager.getProductById("B001"));
    }

    @Test
    void testAddInvalidProduct() {
        Product invalidBook = ProductFactory.createProduct(
                "B002",
                "Cheap Book",
                ProductFactory.BOOK_TYPE,
                5.0,
                5
        );

        manager.addProduct(invalidBook);

        assertEquals(1, manager.getProducts().size());
    }

    @Test
    void testSellProduct() {
        Product electronics = ProductFactory.createProduct(
                "E001",
                "Laptop",
                ProductFactory.ELECTRONICS_TYPE,
                1000.0,
                5
        );

        manager.addProduct(electronics);

        boolean result = manager.sellProduct("E001", 2, DiscountType.NONE);

        assertTrue(result);
        assertEquals(3, electronics.getQuantity());
    }

    @Test
    void testSellInsufficientStock() {
        Product book = ProductFactory.createProduct(
                "B003",
                "Algorithms",
                ProductFactory.BOOK_TYPE,
                30.0,
                2
        );

        manager.addProduct(book);

        boolean result = manager.sellProduct("B003", 5, DiscountType.NONE);

        assertFalse(result);
        assertEquals(2, book.getQuantity());
    }

    @Test
    void testAddStock() {
        Product book = ProductFactory.createProduct(
                "B004",
                "Data Structures",
                ProductFactory.BOOK_TYPE,
                25.0,
                3
        );

        manager.addProduct(book);

        boolean result = manager.addStock("B004", 5);

        assertTrue(result);
        assertEquals(8, book.getQuantity());
    }

    @Test
    void testInventoryValue() {
        Product book = ProductFactory.createProduct(
                "B005",
                "Clean Code",
                ProductFactory.BOOK_TYPE,
                40.0,
                2
        );

        Product electronics = ProductFactory.createProduct(
                "E002",
                "Mouse",
                ProductFactory.ELECTRONICS_TYPE,
                20.0,
                3
        );

        manager.addProduct(book);
        manager.addProduct(electronics);

        double expectedValue = (40.0 * 2) + (20.0 * 3);

        assertEquals(expectedValue, manager.getInventoryValue());
    }

    @Test
    void testLowStockProducts() {
        Product book1 = ProductFactory.createProduct(
                "B006",
                "Refactoring",
                ProductFactory.BOOK_TYPE,
                45.0,
                1
        );

        Product book2 = ProductFactory.createProduct(
                "B007",
                "Design Patterns",
                ProductFactory.BOOK_TYPE,
                50.0,
                10
        );

        manager.addProduct(book1);
        manager.addProduct(book2);

        List<Product> lowStock = manager.getLowStockProducts(3);

        assertEquals(1, lowStock.size());
        assertEquals("B006", lowStock.get(0).getId());
    }

    @Test
    void testNonExistentProduct() {
        boolean sellResult = manager.sellProduct("X999", 1, DiscountType.NONE);
        boolean stockResult = manager.addStock("X999", 5);

        assertFalse(sellResult);
        assertFalse(stockResult);
    }

    @Test
    void testCompleteWorkflow() {
        Product book = ProductFactory.createProduct(
                "B008",
                "Testing Java",
                ProductFactory.BOOK_TYPE,
                35.0,
                10
        );

        manager.addProduct(book);

        assertTrue(manager.sellProduct("B008", 3, DiscountType.SEASONAL));
        assertEquals(7, book.getQuantity());

        assertTrue(manager.addStock("B008", 5));
        assertEquals(12, book.getQuantity());

        assertEquals(35.0 * 12, manager.getInventoryValue());
    }
}
