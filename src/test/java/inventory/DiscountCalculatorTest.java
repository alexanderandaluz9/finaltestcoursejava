package inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountCalculatorTest {

    private Product book;
    private Product electronics;

    @BeforeEach
    void setUp() {
        book = ProductFactory.createProduct("B001", "Book", "BOOK", 20.0, 10);
        electronics = ProductFactory.createProduct("E001", "Laptop", "ELECTRONICS", 100.0, 10);
    }

    @Test
    void testStudentDiscountOnBooks() {
        var result = DiscountCalculator.calculateDiscount(book, 2, "STUDENT");

        assertEquals(4.0, result.getDiscountAmount());
        assertTrue(result.getDescription().toLowerCase().contains("student"));
    }

    @Test
    void testStudentDiscountOnElectronics() {
        var result = DiscountCalculator.calculateDiscount(electronics, 2, "STUDENT");

        assertEquals(0.0, result.getDiscountAmount());
    }

    @Test
    void testBulkDiscountValid() {
        var result = DiscountCalculator.calculateDiscount(book, 5, "BULK");

        assertEquals(15.0, result.getDiscountAmount());
    }

    @Test
    void testBulkDiscountInvalid() {
        var result = DiscountCalculator.calculateDiscount(book, 3, "BULK");

        assertEquals(0.0, result.getDiscountAmount());
    }

    @Test
    void testNoDiscount() {
        var result = DiscountCalculator.calculateDiscount(book, 3, "NONE");

        assertEquals(0.0, result.getDiscountAmount());
    }

    @Test
    void testDiscountCalculationAccuracy() {
        var bulk = DiscountCalculator.calculateDiscount(electronics, 5, "BULK");
        assertEquals(75.0, bulk.getDiscountAmount());
    }
}
