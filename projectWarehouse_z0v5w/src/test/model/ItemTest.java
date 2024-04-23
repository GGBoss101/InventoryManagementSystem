package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    private Item box;

    @BeforeEach
    void setup() {
        box = new Item("B01", "Box", "Can store things");
    }

    @Test
    void testGetters() {
        assertEquals(box.getID(), "B01");
        assertEquals(box.getName(), "Box");
        assertEquals(box.getDescription(), "Can store things");
    }

    @Test
    void testSetters() {
        box.setName("something");
        box.setDescription("something different");
        assertEquals(box.getName(), "something");
        assertEquals(box.getDescription(), "something different");
    }
}
