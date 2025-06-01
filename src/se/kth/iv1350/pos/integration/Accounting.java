package se.kth.iv1350.pos.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.pos.model.Payment;

/**
 * Represents the accounting system. Singleton pattern applied.
 */
public class Accounting {
    private static Accounting instance;
    private List<Payment> payments;

    private Accounting() {
        payments = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the accounting system.
     *
     * @return The singleton instance.
     */
    public static Accounting getInstance() {
        if (instance == null) {
            instance = new Accounting();
        }
        return instance;
    }

    /**
     * Updates the accounting system with the payment.
     *
     * @param payment the payment that has been made.
     */
    public void updateAccounting(Payment payment) {
        payments.add(payment);
    }
}
