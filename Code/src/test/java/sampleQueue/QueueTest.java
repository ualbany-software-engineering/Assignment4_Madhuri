package sampleQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class QueueTest {

	/**
	 * Tests for Queue.
	 */

	private static final String SOME_ITEM = "some-content";
    private static final String ITEM_ONE = "Item 1";
    private static final String ITEM_TWO = "Item 2";
    private static final String ITEM_THREE = "Item 3";

	private Queue<String> q;

	@Test
	@DisplayName("is instantiated with new Queue()")
	void isInstantiatedWithNew() {
		new Queue<>();
	}

	@BeforeEach
	void init() {
		this.q = new Queue<String>();
	}

	@Test
	@DisplayName("Verify Queue isEmpty when queue is initialized")
	void isEmptyShouldGiveTrueOnQueueInit() {
		assertTrue(q.isEmpty());
	}

    //Example of Wrong Test!
    @Test
    @DisplayName("Verify Queue isEmpty returns false when queue is not empty")
    void isEmptyShouldGiveFalseWhenQueueIsNotEmpty() {
        this.q.enqueue(SOME_ITEM);
        assertFalse(q.isEmpty());
    }

	@Test
	@DisplayName("Peek should throw an Exception if called on empty queue")
	void peekThrowsExceptionOnEmpty(){
		assertThrows(NoSuchElementException.class, () -> this.q.peek());
	}

    @Test
    @DisplayName("Peek should return the first element from the non empty queue")
    void peekReturnsFirstItemFromQueue(){
        Queue<String> queue = new Queue<>(1);
        queue.enqueue(ITEM_ONE);
        assertEquals("Item 1", queue.peek());
    }


    @Test
    @DisplayName("Verify the count of number of items in the queue")
    void sizeOfQueueTest() {
        Queue<String> queue = new Queue<>(2);
        queue.enqueue(ITEM_ONE);
        queue.enqueue(ITEM_TWO);
        assertEquals(2, queue.size());
    }

    @Test
    @DisplayName("Verify the number of items in the queue")
    void lengthOfQueueTest() {
        Queue<String> queue = new Queue<>(2);
        queue.enqueue(ITEM_ONE);
        queue.enqueue(ITEM_TWO);
        assertEquals(2, queue.size());
    }

    @Test
    @DisplayName("Enqueue should throw the exception when enqueued on queue with size 0")
    void enqueueThrowsException(){
        Queue<String> queue = new Queue<>(0);
        assertThrows(RuntimeException.class, ()-> queue.enqueue("Item 1"));

    }

    @Test
    @DisplayName("Verifying whether the items are enqueued or not")
    void enqueueItemsOnQueue(){
        Queue<String> queue = new Queue<>(1);
        queue.enqueue(ITEM_ONE);
        assertEquals("Item 1",queue.peek());
    }

    @Test
    @DisplayName("Dequeue should throw an Exception if called on empty queue")
    void dequeueThrowsExceptionOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> this.q.dequeue());
    }

    @Test
    @DisplayName("Verifying whether the items are dequeued or not")
    void dequeueItemsOnQueue(){
        Queue<String> queue = new Queue<>(1);
        queue.enqueue(ITEM_ONE);
        assertEquals("Item 1",queue.dequeue());
    }

    @Test
    @DisplayName("Verifying whether all the items are removed from the queue or not")
    void removeAllItemsOnQueue(){
        Queue<String> queue = new Queue<>(2);
        queue.enqueue(ITEM_ONE);
        queue.enqueue(ITEM_TWO);
        queue.removeAll();
        assertEquals(0,queue.length());
    }

    @Test
    @DisplayName("verify append all the Items to form a string")
    void appendAllItemsOnQueue(){
        Queue<String> queue = new Queue<>(3);
        queue.enqueue(ITEM_ONE);
        queue.enqueue(ITEM_TWO);
        queue.enqueue(ITEM_THREE);
        assertEquals("Item 1 Item 2 Item 3 ",queue.toString());
    }

    @Test
    @DisplayName("verify hasNext() on the Items on queue using Iterator")
    void hasNextTestOnQueueUsingIterator(){
        Queue<String> queue = new Queue<>(3);
        queue.enqueue(ITEM_ONE);
        queue.enqueue(ITEM_TWO);
        queue.enqueue(ITEM_THREE);
        Iterator<String> itr = queue.iterator();
        assertEquals(true,itr.hasNext());
    }

    @Test
    @DisplayName("verify remove() items on queue using iterator")
    void removeTestOnQueueUsingIterator(){
        Queue<String> queue = new Queue<>(3);
        queue.enqueue(ITEM_ONE);
        queue.enqueue(ITEM_TWO);
        queue.enqueue(ITEM_THREE);
        Iterator<String> itr = queue.iterator();
        assertThrows(UnsupportedOperationException.class,() -> itr.remove());
    }

    @Test
    @DisplayName("verify next() items on queue using iterator")
    void nextTestOnEmptyQueue(){
        Iterator<String> itr = this.q.iterator();
        assertThrows(NoSuchElementException.class,() -> itr.next());
    }

    @Test
    @DisplayName("verify next() items on queue using iterator")
    void nextTestOnQueueUsingIterator(){
        Queue<String> queue = new Queue<>(3);
        queue.enqueue(ITEM_ONE);
        queue.enqueue(ITEM_TWO);
        queue.enqueue(ITEM_THREE);
        Iterator<String> itr = queue.iterator();
        assertEquals("Item 1", itr.next());
    }


}
