package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.Inventory;
import se.kth.iv1350.pos.integration.InventoryDatabaseException;
import se.kth.iv1350.pos.integration.ItemNotFoundException;
import se.kth.iv1350.pos.model.*;

public class View {
    private final Controller contr;

    public View(Controller contr) {
        this.contr = contr;
    }

    public void runFakeExecution() {
        try {
            contr.enterItem("failDB");
        } catch (ItemNotFoundException | InventoryDatabaseException e) {
            System.out.println("Could not reach the inventory system. Please try again later.");
        }

        runSaleWithStrategy(new NoDiscount(), "🟢 NO DISCOUNT");
        runSaleWithStrategy(new FixedDiscount(15), "🟡 FIXED DISCOUNT (15 SEK)");
        runSaleWithStrategy(new PercentageDiscount(0.10), "🔵 PERCENTAGE DISCOUNT (10%)");
    }

    private void runSaleWithStrategy(DiscountStrategy strategy, String label) {
        System.out.println("\n========== " + label + " ==========");

        Inventory.getInstance().resetInventory();
        contr.startSale();

        // 🔥 Add observers after sale is initialized
        contr.addPaymentObserver(new TotalRevenueView());
        contr.addPaymentObserver(new TotalRevenueFileOutput());

        contr.setDiscountStrategy(strategy);

        try {
            contr.enterItem("abc123");
            contr.enterItem("def456");
        } catch (ItemNotFoundException | InventoryDatabaseException e) {
            System.out.println("The item was not found. Please check the item ID.");
        }

        if (label.equals("🟢 NO DISCOUNT")) {
            try {
                contr.enterItem("invalidID");
            } catch (ItemNotFoundException | InventoryDatabaseException e) {
                System.out.println("The item was not found. Please check the item ID.");
            }
        }

        contr.endSale();
        Payment payment = contr.pay(100.0);
        System.out.println("Change to customer: " + payment.getChangeAmount() + " SEK");
    }
}
