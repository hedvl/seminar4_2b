package se.kth.iv1350.pos.model;

/**
 * Represents a payment made by the customer.
 */
public class Payment {
    private final double amountPaid;
    private final double change;

    /**
     * Creates a new instance of Payment.
     *
     * @param amountPaid The amount paid by the customer.
     * @param change The change returned to the customer.
     */
    public Payment(double amountPaid, double change) {
        this.amountPaid = amountPaid;
        this.change = change;
    }

    /**
     * Returns the amount paid by the customer.
     *
     * @return The paid amount.
     */
    public double getAmountPaid() {
        return amountPaid;
    }

    /**
     * Returns the change given to the customer.
     *
     * @return The change amount.
     */
    public double getChangeAmount() {
        return change;
    }
}
