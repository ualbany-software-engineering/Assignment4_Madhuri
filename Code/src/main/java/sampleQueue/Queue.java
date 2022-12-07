package sampleQueue;

/******************************************************************************
 *  Compilation:  javac Queue.java
 *  Execution:    java Queue < input.txt
 *  Data files:   https://introcs.cs.princeton.edu/java/43stack/tobe.txt
 *
 *  A generic queue, implemented using a linked list.
 *
 *  % java Queue < tobe.txt
 *  to be or not to be (2 left on queue)
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code Queue} class represents a first-in-first-out (FIFO) queue of
 * generic items. It supports the usual <em>enqueue</em> and <em>dequeue</em>
 * operations, along with methods for peeking at the top item, testing if the
 * queue is empty, getting the number of items in the queue, and iterating over
 * the items in FIFO order.
 * <p>
 * This implementation uses a singly-linked list with a nested class for
 * linked-list nodes. The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>,
 * <em>size</em>, and <em>is-empty</em> operations all take constant time in the
 * worst case.
 * <p>
 * For additional documentation, see
 * <a href="https://introcs.cs.princeton.edu/java/43stack">Section 4.3</a> of
 * <i>Computer Science: An Interdisciplinary Approach</i> by Robert Sedgewick
 * and Kevin Wayne.
 *
 * @param <Item> the generic type of an item in this queue
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Ninad Chaudhari, University at Albany
 */
public class Queue<Item> implements Iterable<Item> {
    private int n; // number of elements on queue
    private int maxLength; // max possible length of queue
    private Node first; // beginning of queue
    private Node last; // end of queue

    // helper linked list class
    private class Node {
        private Item item; // the item in the node
        private Node next; // reference to next item
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {
        first = null;
        last = null;
        n = 0;
        this.maxLength = Integer.MAX_VALUE;
    }

    /**
     * Initializes an queue with (inclusive) maxLength as maximum number of elements
     * allowed in queue
     */
    public Queue(int maxLength) {
        first = null;
        last = null;
        n = 0;
        this.maxLength = maxLength;
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return this.length();
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int length() {
        return n;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    /**
     * Add the item to the queue.
     *
     */
    public void enqueue(Item item) {
        if (this.n == this.maxLength)
            throw new RuntimeException("Maximum Size of queue reached, cannot add more.");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
        n++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item dequeue() {
        // !!!!!!!!!!!!!!!!
        // This part was commented by someone in your team
        // which caused the bug.!!!!!!!!!!!
        // !!!!!!!!!!!!!!!!
        //
        // if (isEmpty())
        // throw new NoSuchElementException("Queue underflow");
        //
        // !!!!!!!!!!!!!!!!
        // End
        // !!!!!!!!!!!!!!!!
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty())
            last = null; // to avoid loitering
        return item;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }

    /**
     * Removes all items from the queue.
     */
    public void removeAll() {
        while (this.length() != 0) {
            this.dequeue();
        }

    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first; // node containing current item

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * Unit tests the {@code Queue} data type.
     */
    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        // System.out.println(queue.dequeue());
        System.out.println(queue.n+" "+queue.maxLength);
        System.out.println("(" + queue.size() + " left on queue)");
    }
}
