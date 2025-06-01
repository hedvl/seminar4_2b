package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    @Test
    public void testItemInitialization() {
        Item item = new Item("abc123", "Test", "Test Desc", 10.0, 0.06, 1);
        assertEquals("abc123", item.getItemID());
        assertEquals("Test", item.getName());
        assertEquals("Test Desc", item.getItemDescription());
        assertEquals(10.0, item.getPrice());
        assertEquals(0.06, item.getVat());
        assertEquals(1, item.getQuantity());
    }
}
