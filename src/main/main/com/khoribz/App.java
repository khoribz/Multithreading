package com.khoribz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class App {
    private App() {
    }

    private static final ArrayList<String> TYPES = new ArrayList<>(
            Arrays.asList("Banana", "Bread", "Clothes")
    );
    private static final ArrayList<Integer> CAPACITIES = new ArrayList<>(
            Arrays.asList(10, 20, 50)
    );
    private static ExecutorService executor = Executors.newCachedThreadPool();
    private static final int TUNNEL_CAPACITY = 5;
    private static ArrayBlockingQueue<Ship> tunnel = new ArrayBlockingQueue<>(TUNNEL_CAPACITY);

    private static int getRandom0to2() {
        final int mul = 12345;
        return (int) (Math.random() * mul) % 3;
    }

    public static void main(String[] args) {
        final int shipNumber = 5;
        for (int shipNum = 1; shipNum < shipNumber; ++shipNum) {
            Integer capacity = CAPACITIES.get(getRandom0to2());
            String type = TYPES.get(getRandom0to2());
            executor.execute(new Ship(capacity, shipNum, type, tunnel));
        }
        executor.shutdown();
    }
}

