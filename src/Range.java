/*
 * Copyright (c) 2020 Ian Clement. All rights reserved.
 */


/**
 * Represents a range of values between lower (inclusive) and upper (exclusive)  values.
 *
 * @author Ian Clement
 */
public class Range implements Traversable<Integer> {

    private int low;
    private int high;
    private int tracker;

    public Range(int high) {
        this(0, high);
    }

    public Range(int low, int high) {
        if(low > high)
            throw new RuntimeException("Impossible range.");
        this.low = low;
        this.high = high;
    }

    @Override
    public String toString() {
        return "[" + low + ".." + high + "[";
    }

    @Override
    public void reset() {
        tracker = low;
    }

    @Override
    public Integer next() {
        if (hasNext())
            return tracker++;
        else
            throw new TraversalException();
    }

    @Override
    public boolean hasNext() {
        return tracker != high;
    }

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }

}
