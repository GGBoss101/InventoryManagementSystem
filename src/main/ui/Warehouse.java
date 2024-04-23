package ui;

import model.*;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

//represents the warehouse
public class Warehouse {

    Inventory inventory;
    Scanner scanner;

    //EFFECT: creates a new empty inventory, a new scanner for input, and opens up the menu.
    public Warehouse() {
        inventory = new Inventory();
        scanner = new Scanner(System.in);
        menu();
    }

    //MODIFIES: this
    //EFFECT: outputs the menu and takes the user input, after which it
    //calls the function to handle the user input, and iterates infinitely.
    public void menu() {
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("Note: Enter ! to return to the menu at any point of time");
            System.out.println("1. Display the inventory (with IDs)");
            System.out.println("2. Display the inventory summary (without IDs)");
            System.out.println("3. Add an item to the inventory");
            System.out.println("4. Remove an item from the inventory (identifying with ID)");
            System.out.println("5. Change an item's information (identifying with ID)");
            System.out.println("6. Load a pre-existing inventory from a file");
            System.out.println("7. Save current warehouse data to a file");
            System.out.println("8. Quit application");
            String choice = scanner.nextLine();
            checkMenu(choice);
            choiceHandler(Integer.parseInt(choice));
        }
    }

    //MODIFIES: this
    //EFFECT: handles the choice entered by the user on the menu screen by
    //carrying out the appropriate action.
    public void choiceHandler(int choice) {
        if (choice == 1) {
            displayInventory(false);
        } else if (choice == 2) {
            displayInventory(true);
        } else if (choice == 3) {
            addItem();
        } else if (choice == 4) {
            removeItem();
        } else if (choice == 5) {
            changeInfo();
        } else if (choice == 6) {
            loadInventory();
        } else if (choice == 7) {
            saveInventory();
        } else if (choice == 8) {
            scanner.close();
            System.exit(0);
        } else {
            System.out.println("Please enter a valid choice.");
        }
    }

    //EFFECT: load a pre-existing inventory from a file into the program
    public void loadInventory() {
        System.out.println("Please enter the name of the file you would like to load the data from:");
        System.out.println("(Note: it must be a file stored in the data folder)");
        JsonReader jsonReader = new JsonReader();
        String fileName = scanner.nextLine();
        checkMenu(fileName);
        boolean fileFound = false;
        while (!fileFound) {
            try {
                this.inventory = jsonReader.read(fileName);
                fileFound = true;
            } catch (IOException e) {
                System.out.println("This file does not exist, please re-enter the file name:");
                fileName = scanner.nextLine();
                checkMenu(fileName);
            }
        }
        System.out.println("The inventory has been successfully load from the file!");
    }

    //EFFECT: saves the current status of the warehouse inventory
    //as a file.
    public void saveInventory() {
        JsonWriter jsonWriter = new JsonWriter();
        System.out.println("Please enter the name of the file you would like to store the data in:");
        System.out.println("(Note: it must be a file stored in the data folder)");
        String fileName = scanner.nextLine();
        checkMenu(fileName);
        boolean fileFound = false;
        while (!fileFound) {
            try {
                jsonWriter.openFile(fileName);
                fileFound = true;
            } catch (FileNotFoundException e) {
                System.out.println("This file does not exist, please re-enter the file name:");
                fileName = scanner.nextLine();
                checkMenu(fileName);
            }
        }
        jsonWriter.write(inventory);
        jsonWriter.closeFile();
        System.out.println("Data has been successfully stored to the file!");
    }

    //EFFECT: Displays the inventory's contents.
    public void displayInventory(boolean isSummary) {
        List<Item> items = inventory.getItems();
        if (items.size() == 0) {
            System.out.println("The inventory is currently empty.");
            return;
        }
        if (!isSummary) {
            for (Item i : items) {
                String out = "Name: " + i.getName() + ", ID: " + i.getID();
                out = out + ", Description: " + i.getDescription();
                System.out.println(out);
            }
        } else {
            displayInventorySummary(items);
        }
    }

    //EFFECT: displays the summary of the inventory (without IDs and descriptions)
    public void displayInventorySummary(List<Item> items) {
        Map<String, Integer> counts = new HashMap<>();
        for (Item i : items) {
            List<String> names = new ArrayList<>(counts.keySet());
            if (!names.contains(i.getName())) {
                counts.put(i.getName(), 1);
            } else {
                int count = counts.get(i.getName()) + 1;
                counts.put(i.getName(), count);
            }
        }
        List<String> keys = new ArrayList<>(counts.keySet());
        for (String key : keys) {
            String out = key + " x " + counts.get(key);
            System.out.println(out);
        }
    }

    //MODIFIES: this
    //EFFECT: adds an item to the inventory if possible, and provides
    //the relevant explanation
    public void addItem() {
        System.out.println("Please enter the name of the item you would like to add:");
        String name = scanner.nextLine();
        checkMenu(name);
        System.out.println("Please enter the ID of the item you would like to add:");
        String id = scanner.nextLine();
        checkMenu(id);
        while (inventory.findItemByID(id) != null) {
            System.out.println("ERROR: An item with this ID already exists.");
            System.out.println("Please re-enter the ID of the item you would like to add:");
            id = scanner.nextLine();
            checkMenu(id);
        }
        System.out.println("Please enter the description of the item you would like to add:");
        String description = scanner.nextLine();
        checkMenu(description);
        boolean added = inventory.addItem(new Item(id, name, description));
        if (!added) {
            System.out.println("ERROR: The item was not added to the inventory.");
        } else {
            System.out.println("The item was successfully added to the inventory!");
        }
    }

    //MODIFIES: this
    //EFFECT: removes an item from the inventory if possible, and provides
    //the relevant explanation
    public void removeItem() {
        if (inventory.getItemsCount() == 0) {
            System.out.println("There are no items to remove.");
            return;
        }
        System.out.println("Please enter the ID of the item you would like to remove from the inventory:");
        String id = scanner.nextLine();
        checkMenu(id);
        boolean isRemoved = inventory.removeItem(id);
        while (!isRemoved) {
            System.out.println("No item with this ID exists.");
            System.out.println("Please re-enter the ID of the item you would like to remove:");
            id = scanner.nextLine();
            checkMenu(id);
            isRemoved = inventory.removeItem(id);
        }
        System.out.println("Item has been successfully removed!");
    }

    //MODIFIES: this
    //EFFECT: changes an item's information based on the user's preference
    public void changeInfo() {
        if (this.inventory.getItemsCount() == 0) {
            System.out.println("There are no items currently in the inventory.");
            return;
        }
        System.out.println("Please enter the ID of the item whose information you would like to change.");
        String id = scanner.nextLine();
        checkMenu(id);
        Item selectedItem = inventory.findItemByID(id);
        while (selectedItem == null) {
            System.out.println("ERROR: No item with this ID exists.");
            System.out.println("Please re-enter the ID.");
            id = scanner.nextLine();
            checkMenu(id);
            selectedItem = inventory.findItemByID(id);
        }
        System.out.println("Please choose which of the following item information to change:");
        System.out.println("1. Name");
        System.out.println("2. ID");
        System.out.println("3. Description");
        String c = scanner.nextLine();
        changeInfoChoiceHandler(selectedItem, c);
    }

    //MODIFIES: this
    //EFFECT: changes the property of an item according to the choice in the parameter
    public void changeInfoChoiceHandler(Item selectedItem, String c) {
        if (c.equals("1")) {
            changeName(selectedItem);
        } else if (c.equals("2")) {
            changeID(selectedItem);
        } else if (c.equals("3")) {
            changeDescription(selectedItem);
        }
    }

    //MODIFIES: this
    //EFFECT: changes the name of a selected item in the inventory
    public void changeName(Item selectedItem) {
        System.out.println("Please enter the new name of the item:");
        String name = scanner.nextLine();
        checkMenu(name);
        selectedItem.setName(name);
        System.out.println("Successfully change the name of the item!");
    }

    //MODIFIES: this
    //EFFECT: changes the ID of a selected item in the inventory
    public void changeID(Item selectedItem) {
        System.out.println("Please enter the new ID of the item:");
        String id2 = scanner.nextLine();
        checkMenu(id2);
        while (inventory.findItemByID(id2) != null) {
            System.out.println("ERROR: An item with this ID already exists.");
            System.out.println("Please re-enter the new ID of the item:");
            id2 = scanner.nextLine();
            checkMenu(id2);
        }
        selectedItem.setID(inventory, id2);
        System.out.println("Successfully change the ID of the item!");
    }

    //MODIFIES: this
    //EFFECT: changes the description of the selected item in the inventory
    public void changeDescription(Item selectedItem) {
        System.out.println("Please enter the new description of the item:");
        String description = scanner.nextLine();
        checkMenu(description);
        selectedItem.setDescription(description);
        System.out.println("Successfully change the description of the item!");
    }

    //EFFECT: checks if the user wants to go back to the main menu, and
    //responds accordingly.
    public void checkMenu(String input) {
        if (input.equals("!")) {
            menu();
        }
    }
}
