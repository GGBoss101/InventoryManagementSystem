package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory inventory;
    private Item box;
    private Item wrench;
    private Item box2;

    @BeforeEach
    void setup() {
        inventory = new Inventory();
        box = new Item("B01", "Box", "Can store things");
        wrench = new Item("W01", "Wrench", "Can fix things");
        box2 = new Item("B01", "Box", "Can store things");
        assertTrue(inventory.addItem(box));
        assertTrue(inventory.addItem(wrench));
        assertFalse(inventory.addItem(box2));
    }

    @Test
    void testAddItem() {
        List<Item> items1 = inventory.getItems();
        assertEquals(2, inventory.getItemsCount());
        assertEquals(items1.get(0), box);
        assertEquals(items1.get(1), wrench);
        assertFalse(box2.setID(inventory, "W01"));
        assertTrue(box2.setID(inventory, "B02"));
        assertEquals(box2.getID(), "B02");
        assertTrue(inventory.addItem(box2));
        List<Item> items2 = inventory.getItems();
        assertEquals(3, inventory.getItemsCount());
        assertEquals(items2.get(0), box);
        assertEquals(items2.get(1), wrench);
        assertEquals(items2.get(2), box2);
    }

    @Test
    void testRemoveOneItemInSmallInventory() {
        assertFalse(inventory.removeItem("Z01"));
        assertTrue(inventory.removeItem("B01"));
        List<Item> items = inventory.getItems();
        assertEquals(items.get(0), wrench);
        assertEquals(inventory.getItemsCount(), 1);
    }

    @Test
    void testRemoveItemsInEmptyInventory() {
        assertFalse(inventory.removeItem("Z01"));
        assertTrue(inventory.removeItem("W01"));
        List<Item> items = inventory.getItems();
        assertEquals(items.get(0), box);
        assertEquals(inventory.getItemsCount(), 1);
        assertTrue(inventory.removeItem("B01"));
        assertEquals(inventory.getItemsCount(), 0);
        assertFalse(inventory.removeItem("X10293"));
    }

    @Test
    void testRemoveAllItemsInLargeInventory() {
        assertTrue(inventory.addItem(new Item("O01", "Orb", "A glass ball")));
        assertEquals(inventory.getItemsCount(), 3);
        assertTrue(inventory.removeItem("W01"));
        assertTrue(inventory.removeItem("O01"));
        assertTrue(inventory.removeItem("B01"));
        assertEquals(inventory.getItemsCount(), 0);
    }

    @Test
    void testFindItemByID() {
        assertEquals(inventory.findItemByID("B01"), box);
        assertEquals(inventory.findItemByID("W01"), wrench);
        assertEquals(inventory.findItemByID("S01"), null);
    }

}
