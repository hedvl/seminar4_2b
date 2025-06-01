package se.kth.iv1350.pos.model;

/**
 * A discount strategy that applies no discount.
 */
public class NoDiscount implements DiscountStrategy {
    /**
     * Returns zero discount.
     *
     * @param amount The original amount.
     * @return 0.0, indicating no discount.
     */
    public double calculateDiscount(double amount) {
        return 0;
    }
}
