package ui;

import model.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//Represents the window that generates the bar chart
public class BarChart extends JFrame {

    JPanel panel;

    //Constructs the bar chart
    public BarChart(Inventory inventory) {
        this.setVisible(true);
        this.setTitle("Bar Graph");
        this.setResizable(false);
        this.setSize(600, 628);

        panel = new BarChartPanel(inventory);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        this.add(scrollPane);
    }
}
