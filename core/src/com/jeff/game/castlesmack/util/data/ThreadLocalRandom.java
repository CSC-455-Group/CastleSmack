package com.jeff.game.castlesmack.util.data;

public class ThreadLocalRandom {

    public static int nextInt(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
