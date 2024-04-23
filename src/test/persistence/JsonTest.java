package persistence;

import model.Item;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {

    protected void checkEquals(String id, String name, String description, Item item) {
        assertEquals(name, item.getName());
        assertEquals(id, item.getID());
        assertEquals(description, item.getDescription());
    }
}
