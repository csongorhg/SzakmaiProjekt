package com.mygdx.game.Math;

import java.util.Random;

/**
 * Created by Hegedüs Csongor on 2/10/2018.
 */

public class RandomNumber {

    private int genNumber;
    private float genNumberf;

    public RandomNumber(int a, int b){
        genNumber = random(a, b);
    }

    public RandomNumber(float a, float b) {
        genNumberf = random(a, b);
    }

    public RandomNumber(int a, int b, int except) {
        do {
            genNumber = random(a, b);
        }while(getGenNumber() == except);
    }

    private int random(int a, int b) {
        return (int) Math.floor (Math.random() * (b - a + 1) + a);
    }

    private float random(float a, float b) {
        return (float)Math.floor (Math.random() * (b - a + 1) + a);
    }

    public int getGenNumber() {
        return genNumber;
    }

    public float getGenNumberf() {
        return genNumberf;
    }
}
