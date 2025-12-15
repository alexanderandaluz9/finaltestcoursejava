package inventory;

/**
 * Strategy pattern implementation for calculating different types of discounts.
 */
public class DiscountCalculator {

    /**
     * Inner class to hold discount calculation results.
     */
    public static class DiscountResult {

        private double discountAmount;
        private String description;

        public DiscountResult(double discountAmount, String description) {
            this.discountAmount = discountAmount;
            this.description = description;
        }

        public double getDiscountAmount() {
            return discountAmount;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * Calculate discount based on discount strategy
     */
    public static DiscountResult calculateDiscount(Product product, int quantity, String discountType) {

        double discountAmount = 0.0;
        String description = "No discount applied";

        if ("STUDENT".equals(discountType)) {

            // 10% discount only for books
            if ("BOOK".equals(product.getType())) {
                discountAmount = product.getPrice() * quantity * 0.10;
                description = "Student discount applied (10% off books)";
            }

        } else if ("BULK".equals(discountType)) {

            // 15% discount if buying 5 or more items
            if (quantity >= 5) {
                discountAmount = product.getPrice() * quantity * 0.15;
                description = "Bulk purchase discount applied (15% off)";
            }

        } else if ("NONE".equals(discountType)) {
            // Explicit no discount
            discountAmount = 0.0;
            description = "No discount applied";
        }

        return new DiscountResult(discountAmount, description);
    }
}
