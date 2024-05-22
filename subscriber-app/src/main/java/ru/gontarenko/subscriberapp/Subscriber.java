package ru.gontarenko.subscriberapp;

import java.awt.*;

import javax.swing.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Component;

@Component
public class Subscriber {
    private static final String TOPIC = "testTopic";
    private static final String serverURI = "tcp://localhost:9001";
//    private static final String serverURI = "tcp://mosquitto-broker:9001";

    private final MqttClient mqttClient;
    private ColorChangingFrame frame;

    public Subscriber(ColorChangingFrame frame) throws MqttException {
        mqttClient = new MqttClient(serverURI, "subscriber");

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(15);
        options.setUserName("admin");
        options.setPassword("pass_123".toCharArray());

        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("Соединение потеряно: " + cause.getMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String payload = new String(message.getPayload());
                System.out.println("Сообщение получено: " + payload);
                processMessage(payload);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });

        mqttClient.connect(options);
        mqttClient.subscribe(TOPIC);
        this.frame = frame;
    }

    private void processMessage(String payload) throws JsonProcessingException {
        Message message = new ObjectMapper().readValue(payload, Message.class);
        frame.updateColorAndText(message.value());
    }

    private record Message(int value) {
    }
}

// todo 1) реакция на данные
//      2) завернуть в докер
//      3) записать видос
//      4) word-отчет
//      5) отправить ему все

