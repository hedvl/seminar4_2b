package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Sale class.
 */
public class SaleTest {
    private Sale sale;

    /**
     * Initializes a fresh Sale instance before each test.
     */
    @BeforeEach
    public void setUp() {
        sale = new Sale();
    }

    /**
     * Tests that adding an item correctly stores it in the sale.
     */
    @Test
    public void testAddItemUpdatesSoldItems() {
        Item item = new Item("abc123", "BigWheel Oatmeal", "Oatmeal description", 29.90, 0.06, 1);
        sale.addItem(item);
        assertTrue(sale.getSoldItems().containsKey("abc123"), "Item ID should be in sold items.");
    }

    /**
     * Tests that total cost calculation includes VAT.
     */
    @Test
    public void testTotalCostCalculation() {
        Item item = new Item("abc123", "BigWheel Oatmeal", "Oatmeal description", 29.90, 0.06, 1);
        sale.addItem(item);
        double expectedTotal = 29.90 + (29.90 * 0.06);
        assertEquals(expectedTotal, sale.getTotalCost(), 0.01, "Total cost should include VAT.");
    }

    /**
     * Tests that the discount strategy is correctly applied to the total cost.
     */
    @Test
    public void testDiscountApplication() {
        Item item = new Item("abc123", "BigWheel Oatmeal", "Oatmeal description", 29.90, 0.06, 1);
        sale.addItem(item);
        sale.setDiscountStrategy(new FixedDiscount(5.0));
        double discountedTotal = sale.getTotalCostAfterDiscount();
        assertTrue(discountedTotal < sale.getTotalCost(), "Discounted total should be less than original.");
    }
}
