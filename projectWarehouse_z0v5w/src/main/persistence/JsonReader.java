package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.*;
import org.json.*;

//made with reference to CPSC 210 resources (JsonSerializationDemo) at UBC Vancouver

//represents a reader that reads and converts the data written in JSON format from a file
//to the current inventory in the program
public class JsonReader {

    EventLog eventLog = EventLog.getInstance();

    //EFFECT: none
    public JsonReader() {

    }

    //reads the file to acquire the inventory stored on the file
    //and returns it
    public Inventory read(String fileName) throws IOException {
        String data = readFile("./data/" + fileName);
        JSONObject jsonObject = new JSONObject(data);
        eventLog.logEvent(new Event("Data loaded to system from file named " + fileName + "."));
        return parseInventory(jsonObject);
    }

    // EFFECT: parses inventory from JSON object and returns it
    private Inventory parseInventory(JSONObject jsonObject) {
//        String name = jsonObject.getString("name");
        Inventory i = new Inventory();
        addItems(i, jsonObject);
        return i;
    }

    //EFFECT: parses items and adds them to the inventory
    public void addItems(Inventory inventory, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Inventory");
        for (Object json : jsonArray) {
            JSONObject nextJsonObject = (JSONObject) json;
            addItem(inventory, nextJsonObject);
        }
    }

    //EFFECT: parses an item and add it to the inventory
    public void addItem(Inventory inventory, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String id = jsonObject.getString("ID");
        String description = jsonObject.getString("description");
        Item item = new Item(id, name, description);
        inventory.addItem(item);
    }

    // EFFECT: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }
}