/*
Rento Saijo
Adam Teryek
William Tarimo
15 April 2025
Assignment 3

This program represents a stack data structure, using
a singly linked list as its underlying storage mechanism.
*/

/**
 * The SinglyLinkedStack class implements a stack data structure
 * using a singly linked list as its underlying storage mechanism.
 */
public class SinglyLinkedStack {  
    // The underlying singly linked list used to store stack elements.
    private SinglyLinkedList list;

    /**
     * Constructs an empty SinglyLinkedStack.
     * Initializes the internal SinglyLinkedList.
     */
    public SinglyLinkedStack() {
        this.list = new SinglyLinkedList();
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the size of the stack
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return true if the stack has no elements, false otherwise
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Pushes a new element onto the top of the stack.
     *
     * @param bracket the character element to be pushed onto the stack
     */
    public void push(char bracket) {
        // Add the new element at the beginning of the underlying list.
        this.list.addFirst(bracket);
    }

    /**
     * Removes and returns the top element from the stack.
     *
     * @return the character element removed from the top of the stack,
     *         or a space character if the stack is empty
     */
    public char pop() {
        // Remove the first element from the underlying list.
        return this.list.removeFirst();
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return the character at the top of the stack,
     *         or a space character if the stack is empty
     */
    public char top() {
        return this.list.first();
    }
  
    /**
     * Displays the contents of the stack by printing the elements to the console.
     */
    public void display() {
        this.list.display();
    }
}