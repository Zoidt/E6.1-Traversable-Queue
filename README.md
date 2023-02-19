# Exercise 6.1 - ✈️ Traversable Queue

## 🎯 Objectives

- **Implement** a traversable queue in Java.

## 🔨 Setup

1. Clone the repo (or download the zip) for this exercise, which you can find [here](https://github.com/JAC-CS-Programming-4-W23/E6.1-Traversable-Queue).
2. Start IntelliJ, go to `File -> Open...`, and select the cloned/downloaded folder.
3. If at the top it says "Project JDK is not defined", click "Setup JDK" on the top right, and select the JDK version you have installed on your machine.

   ![Setup JDK](./images/Setup-JDK.png)

4. To get the unit tests to work, open `QueueTest.java` and add JUnit to the classpath:

   ![Setup Tests](./images/Setup-Tests.png)

   - Just click "OK" on the resulting dialogue window and all the test-related red squigglies should disappear.

## 🔍 Context

Question: you are storing integer values in a stack. You want to know something about the data, for example "what is the largest (or smallest) value?", or "is 123 somewhere in the stack", or maybe you just want to output the current stack values.

Options:

1. Pop the elements of the stack and perform the check. Problem: the stack is empty at the end of this, what if we still needed those values in stack. Pushing them back will invert the order unless we are careful.

2. We can add `min`, `max`, `find`, etc... to the `Stack<T>` class.

   > ⚠️ Problem: most of these operations are type-dependant, for example `min` and `max` only exists for `Comparable` types.

3. Write a "getter" for the array `elements`.

   > ⚠️ Problem: direct access to the array might mean the caller breaks LIFO.

4. Write a `toArray` that will return a copy of the current array elements.

   > ⚠️ Problem: allocating an array takes time and memory.

5. Add methods to the `Stack<T>` class that return each element in the stack, one by one.

We can think of a traversal as "scanning" a collection.

### Range

Taking a look a the `Range` class first will give you an idea about how to implement `Traversable` for `Queue`. A range is simply a `low` integer and a `high` integer. Notice that there is **no array* in this class, only two numbers. We can use the `Range` class like this:

```java
Range range = new Range(1, 5);

range.reset();

while (range.hasNext()) {
    int nextInt = range.next();

    System.out.println(nextInt);
}
```

Internally, `Range` is not looping over an array. Rather, it is incrementing its own `tracker` variable which starts at `low`, and gets incremented every time `next()` is called.

The behaviour should be similar for `Queue` where some sort of `tracker`/`traversal`/`cursor` variable will index the queue's array.

## 🚦 Let's Go

Using the class `IntQueue` (rename it to `Queue` for this exercise) you developed in the [last exercise](https://github.com/JAC-CS-Programming-4-W23/E4.3-Circular-Queue), or using the starter `Queue` class included in this exercise:

1. Have `Queue` implement the `Traversable` interface.
2. You should have some kind of `traversal` member variable that will point to the current position of the traversal.
   1. Think about where `traversal` should start when `reset()` is called.
   2. Think about what value `next()` should return and how that will affect `traversal`.
   3. Think about what condition(s) you need to have for `hasNext()` based on the value of `traversal`.
3. Pass the unit tests in `TestTraversable`.

---

![Tarzan Traversal](./images/Tarzan-Traversal.gif)
