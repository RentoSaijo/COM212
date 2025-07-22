/*
Rento Saijo
Adam Teryek
William Tarimo
15 April 2025
Assignment 3

This program provides methods to verify if a sequence of brackets are correctly matched.
*/

/**
 * The BracketMatcher class provides methods to verify if a sequence of brackets
 * are correctly matched. It supports matching for the following pairs of brackets:
 * '(', ')', '[', ']', '{', '}', and '<', '>'.
 */
public class BracketMatcher {
    // Array of supported opening brackets.
    private final char[] OPEN = {
        '(',
        '[',
        '{',
        '<'
    };
    
    // Array of supported closing brackets corresponding to the opening brackets.
    private final char[] CLOSE = {
        ')',
        ']',
        '}',
        '>'
    };
    
    /**
     * Constructs a new BracketMatcher.
     */
    public BracketMatcher() {
    }

    /**
     * Determines if a given character is an opening bracket.
     *
     * @param c the character to check
     * @return true if the character is one of the defined opening brackets, false otherwise
     */
    public boolean isOpeningBracket(char c) {
        // Iterate through the OPEN array and compare each element with the provided character.
        for (char open : OPEN) {
            if (c == open) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if a given character is a closing bracket.
     *
     * @param c the character to check
     * @return true if the character is one of the defined closing brackets, false otherwise
     */
    public boolean isClosingBracket(char c) {
        // Iterate through the CLOSE array and compare each element with the provided character.
        for (char close : CLOSE) {
            if (c == close) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether an opening bracket corresponds to a closing bracket.
     *
     * @param open  the opening bracket character
     * @param close the closing bracket character
     * @return true if the closing bracket correctly matches the opening bracket, false otherwise
     */
    public boolean corresponds(char open, char close) {
        // Loop through the OPEN array to find the index of the provided opening bracket.
        for (int i = 0; i < OPEN.length; i++) {
            if (open == OPEN[i]) {
                // Check if the closing bracket at the same index in the CLOSE array matches.
                return close == CLOSE[i];
            }
        }
        return false;
    }

    /**
     * Checks if the brackets in a given string are properly matched.
     * If a mismatch is found, an error is printed.
     *
     * @param s the string containing the sequence of brackets to check
     * @return true if all brackets in the string are properly matched, false otherwise
     */
    public boolean checkBrackets(String s) {
        // Create a stack to hold opening brackets encountered in the string.
        SinglyLinkedStack brackets = new SinglyLinkedStack();
        
        // Iterate over each character in the string.
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            // If the character is an opening bracket, push it onto the stack.
            if (this.isOpeningBracket(current)) {
                brackets.push(current);
            }
            // If the character is a closing bracket.
            else if (this.isClosingBracket(current)) {
                // Check if the bracket at the top of the stack corresponds to the closing bracket.
                if (this.corresponds(brackets.top(), current)) {
                    // If they correspond, pop the opening bracket off the stack.
                    brackets.pop();
                } else {
                    // If there is a mismatch, print an error message with the character and its position.
                    System.out.println("Error: " + current + " at position " + i);
                    return false;
                }
            }
        }
        // Return true if the stack is empty (i.e. all opening brackets have been matched).
        return brackets.isEmpty();
    }
}