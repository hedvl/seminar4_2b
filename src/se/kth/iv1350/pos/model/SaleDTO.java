package se.kth.iv1350.pos.model;

import java.util.Map;

/**
 * A Data Transfer Object (DTO) representing a completed sale.
 */
public class SaleDTO {
    private final double totalCost;
    private final double totalVAT;
    private final Map<String, ItemDTO> soldItems;
    private final String timeOfSale;

    /**
     * Creates a new instance representing a completed sale.
     *
     * @param totalCost   The total cost of the sale including VAT.
     * @param totalVAT    The total value-added tax.
     * @param soldItems   The list of items sold during the sale.
     * @param timeOfSale  The timestamp of when the sale occurred.
     */
    public SaleDTO(double totalCost, double totalVAT, Map<String, ItemDTO> soldItems, String timeOfSale) {
        this.totalCost = totalCost;
        this.totalVAT = totalVAT;
        this.soldItems = soldItems;
        this.timeOfSale = timeOfSale;
    }

    /**
     * @return The total cost of the sale including VAT.
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * @return The total VAT amount.
     */
    public double getTotalVAT() {
        return totalVAT;
    }

    /**
     * @return The map of sold items with item IDs as keys and item details as values.
     */
    public Map<String, ItemDTO> getSoldItems() {
        return soldItems;
    }

    /**
     * @return The time of the sale.
     */
    public String getTimeOfSale() {
        return timeOfSale;
    }
}
