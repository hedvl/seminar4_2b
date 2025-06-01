package se.kth.iv1350.pos.model;

/**
 * An interface representing a discount strategy.
 * Implementations define how discounts are calculated for a given amount.
 */
public interface DiscountStrategy {
    /**
     * Calculates the discount for a given amount.
     *
     * @param amount The original total amount before discount.
     * @return The amount to subtract as a discount.
     */
    double calculateDiscount(double amount);
}

