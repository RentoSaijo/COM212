/*
Rento Saijo
Adam Teryek
Hannah Merritt
William Tarimo
4 May 2025
Final Project

This program manages a sequence of String elements as a singly linked list using SNode nodes.
*/

/**
 * A simple singly linked list of strings.
 * Used for storing the user’s timeline posts and friend list in MySocialProfile.
 */
public class SinglyLinkedList {
    // first node in the list
    private SNode head;
    // last node in the list
    private SNode tail;
    // number of elements in the list
    private int size;

    /**
     * Constructs an empty list.
     */
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return current list size
     */
    public int size() {
        return this.size;
    }

    /**
     * Checks whether the list has no elements.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Provides direct access to the first node.
     *
     * @return the head SNode, or null if list is empty
     */
    public SNode getHead() {
        return this.head;
    }

    /**
     * Provides direct access to the last node.
     *
     * @return the tail SNode, or null if list is empty
     */
    public SNode getTail() {
        return this.tail;
    }

    /**
     * Retrieves (but does not remove) the first element.
     *
     * @return the String at head, or null if empty
     */
    public String first() {
        if (this.isEmpty()) {
            return null;
        }
        return this.head.getElement();
    }

    /**
     * Retrieves (but does not remove) the last element.
     *
     * @return the String at tail, or null if empty
     */
    public String last() {
        if (this.isEmpty()) {
            return null;
        }
        return this.tail.getElement();
    }

    /**
     * Inserts a new element at the front of the list.
     *
     * @param string the value to add
     */
    public void addFirst(String string) {
        // link new node to old head
        SNode newNode = new SNode(string, this.head);
        // update head reference
        this.head = newNode;
        if (this.isEmpty()) {
            // if was empty, tail also points here
            this.tail = this.head;
        }
        // one more element now
        this.size++;
    }

    /**
     * Inserts a new element at the end of the list.
     *
     * @param string the value to add
     */
    public void addLast(String string) {
        // next of new tail is null
        SNode newNode = new SNode(string, null);
        if (this.isEmpty()) {
            // if empty, head becomes new node
            this.head = newNode;
        } else {
            // link old tail to new node
            this.tail.setNext(newNode);
        }
        // update tail reference
        this.tail = newNode;
        // increase element count
        this.size++;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed String, or null if list was empty
     */
    public String removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        // node to remove
        SNode temp = this.head;
        // advance head
        this.head = this.head.getNext();
        // decrement size
        this.size--;
        if (this.isEmpty()) {
            // reset tail if now empty
            this.tail = null;
        }
        // clean up reference
        temp.setNext(null);
        // return removed value
        return temp.getElement();
    }

    /**
     * Deletes the first node that contains the given string.
     *
     * @param string the value to delete
     * @return the removed SNode, or null if not found
     */
    public SNode delete(String string) {
        // start at head
        SNode currNode = this.head;
        // keep track of previous
        SNode prevNode = null;
        while (currNode != null) {
            if (currNode.getElement().equals(string)) {
                // found the node to delete
                if (prevNode == null) {
                    // remove head
                    this.head = currNode.getNext();
                } else {
                    // bypass current
                    prevNode.setNext(currNode.getNext());
                }
                if (currNode == this.tail) {
                    // adjust tail if needed
                    this.tail = prevNode;
                }
                // decrement size
                this.size--;
                // return deleted node
                return currNode;
            }
            // move forward
            prevNode = currNode;
            currNode = currNode.getNext();
        }
        // not found in list
        return null;
    }

    /**
     * Returns a CSV-style representation of list elements, each quoted.
     *
     * @return comma-separated quoted elements
     */
    public String toString() {
        SNode current = this.head;
        String list = "";
        while (current != null) {
            // quote and separate each element
            list += "\"" + current.getElement() + "\",";
            current = current.getNext();
        }
        if (list.length() != 0) {
            // remove trailing comma
            return list.substring(0, list.length() - 1);
        }
        // empty string if no elements
        return list;
    }
}