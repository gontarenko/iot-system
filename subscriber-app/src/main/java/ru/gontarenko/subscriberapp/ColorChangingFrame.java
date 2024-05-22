package ru.gontarenko.subscriberapp;

import java.awt.*;

import javax.swing.*;

public class ColorChangingFrame extends JFrame {
    private JPanel panel;
    private JLabel label;

    public ColorChangingFrame() {
        panel = new JPanel();
        label = new JLabel("", SwingConstants.CENTER);

        setTitle("Color Changing Frame");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel.setLayout(new BorderLayout());
        label.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);

        add(panel);
    }

    public void updateColorAndText(int value) {
        Color color = new Color(value, 0, 0);
        panel.setBackground(color);
        label.setText("Current value: " + value);
    }
}