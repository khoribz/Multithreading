package com.khoribz;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс корабля
 */
public class Ship implements Runnable {
    @Getter
    @Setter
    private Integer capacity;
    @Getter
    @Setter
    private Integer number;
    @Getter
    @Setter
    private Port port;
    @Getter
    @Setter
    private String type;
    private ArrayBlockingQueue<Ship> tunnel;
    private AtomicBoolean passed;
    private final int timeForPass = 1000;

    Ship(int capacity, int number, String type, ArrayBlockingQueue<Ship> tunnel) {
        this.capacity = capacity;
        this.number = number;
        this.port = new Port(type);
        this.type = type;
        this.tunnel = tunnel;
        this.passed = new AtomicBoolean(false);
    }

    @Override
    public void run() {
        /**
         * Запуск корабля
         */
        try {
            tunnel.put(this);
            System.out.println(Integer.toString(number) + " ship is inside tunnel");
            Thread.sleep(timeForPass);
            while (!this.passed.get()) {
                if (Objects.equals(port.getType(), this.type)) {
                    if (this.port.getIsFree().get()) {
                        this.port.getIsFree().set(false);
                        System.out.println(Integer.toString(number) + " ship passed tunnel");
                        this.tunnel.take();
                        this.port.discharge(this);
                        this.passed.set(true);
                    }
                } else {
                    System.out.println("Ship is not appropriate for port");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}

