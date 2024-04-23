package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PopupFrame {

    private JFrame frame;
    private JLabel message;

    public PopupFrame(String text) {
        frame = new JFrame();
        message = new JLabel();

        message.setText(text);
        message.setHorizontalAlignment(JLabel.CENTER);
        frame.add(message);
        frame.setTitle("Alert Message");
        frame.setResizable(false);
        frame.setSize(450, 100);
        frame.setVisible(true);

        ActionListener closeWindow = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        };

        new Timer(3000, closeWindow).start();
    }
}
