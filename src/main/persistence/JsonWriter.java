package persistence;

import model.Event;
import model.EventLog;
import model.Inventory;
import model.Item;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

//made with reference to CPSC 210 resources (JsonSerializationDemo) at UBC Vancouver

//represents a writer that writes the data from the current inventory to a file in JSON format
public class JsonWriter {

    PrintWriter writer;
    EventLog eventLog = EventLog.getInstance();

    //EFFECT: none
    public JsonWriter() {

    }

    //EFFECT: writes the data from the inventory to the currently opened file after converting it to the JSON format
    public void write(Inventory inventory) {
        JSONObject json = inventory.toJson();
        saveToFile(json.toString());
    }

    //EFFECT: saves the data in the opened file
    public void saveToFile(String json) {
        writer.print(json);
    }

    //EFFECT: opens a file to write data to
    public void openFile(String fileName) throws FileNotFoundException {
        writer = new PrintWriter(new File("./data/" + fileName));
        eventLog.logEvent(new Event("Data saved from system to file called " + fileName + "."));
    }

    //EFFECT: closes the file, typically after data has been written to it
    public void closeFile() {
        writer.close();
    }

}
