import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestTraversable {

    // shift the position of the queue in memory
    private static <T> void shiftQueue(Queue<T> queue, int pos) {
        for(int i = 0; i < pos; i++) {
            queue.enqueue(null);
            queue.dequeue();
        }
    }

    @Test
    public void testQueueTraversableConstructor() {
        Range range = new Range(3,6);
        Queue<Integer> queue = new Queue<>(5, range);

        assertEquals(3, (int) queue.dequeue());
        assertEquals(4, (int) queue.dequeue());
        assertEquals(5, (int) queue.dequeue());
    }

    @Test
    public void testQueueTraversableEmpty() {
        Queue<Integer> queue = new Queue<>(5);

        queue.reset();
        assertFalse(queue.hasNext());
        shiftQueue(queue, 3);
        queue.reset();
        assertFalse(queue.hasNext());
    }

    @Test
    public void testQueueTraversable() {
        Queue<Integer> queue = new Queue<>(5);
        shiftQueue(queue, 3);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        queue.reset();

        assertTrue(queue.hasNext());
        assertEquals(1, (int) queue.next());
        assertTrue(queue.hasNext());
        assertEquals(2, (int) queue.next());
        assertTrue(queue.hasNext());
        assertEquals(3, (int) queue.next());
        assertFalse(queue.hasNext());
    }

    @Test
    public void testTraversalException() {
        Queue<Integer> queue = new Queue<>(5);
        shiftQueue(queue, 3);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        queue.reset();

        assertTrue(queue.hasNext());
        assertEquals(1, (int) queue.next());
        assertTrue(queue.hasNext());
        assertEquals(2, (int) queue.next());
        assertTrue(queue.hasNext());
        assertEquals(3, (int) queue.next());
        assertFalse(queue.hasNext());

        assertThrows(TraversalException.class, () -> queue.next());
    }

    @Test
    public void testQueueTraversableFull() {
        Queue<Integer> queue = new Queue<>(5);
        shiftQueue(queue, 3);

        while (!queue.isFull()) {
            queue.enqueue(1);
        }

        queue.reset();
        assertTrue(queue.hasNext());

        int count = 0;

        while (queue.hasNext()) {
            assertEquals(1, (int) queue.next());
            count++;
        }

        assertEquals(5, count);
    }

}
