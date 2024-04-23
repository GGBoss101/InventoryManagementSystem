package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//Represents the Graphical User Interface of the warehouse inventory management system
public class GUI implements ActionListener {

    private Inventory inventory;
    private EventLog eventLog;

    private JFrame frame;

    private JPanel bufferPanel;
    private JPanel mainPanel;
    private JPanel summaryPanel;
    private JPanel completePanel;
    private JPanel addPanel;
    private JPanel removePanel;
    private JPanel changePanel;
    private JPanel loadPanel;
    private JPanel savePanel;

    private JLabel topLabel;
    private JLabel summaryLabel;
    private JLabel completeLabel;
    private JLabel summaryBodyLabel;
    private JLabel completeBodyLabel;
    private JLabel addLabel;
    private JLabel addIdLabel;
    private JLabel addNameLabel;
    private JLabel addDescriptionLabel;
    private JLabel removeLabel;
    private JLabel removeIdLabel;
    private JLabel changeLabel;
    private JLabel changeOldIdLabel;
    private JLabel changeNewIdLabel;
    private JLabel changeNewNameLabel;
    private JLabel changeNewDescriptionLabel;
    private JLabel loadLabel;
    private JLabel loadFileNameLabel;
    private JLabel saveLabel;
    private JLabel saveFileNameLabel;

    private JTextField addIdText;
    private JTextField addNameText;
    private JTextField addDescriptionText;
    private JTextField removeIdText;
    private JTextField changeOldIdText;
    private JTextField changeNewIdText;
    private JTextField changeNewNameText;
    private JTextField changeNewDescriptionText;
    private JTextField loadFileNameText;
    private JTextField saveFileNameText;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton addSubmitButton;
    private JButton addClearButton;
    private JButton removeSubmitButton;
    private JButton removeClearButton;
    private JButton changeSubmitButton;
    private JButton changeClearButton;
    private JButton loadSubmitButton;
    private JButton loadClearButton;
    private JButton saveSubmitButton;
    private JButton saveClearButton;

    //Constructs a frame with the user options and a side panel to display information
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public GUI() {
        frame = new JFrame();

        mainPanel = new JPanel();
        summaryPanel = new JPanel();
        completePanel = new JPanel();
        addPanel = new JPanel();
        removePanel = new JPanel();
        changePanel = new JPanel();
        loadPanel = new JPanel();
        savePanel = new JPanel();
        bufferPanel = summaryPanel;

        topLabel = new JLabel();
        summaryLabel = new JLabel();
        completeLabel = new JLabel();
        summaryBodyLabel = new JLabel();
        completeBodyLabel = new JLabel();
        addLabel = new JLabel();
        addIdLabel = new JLabel();
        addNameLabel = new JLabel();
        addDescriptionLabel = new JLabel();
        removeLabel = new JLabel();
        removeIdLabel = new JLabel();
        changeLabel = new JLabel();
        changeOldIdLabel = new JLabel();
        changeNewIdLabel = new JLabel();
        changeNewNameLabel = new JLabel();
        changeNewDescriptionLabel = new JLabel();
        loadLabel = new JLabel();
        loadFileNameLabel = new JLabel();
        saveLabel = new JLabel();
        saveFileNameLabel = new JLabel();

        addIdText = new JTextField();
        addNameText = new JTextField();
        addDescriptionText = new JTextField();
        removeIdText = new JTextField();
        changeOldIdText = new JTextField();
        changeNewIdText = new JTextField();
        changeNewNameText = new JTextField();
        changeNewDescriptionText = new JTextField();
        loadFileNameText = new JTextField();
        saveFileNameText = new JTextField();

        addSubmitButton = new JButton();
        addClearButton = new JButton();
        removeSubmitButton = new JButton();
        removeClearButton = new JButton();
        changeSubmitButton = new JButton();
        changeClearButton = new JButton();
        loadSubmitButton = new JButton();
        loadClearButton = new JButton();
        saveSubmitButton = new JButton();
        saveClearButton = new JButton();

        inventory = new Inventory();
        eventLog = EventLog.getInstance();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                printLog();
            }
        });

        setupMainPanel();
        setupSummaryPanel();
        setupMainMenu();
    }

    //MODIFIES: all the main menu buttons in the application
    //EFFECT: sets up the main buttons that allow the user to navigate the application
    public void setupMainButtons() {
        button1 = new JButton();
        setupButton(button1, "Display the inventory summary", 20, 100, 300, 50);
        button2 = new JButton();
        setupButton(button2, "Display the complete inventory", 20, 175, 300, 50);
        button3 = new JButton();
        setupButton(button3, "Add an item to the inventory", 20, 250, 300, 50);
        button4 = new JButton();
        setupButton(button4, "Remove an item from the inventory", 20, 325, 300, 50);
        button5 = new JButton();
        setupButton(button5, "Change an item's information", 20, 400, 300, 50);
        button6 = new JButton();
        setupButton(button6, "Load a pre-existing inventory from a file", 20, 475, 300, 50);
        button7 = new JButton();
        setupButton(button7, "Save current inventory to a file", 20, 550, 300, 50);
        button8 = new JButton();
        setupButton(button8, "Display the inventory bar graph summary", 20, 625, 300, 50);
        button9 = new JButton();
        setupButton(button9, "Quit application", 20, 700, 300, 50);
    }

    //MODIFIES: button
    //EFFECT: sets up a button with properties based on the parameters
    private void setupButton(JButton button, String text, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
        button.setText(text);
        button.addActionListener(this::actionPerformed);
    }

    //MODIFIES: label, panel
    //EFFECT: sets up a label on the top left of the panel
    public void setupTopLabel(String text, JLabel label, JPanel panel) {
        label.setText(text);
        label.setForeground(Color.GREEN);
        label.setBounds(30, 30, 350, 100);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setVerticalAlignment(JLabel.TOP);
        panel.add(label);
    }

    //MODIFIES: label, panel
    //EFFECT: sets up a label which defines the body of the panel
    public void setupBodyLabel(String text, JLabel label, JPanel panel) {
        label.setText(text);
        label.setForeground(Color.GREEN);
        label.setBounds(30, 60, 350, 500);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setVerticalAlignment(JLabel.TOP);
        panel.add(label);
    }

    //MODIFIES: label
    //EFFECT: sets up the label based on the parameters
    public void setupLabel(JLabel label, String text, int x, int y, int width, int height) {
        label.setText(text);
        label.setForeground(Color.GREEN);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(x, y, width, height);
    }

    //MODIFIES: textField
    //EFFECT: sets up the text field based on the parameters
    public void setupTextField(JTextField textField, int x, int y, int width, int height) {
        textField.setPreferredSize(new Dimension(width, height));
        textField.setBounds(x, y, width, height);
    }

    //MODIFIES: panel
    //EFFECT: sets up a panel to be a side panel (on the right)
    public void setupSidePanel(JPanel panel) {
        panel.setLayout(null);
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
        panel.setBackground(Color.BLACK);
        panel.setBounds(350, 0, 350, 772);
    }

    //MODIFIES: savePanel
    //EFFECT: sets up the save panel to save an inventory to a file in the data folder
    public void setupSavePanel() {
        setupSidePanel(savePanel);
        setupTopLabel("<html><p>Enter the name of the file in the data folder<br>"
                + "to which you would like to save the <br>inventory:</p></html>", saveLabel, savePanel);
        setupLabel(saveFileNameLabel, "File name (end with .json):", 30, 90, 350, 100);
        savePanel.add(saveFileNameLabel);
        setupTextField(saveFileNameText, 25, 120, 250, 20);
        savePanel.add(saveFileNameText);
        setupButton(saveSubmitButton, "Save", 50, 180, 200, 30);
        savePanel.add(saveSubmitButton);
        setupButton(saveClearButton, "Clear", 50, 230, 200, 30);
        savePanel.add(saveClearButton);
    }

    //MODIFIES: loadPanel
    //EFFECT: sets up the load panel to load an inventory from a file in the data folder
    public void setupLoadPanel() {
        setupSidePanel(loadPanel);
        setupTopLabel("<html><p>Enter the name of the file in the data folder<br>"
                + "from which you would like to load the<br>inventory:</p></html>", loadLabel, loadPanel);
        setupLabel(loadFileNameLabel, "File name (end with .json):", 30, 90, 350, 100);
        loadPanel.add(loadFileNameLabel);
        setupTextField(loadFileNameText, 25, 120, 250, 20);
        loadPanel.add(loadFileNameText);
        setupButton(loadSubmitButton, "Load", 50, 180, 200, 30);
        loadPanel.add(loadSubmitButton);
        setupButton(loadClearButton, "Clear", 50, 230, 200, 30);
        loadPanel.add(loadClearButton);
    }

    //MODIFIES: changePanel
    //EFFECT: sets up the change panel to change item's information from the inventory
    public void setupChangePanel() {
        setupSidePanel(changePanel);
        setupTopLabel(changeLabelText(), changeLabel, changePanel);
        setupLabel(changeOldIdLabel, "Old Item ID:", 30, 90, 350, 100);
        changePanel.add(changeOldIdLabel);
        setupLabel(changeNewIdLabel, "New Item ID:", 30, 150, 350, 100);
        changePanel.add(changeNewIdLabel);
        setupLabel(changeNewNameLabel, "New Item Name:", 30, 210, 350, 100);
        changePanel.add(changeNewNameLabel);
        setupLabel(changeNewDescriptionLabel, "New Item Description:", 30, 270, 350, 100);
        changePanel.add(changeNewDescriptionLabel);
        setupTextField(changeOldIdText, 25, 120, 250, 20);
        changePanel.add(changeOldIdText);
        setupTextField(changeNewIdText, 25, 180, 250, 20);
        changePanel.add(changeNewIdText);
        setupTextField(changeNewNameText, 25, 240, 250, 20);
        changePanel.add(changeNewNameText);
        setupTextField(changeNewDescriptionText, 25, 300, 250, 20);
        changePanel.add(changeNewDescriptionText);
        setupButton(changeSubmitButton, "Submit", 50, 360, 200, 30);
        changePanel.add(changeSubmitButton);
        setupButton(changeClearButton, "Clear", 50, 410, 200, 30);
        changePanel.add(changeClearButton);
    }

    //EFFECT: returns the string for the top label text of change panel
    public String changeLabelText() {
        return "<html><p>Enter old ID and new information and submit.<br>"
                + "Leave the field empty for information you do <br>"
                + "not want to change:</p></html>";
    }

    //MODIFIES: removePanel
    //EFFECT: sets up the remove panel to remove items from the inventory
    public void setupRemovePanel() {
        setupSidePanel(removePanel);
        setupTopLabel("<html><p>Enter the ID of the item you would like to<br>remove and submit:</p></html>",
                removeLabel, removePanel);
        setupLabel(removeIdLabel, "Item ID:", 30, 90, 350, 100);
        removePanel.add(removeLabel);
        removePanel.add(removeIdLabel);
        setupTextField(removeIdText, 25, 120, 250, 20);
        removePanel.add(removeIdText);
        setupButton(removeSubmitButton, "Submit", 50, 170, 200, 30);
        removePanel.add(removeSubmitButton);
        setupButton(removeClearButton, "Clear", 50, 220, 200, 30);
        removePanel.add(removeClearButton);
    }

    //MODIFIES: addPanel
    //EFFECT: sets up the add panel to add items to the inventory
    public void setupAddPanel() {
        setupSidePanel(addPanel);
        setupTopLabel("Enter the following information and submit:", addLabel, addPanel);
        setupLabel(addIdLabel, "Item ID:", 30, 90, 350, 100);
        addPanel.add(addIdLabel);
        setupLabel(addNameLabel, "Item Name:", 30, 150, 350, 100);
        addPanel.add(addNameLabel);
        setupLabel(addDescriptionLabel, "Item Description:", 30, 210, 350, 100);
        addPanel.add(addDescriptionLabel);
        setupTextField(addIdText, 25, 120, 250, 20);
        addPanel.add(addIdText);
        setupTextField(addNameText, 25, 180, 250, 20);
        addPanel.add(addNameText);
        setupTextField(addDescriptionText, 25, 240, 250, 20);
        addPanel.add(addDescriptionText);
        setupButton(addSubmitButton, "Submit", 50, 300, 200, 30);
        addPanel.add(addSubmitButton);
        setupButton(addClearButton, "Clear", 50, 350, 200, 30);
        addPanel.add(addClearButton);
    }

    //MODIFIES: summaryPanel
    //EFFECT: sets up the side panel to show the inventory summary
    public void setupSummaryPanel() {
        setupSidePanel(summaryPanel);
        setupTopLabel("Inventory Summary:", summaryLabel, summaryPanel);
        setupBodyLabel(getInventoryAsString(true), summaryBodyLabel, summaryPanel);
    }

    //MODIFIES: completePanel
    //EFFECT: sets up the side panel to show the complete inventory
    public void setupCompletePanel() {
        setupSidePanel(completePanel);
        setupTopLabel("Complete Inventory:", completeLabel, completePanel);
        setupBodyLabel(getInventoryAsString(false), completeBodyLabel, completePanel);
    }

    //MODIFIES: mainPanel
    //EFFECT: sets up the main panel on the left containing all the buttons
    //and user options
    public void setupMainPanel() {
        mainPanel.setLayout(null);
        mainPanel.setOpaque(true);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBounds(0, 0, 350, 772);
        setupTopLabel("<html><p>What would you like to do?</p></html>", topLabel, mainPanel);
        setupMainButtons();
        mainPanel.add(button1);
        mainPanel.add(button2);
        mainPanel.add(button3);
        mainPanel.add(button4);
        mainPanel.add(button5);
        mainPanel.add(button6);
        mainPanel.add(button7);
        mainPanel.add(button8);
        mainPanel.add(button9);
    }

    //MODIFIES: frame
    //EFFECT: sets up the entire window or frame of the application
    public void setupMainMenu() {
        frame.setLayout(null);
        frame.add(mainPanel);
        frame.add(summaryPanel);
        frame.add(completePanel);
        frame.add(addPanel);
        frame.add(removePanel);
        frame.add(changePanel);
        frame.add(loadPanel);
        frame.add(savePanel);
        setupPanelsInitialVisibility();
        frame.setTitle("Warehouse Management System");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECT: sets the initial visibility of the panels for the main menu
    public void setupPanelsInitialVisibility() {
        summaryPanel.setVisible(true);
        completePanel.setVisible(false);
        addPanel.setVisible(false);
        removePanel.setVisible(false);
        changePanel.setVisible(false);
        loadPanel.setVisible(false);
        savePanel.setVisible(false);
    }

    //MODIFIES: this
    //EFFECT:Checks for what buttons are pressed from those available to the user
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            displaySummaryPanel();
        } else if (e.getSource() == button2) {
            displayCompletePanel();
        } else if (e.getSource() == button3) {
            displayAddPanel();
        } else if (e.getSource() == button4) {
            displayRemovePanel();
        } else if (e.getSource() == button5) {
            displayChangePanel();
        } else if (e.getSource() == button6) {
            displayLoadPanel();
        } else if (e.getSource() == button7) {
            displaySavePanel();
        } else if (e.getSource() == button8) {
            displayVisualization();
        } else if (e.getSource() == button9) {
            printLog();
            System.exit(0);
        } else {
            checkPanelButtons(e);
        }
    }

    //MODIFIES: this
    //EFFECT: to check whether any of the buttons in a form or panel have been
    //clicked
    public void checkPanelButtons(ActionEvent e) {
        if (e.getSource() == addSubmitButton) {
            addSubmitFunction();
        } else if (e.getSource() == addClearButton) {
            addClearFields();
        } else if (e.getSource() == removeSubmitButton) {
            removeSubmitFunction();
        } else if (e.getSource() == removeClearButton) {
            removeClearFields();
        } else if (e.getSource() == changeSubmitButton) {
            changeSubmitFunction();
        } else if (e.getSource() == changeClearButton) {
            changeClearFields();
        } else if (e.getSource() == loadSubmitButton) {
            loadSubmitFunction();
        } else if (e.getSource() == loadClearButton) {
            loadClearFields();
        } else if (e.getSource() == saveSubmitButton) {
            saveSubmitFunction();
        } else if (e.getSource() == saveClearButton) {
            saveClearFields();
        }
    }

    //MODIFIES: this
    //EFFECT: carries out the functioning of the submit button in
    //the add panel
    public void addSubmitFunction() {
        String id = addIdText.getText();
        String name = addNameText.getText();
        String description = addDescriptionText.getText();
        addItem(id, name, description);
    }

    //MODIFIES: this
    //EFFECT: carries out the functioning of the submit button in
    //the remove panel
    public void removeSubmitFunction() {
        String id = removeIdText.getText();
        removeItem(id);
    }

    //MODIFIES: this
    //EFFECT: carries out the functioning of the submit button in
    //the change panel
    public void changeSubmitFunction() {
        String oldId = changeOldIdText.getText();
        String newId = changeNewIdText.getText();
        String newName = changeNewNameText.getText();
        String newDescription = changeNewDescriptionText.getText();
        changeInfo(oldId, newId, newName, newDescription);
    }

    //MODIFIES: this
    //EFFECT: carries out the functioning of the load button in
    //the load panel
    public void loadSubmitFunction() {
        String fileName = loadFileNameText.getText();
        loadInventory(fileName);
    }

    //MODIFIES: file
    //EFFECT: carries out the functioning of the save button in
    //the load panel
    public void saveSubmitFunction() {
        String fileName = saveFileNameText.getText();
        saveInventory(fileName);
    }

    //EFFECT: displays the visualization being a bar graph summary
    //of the inventory
    public void displayVisualization() {
        new BarChart(this.inventory);
    }

    //MODIFIES: this
    //EFFECT: displays the savePanel
    public void displaySavePanel() {
        bufferPanel.setVisible(false);
        setupSavePanel();
        savePanel.setVisible(true);
        bufferPanel = savePanel;
    }

    //MODIFIES: this
    //EFFECT: displays the loadPanel
    public void displayLoadPanel() {
        bufferPanel.setVisible(false);
        setupLoadPanel();
        loadPanel.setVisible(true);
        bufferPanel = loadPanel;
    }

    //MODIFIES: this
    //EFFECT: displays the changePanel
    public void displayChangePanel() {
        bufferPanel.setVisible(false);
        setupChangePanel();
        changePanel.setVisible(true);
        bufferPanel = changePanel;
    }

    //MODIFIES: this
    //EFFECT: displays the removePanel
    public void displayRemovePanel() {
        bufferPanel.setVisible(false);
        setupRemovePanel();
        removePanel.setVisible(true);
        bufferPanel = removePanel;
    }

    //MODIFIES: this
    //EFFECT: displays the summaryPanel
    public void displaySummaryPanel() {
        bufferPanel.setVisible(false);
        setupSummaryPanel();
        summaryPanel.setVisible(true);
        bufferPanel = summaryPanel;
    }

    //MODIFIES: this
    //EFFECT: displays the completePanel
    public void displayCompletePanel() {
        bufferPanel.setVisible(false);
        setupCompletePanel();
        completePanel.setVisible(true);
        bufferPanel = completePanel;
    }

    //MODIFIES: this
    //EFFECT: displays the addPanel
    public void displayAddPanel() {
        bufferPanel.setVisible(false);
        setupAddPanel();
        addPanel.setVisible(true);
        bufferPanel = addPanel;
    }

    //MODIFIES: this
    //EFFECT: Clears the text fields in the add item panel
    public void addClearFields() {
        addIdText.setText("");
        addNameText.setText("");
        addDescriptionText.setText("");
    }

    //MODIFIES: this
    //EFFECT: Clear the text fields in the remove item panel
    public void removeClearFields() {
        removeIdText.setText("");
    }

    //MODIFIES: this
    //EFFECT: Clear the text fields in the change item information panel
    public void changeClearFields() {
        changeOldIdText.setText("");
        changeNewIdText.setText("");
        changeNewNameText.setText("");
        changeNewDescriptionText.setText("");
    }

    //MODIFIES: this
    //EFFECT: Clears the text fields in the load panel
    public void loadClearFields() {
        loadFileNameText.setText("");
    }

    //MODIFIES: this
    //EFFECT: Clears the text fields in the save panel
    public void saveClearFields() {
        saveFileNameText.setText("");
    }

    //MODIFIES: this
    //EFFECT: Change an item's information if possible. Alerts the user
    //otherwise. Does not change information of empty fields.
    public void changeInfo(String oldId, String newId, String name, String description) {
        Item selectedItem = inventory.findItemByID(oldId);
        if (selectedItem == null) {
            new PopupFrame("ERROR: no item with this old ID exists in the inventory.");
        } else {
            boolean idIsSet = true;
            if (!newId.equals("")) {
                idIsSet = selectedItem.setID(inventory, newId);
            }
            if (!idIsSet) {
                new PopupFrame("ERROR: an item with this new ID already exists in the inventory.");
            } else {
                if (!name.equals("")) {
                    selectedItem.setName(name);
                }
                if (!description.equals("")) {
                    selectedItem.setDescription(description);
                }
                new PopupFrame("Item information changed successfully!");
            }
        }
    }

    //MODIFIES: this
    //EFFECT: Removes an items from the inventory if found. Alerts the
    //user to an error otherwise.
    public void removeItem(String id) {
        boolean isRemoved = inventory.removeItem(id);
        if (!isRemoved) {
            new PopupFrame("ERROR: no item with this ID exists in the inventory.");
        } else {
            new PopupFrame("Item successfully removed from inventory!");
        }
    }

    //MODIFIES: file
    //EFFECT: saves data (the inventory) to a file whose name is given in
    //the parameter
    public void saveInventory(String fileName) {
        JsonWriter jsonWriter = new JsonWriter();
        try {
            jsonWriter.openFile(fileName);
            jsonWriter.write(inventory);
            jsonWriter.closeFile();
            new PopupFrame("Inventory successfully saved to file!");
        } catch (Exception e) {
            new PopupFrame("ERROR: this file does not exist.");
        }
    }

    //MODIFIES: this
    //EFFECT: loads data (the inventory) from a file whose name is given in
    //the parameter
    public void loadInventory(String fileName) {
        JsonReader jsonReader = new JsonReader();
        try {
            this.inventory = jsonReader.read(fileName);
            new PopupFrame("Inventory successfully loaded from file!");
        } catch (Exception e) {
            new PopupFrame("ERROR: This file does not exist.");
        }
    }

    //MODIFIES: this
    //EFFECT: Adds an item to the inventory if possible. Alerts the user
    //to an error otherwise
    public void addItem(String id, String name, String description) {
        if (id.equals("") || name.equals("") || description.equals("")) {
            new PopupFrame("ERROR: no field must be left empty.");
        } else {
            boolean isAdded = inventory.addItem(new Item(id, name, description));
            if (!isAdded) {
                new PopupFrame("ERROR: an item with this ID already exists. Please re-enter.");
            } else {
                new PopupFrame("Item successfully added to inventory!");
            }
        }
    }

    //EFFECT: Returns the inventory's contents.
    public String getInventoryAsString(boolean isSummary) {
        List<Item> items = inventory.getItems();
        String output = "<html><p>";
        if (items.size() == 0) {
            output = output + "The inventory is currently empty.";
        } else if (!isSummary) {
            for (Item i : items) {
                String out = "<br>Name: " + i.getName() + "<br>ID: " + i.getID();
                out = out + "<br>Description: " + i.getDescription() + "<br>";
                output = output + out;
            }
        } else {
            output = getInventorySummary(items);
        }
        output = output + "</p></html>";
        return output;
    }

    //EFFECT: returns the summary of the inventory (without IDs and descriptions)
    public String getInventorySummary(List<Item> items) {
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
        String output = "<html><p>";
        List<String> keys = new ArrayList<>(counts.keySet());
        for (String key : keys) {
            String out = "<br>" + key + " x " + counts.get(key);
            output = output + out;
        }
        output = output + "</p></html>";
        return output;
    }

    //EFFECT: prints the event log onto the console
    public void printLog() {
        for (Event event : eventLog) {
            System.out.println("Date: " + event.getDate() + ", Description: " + event.getDescription());
        }
    }
}
