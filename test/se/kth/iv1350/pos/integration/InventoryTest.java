package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.model.Item;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Inventory class.
 */
public class InventoryTest {
    private Inventory inventory;

    /**
     * Initializes and resets inventory before each test.
     */
    @BeforeEach
    public void setUp() {
        inventory = Inventory.getInstance();
        inventory.resetInventory();
    }

    /**
     * Tests that fetching a valid item ID returns a non-null item with correct ID.
     */
    @Test
    public void testFetchValidItem() throws Exception {
        Item item = inventory.fetchItem("abc123");
        assertNotNull(item, "Item should not be null.");
        assertEquals("abc123", item.getItemID(), "Item ID should match the requested one.");
    }

    /**
     * Tests that fetching an invalid item ID throws an ItemNotFoundException.
     */
    @Test
    public void testFetchInvalidItemThrowsException() {
        assertThrows(ItemNotFoundException.class, () -> inventory.fetchItem("invalidID"),
                "Should throw ItemNotFoundException for unknown ID.");
    }

    /**
     * Tests that fetching an item with "failDB" triggers a simulated InventoryDatabaseException.
     */
    @Test
    public void testDatabaseFailureThrowsException() {
        assertThrows(InventoryDatabaseException.class, () -> inventory.fetchItem("failDB"),
                "Should throw InventoryDatabaseException for simulated DB failure.");
    }
}
