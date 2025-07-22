/*
Rento Saijo
Adam Teryek
William Tarimo
15 April 2025
Assignment 3

This program prompts the user for a string and checks whether
all the brackets in the string are correctly matched.
*/

import java.util.Scanner;

/**
 * The BracketMatchApp class serves as the entry point for the bracket matching application.
 * It prompts the user to input strings and uses the BracketMatcher to check whether the brackets
 * in the provided strings are properly matched.
 */
public class BracketMatchApp {
    /**
     * The main method that drives the application.
     *
     * @param args command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // A constant string used as a visual separator for output formatting.
        final String FORMATBAR = "------------------------------";
        
        // Create an instance of BracketMatcher to check bracket matching.
        BracketMatcher mb = new BracketMatcher();
        
        // Create a Scanner to read user input from the console.
        Scanner sc = new Scanner(System.in);
        
        // Variable to control the main loop of the application.
        boolean on = true;
        
        // Main loop: continue running as long as 'on' is true.
        while (on) {
            // Prompt the user to enter a string to check for matching brackets.
            System.out.println(FORMATBAR + "\nEnter a string to check for matching brackets:");
            String s = sc.nextLine();
            
            // Use BracketMatcher to check if the brackets in the input string are properly matched.
            if (mb.checkBrackets(s)) {
                System.out.println("All the brackets match up properly :)");
            } else {
                System.out.println("Not all the brackets match up properly :(");
            }
            System.out.println(FORMATBAR);
            
            // Inner loop to validate the user's decision to continue or exit.
            boolean invalid = true;
            while (invalid) {
                // Ask the user if they want to check another string.
                System.out.println("Would you like to check another string? (Y/N)");
                // Read the user input, convert it to uppercase for easier comparison.
                String yn = sc.nextLine().toUpperCase();
                if (yn.equals("Y")) {
                    // If the user wants to continue, break out of the inner loop.
                    invalid = false;
                } else if (yn.equals("N")) {
                    // If the user wants to exit, break out of both loops and print a closing format bar.
                    invalid = false;
                    on = false;
                    System.out.println(FORMATBAR);
                }
                // If input is neither Y nor N, the loop continues asking for valid input.
            }
        }
        
        // Close the scanner.
        sc.close();
    }
}