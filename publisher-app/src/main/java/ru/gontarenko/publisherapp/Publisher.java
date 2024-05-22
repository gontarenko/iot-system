package ru.gontarenko.publisherapp;

import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher {
    private static final String TOPIC = "testTopic";
//    private static final String serverURI = "tcp://mosquitto-broker:9001";
    private static final String serverURI = "tcp://localhost:9001";

    private MqttClient mqttClient;

    public Publisher() throws MqttException {
        mqttClient = new MqttClient(serverURI, "publisher");

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(15);
        options.setUserName("admin");
        options.setPassword("pass_123".toCharArray());

        mqttClient.connect(options);
    }

    public void sendMessage(List<Sensor> sensors) throws MqttException {
        if (sensors.isEmpty()) {
            throw new IllegalStateException();
        }
        for (Sensor sensor : sensors) {
            String messageJson = String.format("{\"value\": %s}", sensor.getValue());
            MqttMessage message = new MqttMessage(messageJson.getBytes());
            message.setRetained(true);
            mqttClient.publish(TOPIC, message);
            System.out.println("Сообщение \"%s\" отправлено".formatted(messageJson));
        }
    }
}
