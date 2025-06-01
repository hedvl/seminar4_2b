package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.*;

public class Controller {
    private Sale sale;
    private final Inventory inventory;
    private final Register register;
    private final Accounting accounting;
    private final Printer printer;

    public Controller() {
        this.inventory = Inventory.getInstance();
        this.register = Register.getInstance();
        this.accounting = Accounting.getInstance();
        this.printer = Printer.getInstance();
    }

    public void startSale() {
        this.sale = new Sale();
    }

    public ItemDTO enterItem(String itemID) throws ItemNotFoundException, InventoryDatabaseException {
        Item item = inventory.fetchItem(itemID);
        sale.addItem(item);
        return item.itemToItemDTO();
    }

    public SaleDTO endSale() {
        sale.endSale();
        return sale.getSaleDTO();
    }

    public Payment pay(double paidAmount) {
        Payment payment = new Payment(paidAmount, sale.getTotalCostAfterDiscount());
        sale.pay(payment);
        register.addPayment(payment);
        accounting.updateAccounting(payment);
        printer.printReceipt(new Receipt(sale, paidAmount));
        return payment;
    }

    public void setDiscountStrategy(DiscountStrategy strategy) {
        sale.setDiscountStrategy(strategy);
    }

    public void addPaymentObserver(PaymentObserver observer) {
        sale.addPaymentObserver(observer);
    }
}
