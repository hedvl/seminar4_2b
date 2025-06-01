package se.kth.iv1350.pos.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.Inventory;
import se.kth.iv1350.pos.integration.InventoryDatabaseException;
import se.kth.iv1350.pos.integration.ItemNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Controller class.
 */
public class ControllerTest {
    private Controller controller;

    /**
     * Sets up a new Controller instance and resets the inventory before each test.
     */
    @BeforeEach
    public void setUp() {
        controller = new Controller();
        Inventory.getInstance().resetInventory();
    }

    /**
     * Tests that a valid item ID can be entered without throwing an exception.
     */
    @Test
    public void testEnterValidItemDoesNotThrow() {
        controller.startSale();
        try {
            controller.enterItem("abc123");
        } catch (ItemNotFoundException | InventoryDatabaseException e) {
            fail("Exception was thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    /**
     * Tests that entering an invalid item ID throws an ItemNotFoundException.
     */
    @Test
    public void testEnterInvalidItemThrowsException() {
        controller.startSale();
        assertThrows(ItemNotFoundException.class, () -> controller.enterItem("invalid"));
    }

    /**
     * Tests that ending a sale after adding a valid item does not throw an exception.
     */
    @Test
    public void testEndSaleDoesNotThrow() {
        controller.startSale();
        try {
            controller.enterItem("abc123");
            controller.endSale();
        } catch (ItemNotFoundException | InventoryDatabaseException e) {
            fail("Exception was thrown during endSale: " + e.getMessage());
        }
    }

    /**
     * Tests that paying after a successful sale does not throw an exception.
     */
    @Test
    public void testPayDoesNotThrow() {
        controller.startSale();
        try {
            controller.enterItem("abc123");
            controller.endSale();
            controller.pay(100);
        } catch (ItemNotFoundException | InventoryDatabaseException e) {
            fail("Exception was thrown during pay: " + e.getMessage());
        }
    }
}
