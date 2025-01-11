package com.example.gasu_studing.myUtils;

public class BooleanMultiThreadControl {
    private boolean value;

    public BooleanMultiThreadControl() {
        this.value = false;
    }

    // Синхронизированный метод для получения значения
    public synchronized boolean getValue() {
        return value;
    }

    // Синхронизированный метод для установки значения
    public synchronized void setValue(boolean value) {
        this.value = value;
    }
}