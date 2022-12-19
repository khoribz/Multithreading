package com.khoribz;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Класс порта
 */
public class Port {
    @Getter @Setter
    private String type;
    @Getter
    private AtomicBoolean isFree;
    private final int productivity = 10;
    private final int oneGoodDischargeTime = 1000;

    public Port(String type) {
        this.type = type;
        this.isFree = new AtomicBoolean(true);
    }

    public void discharge(@NotNull Ship ship) throws InterruptedException {
        /**
         * Функция для выгрузки товара на порту
         */
        isFree.set(false);
        System.out.println(type + " port is busy by ship №" + ship.getNumber() +
                "with capacity " + ship.getCapacity());

        Thread.sleep((long) oneGoodDischargeTime * ship.getCapacity() / productivity);

        isFree.set(true);
        System.out.println(type + " port is free by ship №" + ship.getNumber() +
                "with capacity " + ship.getCapacity());
    }
}

