package persistence;

import model.*;
import persistence.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderInvalidFile() {
        try {
            JsonReader reader = new JsonReader();
            reader.read("doesNotExist.json");
            fail("Was supposed to throw an IOException");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testEmptyInventory() {
        try {
            Inventory i = new Inventory();
            JsonWriter writer = new JsonWriter();
            writer.openFile("emptyFileTest.json");
            writer.write(i);
            writer.closeFile();

            JsonReader reader = new JsonReader();
            i = reader.read("emptyFileTest.json");
            assertEquals(0, i.getItemsCount());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testExpectedInventory() {
        try {
            Inventory i = new Inventory();
            i.addItem(new Item("B01", "Box", "Can store things"));
            i.addItem(new Item("B02", "Box", "Can store things"));
            i.addItem(new Item("W01", "Wrench", "Can fix things"));
            JsonWriter writer = new JsonWriter();
            writer.openFile("expectedInventoryTest.json");
            writer.write(i);
            writer.closeFile();

            JsonReader reader = new JsonReader();
            i = reader.read("expectedInventoryTest.json");
            assertEquals(3, i.getItemsCount());
            List<Item> items = i.getItems();
            checkEquals("B01", "Box", "Can store things", items.get(0));
            checkEquals("B02", "Box", "Can store things", items.get(1));
            checkEquals("W01", "Wrench", "Can fix things", items.get(2));
        } catch (IOException a) {
            fail("Exception should not have been thrown");
        }
    }
}
