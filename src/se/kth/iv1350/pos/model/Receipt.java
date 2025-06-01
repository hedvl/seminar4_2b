package se.kth.iv1350.pos.model;

import java.util.Map;

/**
 * Represents the receipt for a completed sale.
 */
public class Receipt {
    private final Sale sale;
    private final double paidAmount;

    /**
     * Creates a new instance, representing the receipt for the specified sale.
     * @param sale The sale that was paid.
     * @param paidAmount The amount paid by the customer.
     */
    public Receipt(Sale sale, double paidAmount) {
        this.sale = sale;
        this.paidAmount = paidAmount;
    }

    @Override
    public String toString() {
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append("--- RECEIPT --- \n");
        receiptBuilder.append("Time of Sale: ").append(sale.getTimeOfSale()).append("\n");

        for (Map.Entry<String, Item> entry : sale.getSoldItems().entrySet()) {
            Item item = entry.getValue();
            receiptBuilder.append(String.format("%s %d x %.2f = %.2f  SEK \n",
                    item.getName(),
                    item.getQuantity(),
                    item.getPrice(),
                    item.getPrice() * item.getQuantity()));
        }

        double totalCostAfterDiscount = sale.getTotalCostAfterDiscount();
        double change = paidAmount - totalCostAfterDiscount;

        receiptBuilder.append(String.format("\nTotal Cost: %.2f SEK \n", totalCostAfterDiscount));
        receiptBuilder.append(String.format("\nTotal VAT: %.2f SEK \n", sale.getTotalVat()));
        receiptBuilder.append(String.format("\nCash: %.2f SEK\n", paidAmount));
        receiptBuilder.append(String.format("Change: %.2f SEK\n", change));
        receiptBuilder.append("--- END RECEIPT ---\n");

        return receiptBuilder.toString();
    }
}
