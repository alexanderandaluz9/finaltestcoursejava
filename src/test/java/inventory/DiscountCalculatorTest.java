package inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountCalculatorTest {

    private Product book;
    private Product electronics;

    @BeforeEach
    void setUp() {
        book = ProductFactory.createProduct(
                "B001",
                "Test Book",
                "BOOK",
                20.0,
                10
        );

        electronics = ProductFactory.createProduct(
                "E001",
                "Test Electronics",
                "ELECTRONICS",
                100.0,
                10
        );
    }

    @Test
    void testStudentDiscountOnBooks() {
        DiscountCalculator.DiscountResult result =
                DiscountCalculator.calculateDiscount(book, 2, "STUDENT");

        assertEquals(4.0, result.getDiscountAmount());
        assertTrue(result.getDescription().toLowerCase().contains("student"));
    }

    @Test
    void testStudentDiscountOnElectronics() {
        DiscountCalculator.DiscountResult result =
                DiscountCalculator.calculateDiscount(electronics, 2, "STUDENT");

        assertEquals(0.0, result.getDiscountAmount());
        assertTrue(result.getDescription().toLowerCase().contains("not applicable"));
    }

    @Test
    void testBulkDiscountValid() {
        DiscountCalculator.DiscountResult bookResult =
                DiscountCalculator.calculateDiscount(book, 5, "BULK");

        DiscountCalculator.DiscountResult electronicsResult =
                DiscountCalculator.calculateDiscount(electronics, 5, "BULK");

        assertEquals(15.0, bookResult.getDiscountAmount());          // 20 * 5 * 15%
        assertEquals(75.0, electronicsResult.getDiscountAmount());  // 100 * 5 * 15%
    }

    @Test
    void testBulkDiscountInvalid() {
        DiscountCalculator.DiscountResult result =
                DiscountCalculator.calculateDiscount(book, 3, "BULK");

        assertEquals(0.0, result.getDiscountAmount());
        assertTrue(result.getDescription().toLowerCase().contains("5"));
    }

    @Test
    void testNoDiscount() {
        DiscountCalculator.DiscountResult result =
                DiscountCalculator.calculateDiscount(book, 3, "NONE");

        assertEquals(0.0, result.getDiscountAmount());
        assertTrue(result.getDescription().toLowerCase().contains("no discount"));
    }

    @Test
    void testBoundaryConditions() {
        DiscountCalculator.DiscountResult exactBulk =
                DiscountCalculator.calculateDiscount(book, 5, "BULK");

        DiscountCalculator.DiscountResult singleItem =
                DiscountCalculator.calculateDiscount(book, 1, "BULK");

        DiscountCalculator.DiscountResult largeQty =
                DiscountCalculator.calculateDiscount(book, 100, "BULK");

        assertEquals(15.0, exactBulk.getDiscountAmount());
        assertEquals(0.0, singleItem.getDiscountAmount());
        assertEquals(300.0, largeQty.getDiscountAmount()); // 20 * 100 * 15%
    }

    @Test
    void testDiscountCalculationAccuracy() {
        DiscountCalculator.DiscountResult student =
                DiscountCalculator.calculateDiscount(book, 2, "STUDENT");

        DiscountCalculator.DiscountResult bulk =
                DiscountCalculator.calculateDiscount(electronics, 5, "BULK");

        assertEquals(4.0, student.getDiscountAmount());
        assertEquals(75.0, bulk.getDiscountAmount());
    }
}
