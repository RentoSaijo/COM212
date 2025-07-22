/*
Rento Saijo
William Tarimo
Data Structures
4 March 2025
Homework 4

This program establishes a node for a singly-linked list, designed to store a GameScore object
along with a reference to the next node.
*/

/**
 * Represents a node in a singly-linked list that stores a GameScore element.
 */
public class SNode {
    // The GameScore element stored in this node.
    private GameScore element;
    // The reference to the next node in the list.
    private SNode next;

    /**
     * Constructs an SNode with the specified GameScore element and the next node.
     *
     * @param s the GameScore element to be stored in this node
     * @param n the next node in the list
     */
    public SNode(GameScore s, SNode n) {
        this.element = s;
        this.next = n;
    }

    /**
     * Returns the GameScore element stored in this node.
     *
     * @return the GameScore element
     */
    public GameScore getElement() {
        return this.element;
    }

    /**
     * Returns the next node in the list.
     *
     * @return the next SNode
     */
    public SNode getNext() {
        return this.next;
    }

    /**
     * Sets the GameScore element of this node.
     *
     * @param s the new GameScore element
     */
    public void setElement(GameScore s) {
        this.element = s;
    }

    /**
     * Sets the next node in the list.
     *
     * @param n the new next SNode
     */
    public void setNext(SNode n) {
        this.next = n;
    }

    /**
     * Returns a string representation of this SNode.
     * Delegates to the toString method of the GameScore element.
     *
     * @return a string representation of the node's element
     */
    public String toString() {
        return this.element.toString();
    }
}