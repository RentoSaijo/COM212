/*
Rento Saijo
William Tarimo
Data Structures
6 March 2025
Homework 5

This program establishes a node for a doubly-linked list that
encapsulates a GameScore along with links to both the previous and next nodes.
*/

/**
 * Represents a node in a doubly-linked list that stores a GameScore element.
 * Each node maintains references to both the previous and next nodes in the list.
 */
public class DNode {
    // The GameScore element stored in this node.
    private GameScore element;
    // Reference to the next node in the doubly-linked list.
    private DNode next;
    // Reference to the previous node in the doubly-linked list.
    private DNode prev;

    /**
     * Constructs a DNode with the specified GameScore element, previous node, and next node.
     *
     * @param s the GameScore element to be stored in this node
     * @param p the previous node in the list
     * @param n the next node in the list
     */
    public DNode(GameScore s, DNode p, DNode n) {
        this.element = s;
        this.prev = p;
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
     * Returns the next node in the doubly-linked list.
     *
     * @return the next DNode
     */
    public DNode getNext() {
        return this.next;
    }

    /**
     * Returns the previous node in the doubly-linked list.
     *
     * @return the previous DNode
     */
    public DNode getPrev() {
        return this.prev;
    }

    /**
     * Sets the GameScore element for this node.
     *
     * @param s the new GameScore element to be stored
     */
    public void setElement(GameScore s) {
        this.element = s;
    }

    /**
     * Sets the next node reference in the list.
     *
     * @param n the new next node
     */
    public void setNext(DNode n) {
        this.next = n;
    }

    /**
     * Sets the previous node reference in the list.
     *
     * @param p the new previous node
     */
    public void setPrev(DNode p) {
        this.prev = p;
    }

    /**
     * Returns a string representation of this node.
     * Delegates to the toString method of the GameScore element.
     *
     * @return a string representation of the stored GameScore
     */
    public String toString() {
        return this.element.toString();
    }
}