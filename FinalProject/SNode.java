/*
Rento Saijo
Adam Teryek
Hannah Merritt
William Tarimo
4 May 2025
Final Project

This program represents a node in a singly linked list, storing a String element and reference to the next node.
*/

/**
 * A node in a singly linked list, holding a String element and a link to the next node.
 * Used by SinglyLinkedList to chain elements in the user's timeline and friend lists.
 */
public class SNode {
    // data element stored in this node
    private String element;
    // reference to the next node in the list
    private SNode next;

    /**
     * Constructs a new node with the given element and next-node reference.
     *
     * @param element the String value to store in this node
     * @param next    the next node in the list (or null if none)
     */
    public SNode(String element, SNode next) {
        this.element = element;
        this.next = next;
    }

    /**
     * Returns the element stored in this node.
     *
     * @return the String element of this node
     */
    public String getElement() {
        return this.element;
    }

    /**
     * Returns the next node in the list.
     *
     * @return the next SNode, or null if this is the tail
     */
    public SNode getNext() {
        return this.next;
    }

    /**
     * Updates the element stored in this node.
     *
     * @param element the new String element to set
     */
    public void setElement(String element) {
        this.element = element;
    }

    /**
     * Updates the reference to the next node.
     *
     * @param next the SNode that should follow this one in the list
     */
    public void setNext(SNode next) {
        this.next = next;
    }

    /**
     * Overrides the toString to return the element stored in this node.
     *
     * @return the String element of this node
     */
    public String toString() {
        return this.element;
    }
}