package ru.gontarenko.publisherapp;

import java.util.Random;

public class Sensor {
    private final int min;
    private final int max;

    public Sensor(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getValue() {
        return new Random().nextInt(max - min + 1) + min;
    }
}
