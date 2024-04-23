package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//represents the inventory for the warehouse

public class Inventory {

    ArrayList<Item> items;
    EventLog eventLog;

    //EFFECT: creates an empty array list of items
    public Inventory() {
        this.items = new ArrayList<>();
        this.eventLog = EventLog.getInstance();
    }

    //MODIFIES: this
    //EFFECT: adds an item to the inventory and return true if successfully added,
    //and false if it was not added because an item with the same id already exists
    public boolean addItem(Item item) {
        Item i = findItemByID(item.getID());
        if (i != null) {
            return false;
        } else {
            this.items.add(item);
            eventLog.logEvent(new Event(item.getName() + " successfully added to inventory."));
            return true;
        }
    }

    //MODIFIES: this
    //EFFECT: attempts to remove an item from the inventory and returns true if
    //the item is successfully removed and false if not.
    public boolean removeItem(String id) {
        Item itemToRemove = findItemByID(id);
        if (itemToRemove != null) {
            for (int i = 0; i < this.getItemsCount(); i++) {
                if (this.items.get(i).equals(itemToRemove)) {
                    this.items.remove(i);
                    eventLog.logEvent(new Event(itemToRemove.getName() + " successfully removed from inventory."));
                    return true;
                }
            }
        }
        return false;
    }

    //EFFECT: Searches for the item with the given ID and returns it
    //if found, and null otherwise
    public Item findItemByID(String id) {
        for (Item i : this.items) {
            if (i.getID().equals(id)) {
                return i;
            }
        }
        return null;
    }

    //EFFECT: returns the inventory as a JSON object (containing a JSON array
    //of all its items)
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Inventory", createJsonArray());
        return json;
    }

    //EFFECT: converts the array list of items currently in the inventory
    //as a JSON array and returns it.
    public JSONArray createJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (Item item : this.items) {
            jsonArray.put(item.toJson());
        }
        return jsonArray;
    }

    //EFFECT: returns the items currently stored in the inventory
    //as an array list
    public List<Item> getItems() {
        return this.items;
    }

    public int getItemsCount() {
        return items.size();
    }
}
