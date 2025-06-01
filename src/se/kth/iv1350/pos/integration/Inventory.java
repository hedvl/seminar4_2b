package se.kth.iv1350.pos.integration;

import java.util.HashMap;
import java.util.Map;
import se.kth.iv1350.pos.model.Item;
import se.kth.iv1350.pos.model.Sale;

/**
 * Represents the inventory system. Singleton pattern applied.
 */
public class Inventory {
    private static Inventory instance;
    private final Map<String, Item> items = new HashMap<>();

    private Inventory() {
        initializeInventory();
    }

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    private void initializeInventory() {
        items.clear();
        items.put("abc123", new Item("abc123", "BigWheel Oatmeal",
                "BigWheel Oatmeal 500ml, whole grain oats, high fiber, gluten free",
                29.90, 0.06, 1));

        items.put("def456", new Item("def456", "YouGOGo Blueberry",
                "YouGoGo Blueberry 240g, low sugar, blueberry flavor yoghurt",
                14.90, 0.06, 1));
    }

    public void resetInventory() {
        initializeInventory();
    }

    public Item fetchItem(String itemID) throws ItemNotFoundException, InventoryDatabaseException {
        if ("failDB".equals(itemID)) {
            throw new InventoryDatabaseException("Simulated database failure.");
        }

        Item item = items.get(itemID);
        if (item == null) {
            throw new ItemNotFoundException(itemID);
        }

        return new Item(
                item.getItemID(),
                item.getName(),
                item.getItemDescription(),
                item.getPrice(),
                item.getVat(),
                item.getQuantity()
        );
    }

    public Map<String, Item> getItems() {
        return new HashMap<>(items);
    }

    public void updateInventory(Sale sale) {
        for (Map.Entry<String, Item> soldEntry : sale.getSoldItems().entrySet()) {
            String itemID = soldEntry.getKey();
            Item soldItem = soldEntry.getValue();
            Item inventoryItem = items.get(itemID);

            if (inventoryItem != null) {
                int newQuantity = inventoryItem.getQuantity() - soldItem.getQuantity();
                if (newQuantity <= 0) {
                    items.remove(itemID);
                } else {
                    Item updated = new Item(
                            inventoryItem.getItemID(),
                            inventoryItem.getName(),
                            inventoryItem.getItemDescription(),
                            inventoryItem.getPrice(),
                            inventoryItem.getVat(),
                            newQuantity
                    );
                    items.put(itemID, updated);
                }
            }
        }
    }
}
