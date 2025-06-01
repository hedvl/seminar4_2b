package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Payment;

/**
 * Represents the register in the POS system.
 */
public class Register {
    private static Register instance;

    private Register() {
        // Private constructor for singleton
    }

    /**
     * Returns the single instance of Register.
     *
     * @return The Register instance.
     */
    public static Register getInstance() {
        if (instance == null) {
            instance = new Register();
        }
        return instance;
    }

    /**
     * Registers the payment made by the customer.
     *
     * @param payment The payment to register.
     */
    public void addPayment(Payment payment) {
        // Currently no logic, placeholder method
    }
}
