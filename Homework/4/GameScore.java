/*
Rento Saijo
William Tarimo
Data Structures
4 March 2025
Homework 4

This program encapsulates a player’s name and score, providing getters, setters, and basic object comparison.
*/

/**
 * Represents the score of a game for a player.
 * Contains the player's name and score.
 */
public class GameScore {
    // Instance variable to store the player's name.
    private String name;
    // Instance variable to store the player's score.
    private int score;

    /**
     * Constructs a GameScore object with the specified name and score.
     *
     * @param n the name of the player
     * @param s the score of the player
     */
    public GameScore(String n, int s) {
        this.name = n;
        this.score = s;
    }

    /**
     * Returns the name of the player.
     *
     * @return the player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the score of the player.
     *
     * @return the player's score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Sets the player's name.
     *
     * @param n the new name of the player
     */
    public void setName(String n) {
        this.name = n;
    }

    /**
     * Sets the player's score.
     *
     * @param s the new score of the player
     */
    public void setScore(int s) {
        this.score = s;
    }

    /**
     * Returns a string representation of the GameScore object.
     * Format: (name, score)
     *
     * @return a string representing the GameScore
     */
    public String toString() {
        return "(" + this.name + ", " + this.score + ")";
    }

    /**
     * Compares this GameScore object with the specified GameScore object for equality.
     * Two GameScore objects are considered equal if they have the same name and score.
     *
     * @param other the other GameScore object to compare against
     * @return true if the two GameScore objects are equal; false otherwise
     */
    public boolean equals(GameScore other) {
        // Check if both objects are null.
        if (this == null && other == null)
            return true;
        // If either object is null,
        if (this == null || other == null)
            // they cannot be equal.
            return false;
        // Compare score and name values.
        return (this.score == other.getScore() && this.name.equals(other.getName()));
    }
}