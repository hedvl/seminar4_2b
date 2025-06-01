package se.kth.iv1350.pos.view;
import se.kth.iv1350.pos.model.Payment;
import se.kth.iv1350.pos.model.PaymentObserver;

/**
 * Displays the total revenue in the console using the observer pattern.
 */
public class TotalRevenueView implements PaymentObserver {

    private double totalRevenue;

    @Override
    public void newPayment(Payment payment) {
        totalRevenue += payment.getAmountPaid() - payment.getChangeAmount();
        printTotalCostsForAllSales();
    }

    private void printTotalCostsForAllSales() {
        System.out.println("Total revenue:" + String.format("%.2f", totalRevenue) + "SEK");
    }
}
