package se.kth.iv1350.pos.model;

/**
 * A discount strategy that subtracts a percentage from the total amount.
 */
public class PercentageDiscount implements DiscountStrategy {
    private final double percentage;

    /**
     * Creates a new instance with the specified discount percentage.
     *
     * @param percentage The percentage to apply (e.g., 0.1 for 10%).
     */
    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }

    /**
     * Calculates the discount based on the percentage.
     *
     * @param amount The original amount.
     * @return The calculated discount.
     */
    public double calculateDiscount(double amount) {
        return amount * percentage;
    }
}
