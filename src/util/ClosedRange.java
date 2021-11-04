package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClosedRange {
    private int lower;
    private int upper;

    private static final Random RAND = new Random();

    public ClosedRange(int lower, int upper) {
        if (lower > upper) throw new IllegalArgumentException("Illegal boundaries!");
        if (lower < 0) throw new IllegalArgumentException("Only positive numbers allowed!");

        this.lower = lower;
        this.upper = upper;
    }

    public int getLower() {
        return lower;
    }

    public int getUpper() {
        return upper;
    }

    public int getNumberOfItems() {
        return (upper - lower + 1);
    }

    public boolean isInRange(int candidate){
        return (candidate <= upper && candidate >= lower);
    }

    public int random(){
        return RAND.nextInt((upper - lower) + 1) + lower;
    }

    @Override
    public String toString() {
        int maximumNumberLength = (int)(Math.log10(upper)+1);
        String rangeFormatString = String.format("[%%%1$dd, %%%1$dd]", maximumNumberLength);
        return String.format(rangeFormatString, lower, upper);
    }
}
