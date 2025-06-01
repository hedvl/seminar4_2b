package se.kth.iv1350.pos.model;

/**
 * Represents an item in the POS system.
 */
public class Item {
    private final String itemID;
    private final String name;
    private final String itemDescription;
    private final double price;
    private final double vat;
    private final int quantity;

    /**
     * Creates a new instance of an item.
     *
     * @param itemID          The unique identifier of the item.
     * @param name            The name of the item.
     * @param itemDescription The description of the item.
     * @param price           The price of the item (excluding VAT).
     * @param vat             The VAT rate for the item.
     * @param quantity        The quantity of the item.
     */
    public Item(String itemID, String name, String itemDescription, double price, double vat, int quantity) {
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
     * Converts this item into a corresponding DTO object.
     *
     * @return An ItemDTO representing this item.
     */
    public ItemDTO itemToItemDTO() {
        return new ItemDTO(itemID, name, itemDescription, price, vat, quantity);
    }
}
