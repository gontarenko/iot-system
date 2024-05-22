package ru.gontarenko.subscriberapp;

import javax.swing.*;

import org.eclipse.paho.client.mqttv3.MqttException;

public class SubscriberMain {
    public static void main(String[] args) throws MqttException {
        ColorChangingFrame frame = new ColorChangingFrame();
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
        Subscriber subscriber = new Subscriber(frame);
        while (true) {

        }
    }
}
