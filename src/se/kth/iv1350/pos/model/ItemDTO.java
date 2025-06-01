package se.kth.iv1350.pos.model;

/**
 * A data transfer object (DTO) for an item.
 */
public class ItemDTO {
    private final String itemID;
    private final String name;
    private final String itemDescription;
    private final double price;
    private final double vat;
    private final int quantity;

    /**
     * Creates a new ItemDTO with all properties.
     *
     * @param itemID The unique identifier for the item.
     * @param name The name of the item.
     * @param itemDescription A short description of the item.
     * @param price The price of the item.
     * @param vat The VAT for the item.
     * @param quantity The quantity of the item.
     */
    public ItemDTO(String itemID, String name, String itemDescription, double price, double vat, int quantity) {
        this.itemID = itemID;
        this.name = name;
        this.itemDescription = itemDescription;
        this.price = price;
        this.vat = vat;
        this.quantity = quantity;
    }

    public String getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public double getPrice() {
        return price;
    }

    public double getVat() {
        return vat;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns a string representation of this ItemDTO.
     *
     * @return A string containing item name, price, and quantity.
     */
    @Override
    public String toString() {
        return name + " " + quantity + " x " + price + " = " + String.format("%.2f", quantity * price) + "  SEK ";
    }
}
