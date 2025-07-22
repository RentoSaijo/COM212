/*
Rento Saijo
William Tarimo
Data Structures
4 March 2025
Homework 4

This program provides an implementation of a singly-linked list that manages GameScore objects in descending order,
including methods for adding, removing, searching, and displaying nodes; tests them with an example.
*/

// Import Random class for generating random numbers.
import java.util.Random;

/**
 * Implements a singly-linked list to store GameScore objects in descending order.
 * Provides methods for adding, removing, finding, and displaying nodes.
 */
public class SinglyLinkedList {
    // Reference to the first node (head) in the list.
    private SNode head;
    // Reference to the last node (tail) in the list.
    private SNode tail;
    // The number of nodes in the list.
    private int size;

    /**
     * Constructs an empty SinglyLinkedList.
     */
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Returns the number of nodes in the list.
     *
     * @return the size of the list
     */
    public int size() {
        return this.size;
    }

    /**
     * Checks whether the list is empty.
     *
     * @return true if the list is empty; false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the first GameScore in the list.
     *
     * @return the GameScore of the head node, or null if the list is empty
     */
    public GameScore first() {
        if (this.isEmpty()) {
            return null;
        }
        return this.head.getElement();
    }

    /**
     * Returns the last GameScore in the list.
     *
     * @return the GameScore of the tail node, or null if the list is empty
     */
    public GameScore last() {
        if (this.isEmpty()) {
            return null;
        }
        return this.tail.getElement();
    }

    /**
     * Adds a new GameScore to the beginning of the list.
     *
     * @param newScore the GameScore to add
     */
    public void addFirst(GameScore newScore) {
        // Create a new node whose next is the current head.
        SNode newNode = new SNode(newScore, this.head);
        // If list is empty, set tail to new node.
        if (this.isEmpty()) {
            this.tail = newNode;
        }
        // Set the new node as the head.
        this.head = newNode;
        this.size++;
    }

    /**
     * Adds a new GameScore to the end of the list.
     *
     * @param newScore the GameScore to add
     */
    public void addLast(GameScore newScore) {
        // Create a new node with no next node.
        SNode newNode = new SNode(newScore, null);
        // If list is empty, set head to new node.
        if (this.isEmpty()) {
            this.head = newNode;
        } else {
            // Otherwise, link the current tail to the new node.
            this.tail.setNext(newNode);
        }
        // Update tail to the new node.
        this.tail = newNode;
        this.size++;
    }

    /**
     * Removes and returns the first GameScore from the list.
     *
     * @return the removed GameScore, or null if the list is empty
     */
    public GameScore removeFirst() {
        if (this.isEmpty())
            return null;

        // Get the element from the head node.
        GameScore gameScore = this.head.getElement();
        // Move head pointer to the next node.
        this.head = this.head.getNext();
        size--;

        // If the list is now empty, set tail to null.
        if (this.isEmpty()) {
            this.tail = null;
        }

        return gameScore;
    }

    /**
     * Displays all GameScores in the list.
     * Prints each node's element to the console.
     */
    public void display() {
        SNode currNode = this.head;
        // Traverse the list and print each node.
        while (currNode != null) {
            System.out.println(currNode);
            currNode = currNode.getNext();
        }
    }

    /**
     * Searches for a node containing the specified GameScore.
     *
     * @param target the GameScore to find
     * @return the SNode containing the target GameScore, or null if not found
     */
    public SNode find(GameScore target) {
        SNode currNode = this.head;
        // Traverse the list and compare each node's element with the target.
        while (currNode != null) {
            if (currNode.getElement().equals(target)) {
                return currNode;
            }
            currNode = currNode.getNext();
        }
        return null;
    }

    /**
     * Inserts a new GameScore into the list in descending order.
     * The list is assumed to be maintained in descending order by score.
     *
     * @param newScore the GameScore to add
     */
    public void add(GameScore newScore) {
        // If list is empty or newScore has a score higher than or equal to the head,
        if (this.isEmpty() || newScore.getScore() >= this.head.getElement().getScore()) {
            // insert at the beginning.
            addFirst(newScore);
            return;
        }

        // If newScore has a score lower than or equal to the tail,
        if (newScore.getScore() <= this.tail.getElement().getScore()) {
            // insert at the end.
            addLast(newScore);
            return;
        }

        // Otherwise, find the correct insertion point in the middle.
        SNode currNode = this.head;
        while (currNode.getNext() != null && newScore.getScore() < currNode.getNext().getElement().getScore()) {
            currNode = currNode.getNext();
        }

        // Create a new node with the next reference pointing to the current node's next.
        SNode newNode = new SNode(newScore, currNode.getNext());
        currNode.setNext(newNode);
        this.size++;
    }

    /**
     * Deletes the first node containing the specified GameScore.
     *
     * @param gameScore the GameScore to delete
     * @return the removed SNode, or null if no matching node was found
     */
    public SNode delete(GameScore gameScore) {
        SNode currNode = this.head;
        SNode prevNode = null;
        
        // Traverse the list looking for a node with the matching GameScore.
        while (currNode != null) {
            if (currNode.getElement().equals(gameScore)) {
                // If the node to delete is the head node.
                if (prevNode == null) {
                    this.head = currNode.getNext();
                } else {
                    // Bypass the current node.
                    prevNode.setNext(currNode.getNext());
                }
                this.size--;
                return currNode;
            }
            prevNode = currNode;
            currNode = currNode.getNext();
        }
        // Return null if the element was not found.
        return null;
    }

    /**
     * Demonstrates the functionality of the SinglyLinkedList class.
     * Creates a list of GameScores, adds random scores, displays the list,
     * searches for specific scores, and deletes specified entries.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Random rnd = new Random();
        // Create an instance of SinglyLinkedList.
        SinglyLinkedList gameScores = new SinglyLinkedList();

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