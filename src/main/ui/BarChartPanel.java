package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Represents the panel of the bar chart window
public class BarChartPanel extends JPanel {

    Inventory inventory;
    int maxScale;
    Map<String, Integer> counts;
    List<String> names;
    int breadth;

    //Sets up the panel to display the bar chart
    public BarChartPanel(Inventory inventory) {
        this.inventory = inventory;
        this.counts = getItemCounts();
        this.names = new ArrayList<>(counts.keySet());
        this.maxScale = getMaxScale(counts, names);

        this.setVisible(true);
        this.setOpaque(true);
        this.setLayout(null);
        breadth = getWidth();
        this.setPreferredSize(new Dimension(breadth, 600));
        this.setMinimumSize(new Dimension(breadth, 600));
        this.setBackground(Color.WHITE);
    }

    //Max Width: 600, Max Height: 600
    //Paints the bar chart
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawLine(0, 550, breadth, 550);
        g2D.drawLine(50, 0, 50, 600);

        drawScales(g2D);
        setupScalesLabels(g2D);

        int itemTypes = names.size();
        int x = 90;
        int y = 550;
        int width = 40;
        int height;
        for (int i = 0; i < itemTypes; i++) {
            String name = names.get(i);
            int count = counts.get(name);
            double h = (count * 500) / maxScale;
            height = (int)(h);
            g2D.fillRect(x, y - height, width, height);
            g2D.drawString(name, x, 575);
            x = x + width + 50;
        }
    }

    //EFFECT: draws the scales into the graph's y-axis
    public void drawScales(Graphics2D g2D) {
        g2D.drawLine(40, 500, 60, 500);
        g2D.drawLine(40, 450, 60, 450);
        g2D.drawLine(40, 400, 60, 400);
        g2D.drawLine(40, 350, 60, 350);
        g2D.drawLine(40, 300, 60, 300);
        g2D.drawLine(40, 250, 60, 250);
        g2D.drawLine(40, 200, 60, 200);
        g2D.drawLine(40, 150, 60, 150);
        g2D.drawLine(40, 100, 60, 100);
        g2D.drawLine(40, 50, 60, 50);
    }

    //EFFECT: adds the labels for the scales into the y-axis
    public void setupScalesLabels(Graphics2D g2D) {
        int oneScale = maxScale / 10;
        g2D.drawString(String.valueOf(maxScale), 5, 55);
        g2D.drawString(String.valueOf(oneScale * 9), 5, 105);
        g2D.drawString(String.valueOf(oneScale * 8), 5, 155);
        g2D.drawString(String.valueOf(oneScale * 7), 5, 205);
        g2D.drawString(String.valueOf(oneScale * 6), 5, 255);
        g2D.drawString(String.valueOf(oneScale * 5), 5, 305);
        g2D.drawString(String.valueOf(oneScale * 4), 5, 355);
        g2D.drawString(String.valueOf(oneScale * 3), 5, 405);
        g2D.drawString(String.valueOf(oneScale * 2), 5, 455);
        g2D.drawString(String.valueOf(oneScale * 1), 5, 505);
    }

    //EFFECT: returns the recommended width for the panel
    public int getWidth() {
        int itemTypes = getItemCounts().keySet().size();
        int width = 90 * (itemTypes + 1);
        if (width < 580) {
            width = 580;
        }
        return width;
    }

    //EFFECT: returns a maximum value for the y-scale of the bar
    //graph
    public int getMaxScale(Map<String, Integer> counts, List<String> names) {
        int largest = 0;
        for (String name : names) {
            int count = counts.get(name);
            if (count > largest) {
                largest = count;
            }
        }
        int numOfDigits = Integer.toString(largest).length();
        int upperBound = (int) Math.pow(10, numOfDigits);
        return upperBound;
    }

    //EFFECT: returns a hashmap containing the item types mapping
    //to the count of each item
    public Map<String, Integer> getItemCounts() {
        List<Item> items = inventory.getItems();
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
        return counts;
    }

    //MODIFIES: label
    //EFFECT: sets up the label based on the parameters
    public void setupLabel(JLabel label, String text, int x, int y, int width, int height) {
        label.setText(text);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(x, y, width, height);
        label.setVisible(true);
        label.setOpaque(true);
    }
}
