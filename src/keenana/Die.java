/*
 * Course: CSC 1110 131
 * Term: Fall 2023
 * Assignment: Lab 7
 * Name: Andrew Keenan
 * Created: 10-11-23
 */
package keenana;

import java.util.Random;

/**
 * this class is used to create and object called die
 */
public class Die {
    /**
     * default number of sides
     */
    public static final int DEFAULTNUMSIDES = 6;
    private final int numSides;
    private int currentValue;
    private Random generator;
    /**
     * creates a die with a set number of sides
     * @param numSides is the entered value of the amount of sides
     */
    public Die(int numSides){
        final int maxNum = 100;
        if(numSides>maxNum || numSides<2){
            this.numSides = DEFAULTNUMSIDES;
        } else {
            this.numSides = numSides;
        }
        generator = new Random();
        roll();
    }
    public int getCurrentValue(){
        return currentValue;
    }

    /**
     * "rolls" the dice by producing a random
     * number between 1 and the amount of sides
     * by generating a value using Random()
     */
    public void roll(){
        currentValue = generator.nextInt(1, numSides);
    }
}