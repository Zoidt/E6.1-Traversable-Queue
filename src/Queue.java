/*
 * Copyright (c) 2021 Ian Clement. All rights reserved.
 */

/**
 * FIFO collection
 */
public class Queue<T> {

    // Constants
    private static final int DEFAULT_CAPACITY = 100;

    // Fields

    // store the queue in a "circular" array between front and rear
    private T[] elements;
    private int front;
    private int rear;

    // track the empty queue with a flag (set to true when dequeue empties the queue)
    private boolean empty;

    // Constructors

    public Queue() {
        this(DEFAULT_CAPACITY);
    }

    public Queue(int capacity) {
        this.front = -1;
        this.rear = -1;
        this.empty = true;
        this.elements = (T[]) new Object[capacity];
    }

    // Methods

    /**
     * Add an item to the rear of the queue.
     * @param item item to be added to queue.
     * @precondition The queue is not full.
     * @postcondition The item has been added to the rear of the queue.
     */
    public void enqueue(T item) {
        if(isFull())  // check precondition
            throw new QueueOverflowException();

        // enqueue the element
        rear = mod(rear + 1, elements.length);  // (rear + 1) % elements.length
        elements[rear] = item;

        // enqueue means the queue is not empty
        empty = false;
    }

    /**
     * Remove and return the item from the front of a queue.
     * @return the item that was at the front of the queue.
     * @precondition The queue is not empty.
     * @postcondition The front item has been removed from the queue.
     */
    public T dequeue() {
        if(isEmpty())  // check precondition
            throw new QueueUnderflowException();

        // step the "front" index, dequeue the element and nullify the position in the array.
        front = mod(front + 1, elements.length);
        T element = elements[front];
        elements[front] = null;

        // dequeue can make the queue empty
        if(front == rear)
            empty = true;

        return element;
    }

    /**
     * Return the item from the front of a queue.
     * @return the item that was at the front of the queue.
     * @precondition The queue is not empty.
     */
    public T peek() {
        if(isEmpty()) { // check precondition
            throw new QueueUnderflowException();
        }

        return elements[mod(front + 1, elements.length)];
    }

    /**
     * Test whether the queue is empty.
     * @return true if the queue is empty, false otherwise.
     * @precondition None.
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * Test whether the queue is full.
     * @return true if the queue is full, false otherwise.
     * @precondition None.
     */
    public boolean isFull() {
        return mod(front, elements.length) == mod(rear, elements.length) && !empty;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("FRONT -> [");

        if(!empty) {
            for (int i = mod(front + 1, elements.length);
                 i != mod(rear, elements.length);
                 i = mod(i + 1, elements.length))
            {
                result.append(elements[i]);
                result.append(", ");
            }

            result.append(elements[rear]);
        }

        result.append("] <- REAR");

        return result.toString();
    }

    /**
     * Compute x modulo m.
     * @param x The value.
     * @param m The modulus.
     * @return The value of x modulo m, which will always be positive.
     */
    private static int mod(int x, int m) {
        if(x >= 0)
            return x % m;
        else
            return m + (x % m);
    }

}
