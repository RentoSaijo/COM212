/*
Rento Saijo
Adam Teryek
William Tarimo
15 April 2025
Assignment 3

This program represents a node in a singly linked list, where
each node stores a character and a reference to the next SNode.
*/

/**
 * The SNode class represents a node in a singly linked list.
 * Each node stores a character and a reference to the next SNode.
 */
public class SNode {
    // The character element stored in this node.
    private char element;
    
    // Reference to the next node in the list.
    private SNode next;

    /**
     * Constructs an SNode with the specified element value and reference to the next node.
     *
     * @param element the character value to be stored in this node
     * @param next    the next SNode in the linked list
     */
    public SNode(char element, SNode next) {
        this.element = element;
        this.next = next;
    }

    /**
     * Returns the character element stored in this node.
     *
     * @return the element character of this node
     */
    public char getElement() {
        return this.element;
    }

    /**
     * Returns the next node in the linked list.
     *
     * @return the next SNode; or null if there is no subsequent node
     */
    public SNode getNext() {
        return this.next;
    }

    /**
     * Sets the character element stored in this node.
     *
     * @param element the new character value to store in this node
     */
    public void setElement(char element) {
        this.element = element;
    }

    /**
     * Sets the next node in the linked list.
     *
     * @param next the new SNode to be linked as the next node
     */
    public void setNext(SNode next) {
        this.next = next;
    }

    /**
     * Returns a string representation of this node.
     * In this case, it returns the element character as a string.
     *
     * @return a String representing this node's element
     */
    public String toString() {
        return this.element + "";
    }
}