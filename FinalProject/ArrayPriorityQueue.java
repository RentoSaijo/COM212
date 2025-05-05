/*
Rento Saijo
Adam Teryek
Hannah Merritt
William Tarimo
4 May 2025
Final Project

This program implements a min-heap priority queue of Event objects using a 1-based array,
used to store and retrieve upcoming events in chronological order for MySocialProfile.
*/

/**
 * A priority queue backed by an array-based binary heap.
 * The smallest (earliest) Event is always at the root (index 1).
 */
public class ArrayPriorityQueue {
    // array storage for heap elements; index 0 unused for simpler math
    private Event[] A;
    // number of elements currently in the heap
    private int n;

    /**
     * Constructs an empty priority queue with specified initial capacity.
     *
     * @param capacity maximum number of events (heap array length = capacity+1)
     */
    public ArrayPriorityQueue(int capacity) {
        this.A = new Event[capacity+1];
        this.n = 0;
    }

    /**
     * Returns the number of events in the queue.
     *
     * @return current heap size
     */
    public int size() {
        return this.n;
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if no elements, false otherwise
     */
    public boolean isEmpty() {
        return this.n == 0;
    }

    /**
     * Peeks at the minimum (earliest) event without removing it.
     *
     * @return Event at root, or null if empty
     */
    public Event getMin() {
        if (this.isEmpty()) {
            return null;
        }
        // smallest event is at index 1
        return this.A[1];
    }

    /**
     * Inserts a new event into the heap.
     * Bubbles up to restore heap order.
     *
     * @param e the Event to add (must not be null)
     */
    public void insert(Event e) {
        // place at next available index
        this.A[this.n+1] = e;
        // increment count
        this.n++;
        // child index
        int cIndex = this.n;
        // parent index
        int pIndex = parentIndex(cIndex);
        // bubble up while smaller than parent
        while (pIndex >= 1 && this.A[cIndex].compareTo(this.A[pIndex]) < 0) {
            swap(cIndex, pIndex);
            cIndex = pIndex;
            pIndex = parentIndex(cIndex);
        }
    }

    /**
     * Removes and returns the minimum (earliest) event.
     * Swaps root with last element, bubbles down to restore order.
     *
     * @return removed Event, or null if heap was empty
     */
    public Event extractMin() {
        if (this.isEmpty()) {
            return null;
        }
        // move last element to root
        swap(1, this.n);
        // store smallest
        Event min = this.A[this.n];
        // clear last slot
        A[this.n] = null;
        // decrement size
        this.n--;
        // start bubbling down from root
        int parent = 1;
        while (true) {
            int left = leftChildIndex(parent);
            int right = rightChildIndex(parent);
            int smallest = parent;
            // check left child
            if (left <= this.n && this.A[left].compareTo(this.A[smallest]) < 0) {
                smallest = left;
            }
            // check right child
            if (right <= this.n && this.A[right].compareTo(this.A[smallest]) < 0) {
                smallest = right;
            }
            // heap property restored
            if (smallest == parent) {
                break;
            }
            swap(parent, smallest);
            // continue down
            parent = smallest;
        }
        return min;
    }

    /**
     * Computes the parent index of a given node index.
     * 
     * @param i the child node index
     * @return the index of its parent
     */
    private int parentIndex(int i) {
        return i / 2;
    }

    /**
     * Computes the left child index of a given node index.
     * 
     * @param i the parent node index
     * @return the index of its left child
     */
    private int leftChildIndex(int i) {
        return 2 * i;
    }

    /**
     * Computes the right child index of a given node index.
     * 
     * @param i the parent node index
     * @return the index of its right child
     */
    private int rightChildIndex(int i) {
        return 2 * i + 1;
    }

    /**
     * Swaps two elements in the heap array.
     *
     * @param i index of first element
     * @param j index of second element
     */
    private void swap(int i, int j) {
        Event temp = this.A[i];
        this.A[i] = this.A[j];
        this.A[j] = temp;
    }
}