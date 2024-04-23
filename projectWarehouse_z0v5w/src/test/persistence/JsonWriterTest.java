package persistence;

import model.*;
import persistence.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter();
            writer.openFile("/data/my\0illegal:fileName.json");
            writer.closeFile();
            fail("Should have thrown exception");
        } catch (IOException a) {
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
        } catch (IOException b) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testExpectedInventory() {
        try {
            Inventory i = new Inventory();
            JsonWriter writer = new JsonWriter();
            i.addItem(new Item("BB01", "Box", "Can store things"));
            i.addItem(new Item("WW01", "Wrench", "Can fix things"));
            i.addItem(new Item("BB02", "Box", "Can store things"));
            writer.openFile("expectedInventoryTest.json");
            writer.write(i);
            writer.closeFile();

            JsonReader reader = new JsonReader();
            i = reader.read("expectedInventoryTest.json");
            assertEquals(3, i.getItemsCount());
            List<Item> items = i.getItems();
            checkEquals("BB01", "Box", "Can store things", items.get(0));
            checkEquals("WW01", "Wrench", "Can fix things", items.get(1));
            checkEquals("BB02", "Box", "Can store things", items.get(2));
        } catch (IOException c) {
            fail("Exception should not have been thrown");
        }
    }
}
