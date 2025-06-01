package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Receipt;

/**
 * Represents the printer. Singleton pattern applied.
 */
public class Printer {
    private static Printer instance;

    private Printer() {}

    public static Printer getInstance() {
        if (instance == null) {
            instance = new Printer();
        }
        return instance;
    }

    /**
     * Prints the specified receipt.
     *
     * @param receipt The receipt to be printed.
     */
    public void printReceipt(Receipt receipt) {
        System.out.println(receipt.toString());
    }
}
