package se.kth.iv1350.pos.model;

/**
 * A discount strategy that subtracts a fixed amount from the total.
 */
public class FixedDiscount implements DiscountStrategy {
    private final double discountAmount;

    /**
     * Creates a new instance with a specified fixed discount.
     *
     * @param discountAmount The fixed discount amount to apply.
     */
    public FixedDiscount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * Returns the fixed discount, capped by the original amount.
     *
     * @param amount The original amount.
     * @return The discount to subtract.
     */
    public double calculateDiscount(double amount) {
        return Math.min(discountAmount, amount);
    }
}
