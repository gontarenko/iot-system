package ru.gontarenko.publisherapp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PublisherScheduler {
    private final List<Sensor> sensors;
    private final Publisher publisher;

    public PublisherScheduler() throws MqttException {
        this.sensors = new ArrayList<>();
        this.sensors.add(new Sensor(0, 255));

        this.publisher = new Publisher();
    }

    @Scheduled(fixedDelay = 1, initialDelay = 4, timeUnit = TimeUnit.SECONDS)
    public void startPublisher() {
        try {
            publisher.sendMessage(sensors);
        } catch (MqttException e) {
            System.out.println("Ошибка отправки сообщения");
            e.printStackTrace();
        }
    }
}
