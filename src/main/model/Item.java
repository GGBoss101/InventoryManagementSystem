package model;

import org.json.JSONObject;

//represents an item which can be stored in the warehouse inventory
public class Item {

    private String id;
    private String name;
    private String description;
    EventLog eventLog;

    //EFFECT: creates an items with an ID, name, and description
    public Item(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.eventLog = EventLog.getInstance();
    }

    //MODIFIES: this
    //EFFECT: changes the description of the item
    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        this.eventLog.logEvent(new Event("Item description changed from '"
                + oldDescription + "' to '" + description + "'."));
    }

    //MODIFIES: this
    //EFFECT: changes the name of the item
    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        this.eventLog.logEvent(new Event("Item name changed from " + oldName + " to " + name + "."));
    }

    //MODIFIES: this
    //EFFECT: sets the ID of the item and returns true or false if it was set
    //successfully or not respectively.
    public boolean setID(Inventory inventory, String id) {
        Item item = inventory.findItemByID(id);
        if (item != null) {
            return false;
        } else {
            String oldId = this.id;
            this.id = id;
            this.eventLog.logEvent(new Event("Item ID changed from " + oldId + " to " + id + "."));
            return true;
        }
    }

    //EFFECT: converts this item to JSON format, as a JSON object, and returns it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("ID", this.id);
        json.put("description", this.description);
        return json;
    }

    public String getName() {
        return this.name;
    }

    public String getID() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }
}
