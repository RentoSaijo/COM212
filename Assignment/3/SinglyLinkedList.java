/*
Rento Saijo
Adam Teryek
William Tarimo
15 April 2025
Assignment 3

This program represents a singly linked list data structure, maintaining
a reference to the head and tail nodes and tracking the list size.
*/

/**
 * The SinglyLinkedList class implements a singly linked list data structure.
 * It maintains a reference to the head and tail nodes and tracks the list size.
 */
public class SinglyLinkedList {
    // Reference to the first node in the list.
    private SNode head;
    // Reference to the last node in the list.
    private SNode tail;
    // The number of elements in the list.
    int size;

    /**
     * Constructs an empty SinglyLinkedList.
     * Both head and tail are set to null and size is initialized to 0.
     */
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the size of the list
     */
    public int size() {
        return this.size;
    }

    /**
     * Checks whether the list is empty.
     *
     * @return true if the list has no elements, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the first element in the list.
     *
     * @return the first character in the list, or a space character if the list is empty
     */
    public char first() {
        if (this.isEmpty()) {
            return ' ';
        }
        return this.head.getElement();
    }

    /**
     * Returns the last element in the list.
     *
     * @return the last character in the list, or a space character if the list is empty
     */
    public char last() {
        if (this.isEmpty()) {
            return ' ';
        }
        return this.tail.getElement();
    }

    /**
     * Adds a new element at the beginning of the list.
     *
     * @param bracket the character to be added as the first element
     */
    public void addFirst(char bracket) {
        // Create a new node with the given bracket and link it to the current head.
        SNode newNode = new SNode(bracket, this.head);
        // Set the new node as the head.
        this.head = newNode;
        // If the list was empty, set tail to head as well.
        if (this.isEmpty()) {
            this.tail = this.head;
        }
        // Increment the list size.
        this.size++;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the character that was removed, or a space character if the list is empty
     */
    public char removeFirst() {
        if (this.isEmpty()) {
            return ' ';
        }
        // Store the current head temporarily.
        SNode temp = this.head;
        // Move the head pointer to the next node.
        this.head = this.head.getNext();
        // Decrement the list size.
        this.size--;
        // If the list becomes empty, set tail to null.
        if (this.isEmpty()) {
            this.tail = null;
        }
        // Disconnect the removed node from the list.
        temp.setNext(null);
        // Return the element from the removed node.
        return temp.getElement();
    }

    /**
     * Displays the list elements by printing them to the console.
     */
    public void display() {
        SNode current = this.head;
        // Iterate through the list and print each node's element.
        while (current != null) {
            System.out.print(current + " ");
            current = current.getNext();
        }
        // Move to a new line after printing all elements.
        System.out.println();
    }

    /**
     * Returns a string representation of the list.
     *
     * @return a string that contains all the list elements separated by spaces
     */
    public String toString() {
        SNode current = this.head;
        String list = "";
        // Build the string by concatenating each node's element.
        while (current != null) {
            list += current + " ";
            current = current.getNext();
        }
        return list;
    }
}