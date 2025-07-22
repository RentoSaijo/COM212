/*
Rento Saijo
William Tarimo
Data Structures
6 March 2025
Homework 5

This program provides an implementation of doubly-linked listl,
using header and trailer sentinels to manage GameScore objects in descending order,
with methods for adding, removing, finding, and displaying elements.
*/

// Import Random class for generating random numbers.
import java.util.Random;

/**
 * Implements a doubly-linked list to store GameScore objects in descending order.
 * Uses header and trailer sentinel nodes to simplify edge case handling.
 */
public class DoublyLinkedList {
    // Sentinel node at the beginning of the list.
    private DNode header;
    // Sentinel node at the end of the list.
    private DNode trailer;
    // The number of real elements in the list.
    private int size;

    /**
     * Constructs an empty DoublyLinkedList with header and trailer sentinel nodes.
     */
    public DoublyLinkedList() {
        // Initialize header with null element, no previous, and no next.
        this.header = new DNode(null, null, null);
        // Initialize trailer with null element; its previous is header.
        this.trailer = new DNode(null, this.header, null);
        // Link header to trailer.
        this.header.setNext(this.trailer);
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
     * Checks if the list is empty.
     *
     * @return true if the list contains no elements; false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the first GameScore in the list.
     *
     * @return the first GameScore, or null if the list is empty
     */
    public GameScore first() {
        if (this.isEmpty()) {
            return null;
        }
        return this.header.getNext().getElement();
    }

    /**
     * Returns the last GameScore in the list.
     *
     * @return the last GameScore, or null if the list is empty
     */
    public GameScore last() {
        if (this.isEmpty()) {
            return null;
        }
        return this.trailer.getPrev().getElement();
    }

    /**
     * Adds a new GameScore at the beginning of the list.
     *
     * @param newScore the GameScore to add
     */
    public void addFirst(GameScore newScore) {
        // Insert between header and the node after header.
        addBetween(newScore, this.header, this.header.getNext());
    }

    /**
     * Adds a new GameScore at the end of the list.
     *
     * @param newScore the GameScore to add
     */
    public void addLast(GameScore newScore) {
        // Insert between the node before trailer and trailer.
        addBetween(newScore, this.trailer.getPrev(), this.trailer);
    }

    /**
     * Removes and returns the first GameScore in the list.
     *
     * @return the removed GameScore, or null if the list is empty
     */
    public GameScore removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        // Remove the node right after header.
        return remove(this.header.getNext());
    }

    /**
     * Removes and returns the last GameScore in the list.
     *
     * @return the removed GameScore, or null if the list is empty
     */
    public GameScore removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        // Remove the node right before trailer.
        return remove(this.trailer.getPrev());
    }

    /**
     * Displays all GameScores in the list.
     * Traverses the list from header to trailer and prints each GameScore.
     */
    public void display() {
        DNode current = this.header.getNext();
        // Traverse until reaching the trailer sentinel node (indicated by null element).
        while (current.getElement() != null) {
            System.out.println(current);
            current = current.getNext();
        }
        System.out.println();
    }

    /**
     * Inserts a new GameScore between two given nodes.
     *
     * @param newScore the GameScore to add
     * @param prevNode the node after which the new node is to be inserted
     * @param nextNode the node before which the new node is to be inserted
     */
    private void addBetween(GameScore newScore, DNode prevNode, DNode nextNode) {
        DNode newNode = new DNode(newScore, prevNode, nextNode);
        prevNode.setNext(newNode);
        nextNode.setPrev(newNode);
        this.size++;
    }

    /**
     * Removes a specified node from the list and returns its GameScore.
     *
     * @param xNode the node to remove
     * @return the GameScore contained in the removed node
     */
    private GameScore remove(DNode xNode) {
        DNode prevNode = xNode.getPrev();
        DNode nextNode = xNode.getNext();
        // Bypass xNode.
        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);
        this.size--;
        return xNode.getElement();
    }

    /**
     * Searches for a node containing the specified GameScore.
     *
     * @param target the GameScore to find
     * @return the DNode containing the target GameScore, or null if not found
     */
    public DNode find(GameScore target) {
        DNode currNode = this.header.getNext();
        // Traverse until reaching the trailer (null element).
        while (currNode.getElement() != null) {
            if (currNode.getElement().equals(target)) {
                return currNode;
            }
            currNode = currNode.getNext();
        }
        return null;
    }

    /**
     * Inserts a new GameScore into the list in descending order.
     * If the list is empty or the new score is higher than or equal to the first score,
     * it is added to the beginning. If it's lower than or equal to the last score,
     * it is added to the end. Otherwise, it is inserted in the correct position.
     *
     * @param newScore the GameScore to add
     */
    public void add(GameScore newScore) {
        // Add at the beginning if list is empty or newScore is higher than or equal to the first element.
        if (this.isEmpty() || newScore.getScore() >= this.header.getNext().getElement().getScore()) {
            addFirst(newScore);
            return;
        }

        // Add at the end if newScore is lower than or equal to the last element.
        if (newScore.getScore() <= this.trailer.getPrev().getElement().getScore()) {
            addLast(newScore);
            return;
        }

        // Otherwise, traverse the list to find the correct insertion point.
        DNode currNode = this.header.getNext();
        while (currNode.getNext().getElement() != null && newScore.getScore() < currNode.getNext().getElement().getScore()) {
            currNode = currNode.getNext();
        }
        // Insert between currNode and its next node.
        addBetween(newScore, currNode, currNode.getNext());
    }

    /**
     * Deletes the first node containing the specified GameScore.
     *
     * @param target the GameScore to delete
     * @return the deleted DNode, or null if no matching node is found
     */
    public DNode delete(GameScore target) {
        DNode currNode = this.header.getNext();
        // Traverse until reaching the trailer.
        while (currNode.getElement() != null) {
            if (currNode.getElement().equals(target)) {
                // Remove the node and return it.
                remove(currNode);
                return currNode;
            }
            currNode = currNode.getNext();
        }
        return null;
    }

    /**
     * Demonstrates the functionality of the DoublyLinkedList class.
     * Creates a list of GameScores, adds random scores, displays the list,
     * searches for specific scores, and deletes specified entries.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
		Random rnd = new Random();
		// Create an instance of DoublyLinkedList.
		DoublyLinkedList gameScores = new DoublyLinkedList();

		// Add two GameScores with predetermined scores.
        gameScores.add(new GameScore("Player 1", 25));
        gameScores.add(new GameScore("Player 2", 75));
        // Add additional GameScores with random scores for players 3 through 10.
        for (int i = 3; i <= 10; i++) {
            gameScores.add(new GameScore("Player " + i, rnd.nextInt(100)));
        }

        // Display the current list of game scores.
        System.out.println("Game Scores:");
        gameScores.display();

        // Demonstrate the find method for two different GameScores.
        System.out.print("\nFound Player 1 w/ 25 Points: ");
        System.out.println(gameScores.find(new GameScore("Player 1", 25)) != null);
        System.out.print("Found Player 2 w/ 50 Points: ");
        System.out.println(gameScores.find(new GameScore("Player 2", 50)) != null);

        // Demonstrate the delete method by removing specific GameScores.
        System.out.println("\nRemoving Player 1: " + gameScores.delete(new GameScore("Player 1", 25)));
        System.out.println("Removing Player 2: " + gameScores.delete(new GameScore("Player 2", 75)));

        // Display the list of game scores after deletion.
        System.out.println("\nNew Game Scores:");
        gameScores.display();
	}
}