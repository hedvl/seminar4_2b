package se.kth.iv1350.pos.model;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a sale transaction, handling items, totals, VAT, discounts, and payment observers.
 */
public class Sale {
    private final Map<String, Item> soldItems = new HashMap<>();
    private double runningTotal = 0;
    private double totalVAT = 0;
    private DiscountStrategy discountStrategy = new NoDiscount(); // Default strategy

    private final List<PaymentObserver> paymentObservers = new ArrayList<>();

    /**
     * Adds an item to the current sale and updates totals.
     *
     * @param item The item to add.
     */
    public void addItem(Item item) {
        String itemID = item.getItemID();
        if (soldItems.containsKey(itemID)) {
            Item existing = soldItems.get(itemID);
            int newQuantity = existing.getQuantity() + item.getQuantity();
            Item updatedItem = new Item(
                    itemID,
                    existing.getName(),
                    existing.getItemDescription(),
                    existing.getPrice(),
                    existing.getVat(),
                    newQuantity
            );
            soldItems.put(itemID, updatedItem);
        } else {
            soldItems.put(itemID, item);
        }

        runningTotal += item.getPrice() * item.getQuantity();
        totalVAT += item.getPrice() * item.getQuantity() * item.getVat();
    }

    /**
     * @return Total cost including VAT.
     */
    public double getTotalCost() {
        return runningTotal + totalVAT;
    }

    /**
     * @return Total VAT amount.
     */
    public double getTotalVat() {
        return totalVAT;
    }

    /**
     * @return Map of sold items.
     */
    public Map<String, Item> getSoldItems() {
        return soldItems;
    }

    /**
     * @return SaleDTO representing current sale.
     */
    public SaleDTO getSaleDTO() {
        return new SaleDTO(
                getTotalCost(),
                getTotalVat(),
                convertToItemDTOMap(soldItems),
                getTimeOfSale()
        );
    }

    /**
     * Calculates final total after applying discount strategy.
     *
     * @return Final total after discount.
     */
    public double getTotalCostAfterDiscount() {
        double discount = discountStrategy.calculateDiscount(getTotalCost());
        return getTotalCost() - discount;
    }

    /**
     * Sets the strategy for discount application.
     *
     * @param strategy DiscountStrategy to use.
     */
    public void setDiscountStrategy(DiscountStrategy strategy) {
        this.discountStrategy = strategy;
    }

    private Map<String, ItemDTO> convertToItemDTOMap(Map<String, Item> items) {
        Map<String, ItemDTO> dtoMap = new HashMap<>();
        for (Map.Entry<String, Item> entry : items.entrySet()) {
            Item item = entry.getValue();
            dtoMap.put(entry.getKey(), item.itemToItemDTO());
        }
        return dtoMap;
    }

    private String getCurrentTime() {
        return java.time.LocalTime.now().withNano(0).toString();
    }

    /**
     * @return Time of sale as a string.
     */
    public String getTimeOfSale() {
        return getCurrentTime();
    }

    /**
     * Checks if sale contains any valid items.
     *
     * @return True if there are items, false otherwise.
     */
    public boolean hasValidItems() {
        return !soldItems.isEmpty();
    }

    /**
     * Ends the sale. Placeholder for future logic.
     */
    public void endSale() {
        // Reserved for future logic
    }

    /**
     * Registers an observer that should be notified when a payment is made.
     *
     * @param observer The observer to notify.
     */
    public void addPaymentObserver(PaymentObserver observer) {
        paymentObservers.add(observer);
    }

    private void notifyObservers(Payment payment) {
        for (PaymentObserver observer : paymentObservers) {
            observer.newPayment(payment);
        }
    }

    /**
     * Processes payment and notifies all observers.
     *
     * @param payment The payment that was made.
     */
    public void pay(Payment payment) {
        notifyObservers(payment);
    }
}
