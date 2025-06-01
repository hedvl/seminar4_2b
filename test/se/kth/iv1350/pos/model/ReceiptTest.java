package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {

    @Test
    public void testReceiptCreation() {
        // Arrange
        HashMap<String, Item> soldItems = new HashMap<>();
        soldItems.put("abc123", new Item("abc123", "Oatmeal", "500g Oatmeal", 29.90, 0.06, 1));

        Sale sale = new Sale();
        for (Item item : soldItems.values()) {
            sale.addItem(item);
        }

        double change = 10.0;

        // Act
        Receipt receipt = new Receipt(sale, change);

        // Assert
        assertNotNull(receipt.toString());
        assertTrue(receipt.toString().contains("RECEIPT"));
        assertTrue(receipt.toString().contains("Oatmeal"));
    }
}
