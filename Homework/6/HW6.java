/*
Rento Saijo
William Tarimo
Data Structures
10 April 2025
Homework 6

This program demonstrates several recursive algorithms, including factorial calculation,
binary search, array reversal, repeated addition for multiplication, string reversal,
and palindrome checking, with sample usage in the main method.
*/

/**
 * HW6 provides several recursive methods for operations such as computing factorials,
 * performing binary search, reversing arrays, computing products via repeated addition,
 * reversing strings (anadrome), and checking if a string is a palindrome, along with a main
 * method to test the functionality of each method.
 */
public class HW6 {

    /**
     * Computes the factorial of a non-negative integer n using recursion.
     *
     * @param n a non-negative integer
     * @return the factorial of n
     */
    public static int factorial(int n) {
        // Factorial of 0 is defined as 1.
        if (n == 0) {
            return 1;
        }

        // Multiply n by the factorial of n-1.
        return n * factorial(n - 1);
    }

    /**
     * Searches for a target value in a sorted array using binary search.
     *
     * @param target the value to search for
     * @param nums   a sorted array of integers
     * @return the index of target if found; otherwise, -1
     */
    public static int binary_search(int target, int[] nums) {
        // Initialize search boundaries and call the helper method.
        return binary_search(target, nums, 0, nums.length - 1);
    }

    /**
     * Helper method for binary search that performs recursive search.
     *
     * @param target the value to search for
     * @param nums   a sorted array of integers
     * @param left   left boundary of the current search interval
     * @param right  right boundary of the current search interval
     * @return the index of target if found; otherwise, -1
     */
    public static int binary_search(int target, int[] nums, int left, int right) {
        // If left boundary exceeds right, the target is not present.
        if (left > right) {
            return -1;
        }

        // Calculate the middle index of the current interval.
        int middle = (left + right) / 2;

        // If the middle element is less than target, search in the right half.
        if (nums[middle] < target) {
            return binary_search(target, nums, middle + 1, right);
        }
        // If the middle element is greater than target, search in the left half.
        if (nums[middle] > target) {
            return binary_search(target, nums, left, middle - 1);
        }
        // Target found at the middle index.
        return middle;
    }

    /**
     * Reverses the elements of an array in place.
     *
     * @param nums an array of integers to be reversed
     */
    public static void reverse(int[] nums) {
        // Call the helper method with the full range of the array.
        reverse(nums, 0, nums.length - 1);
    }

    /**
     * Helper method that reverses a portion of an array recursively.
     *
     * @param nums  an array of integers
     * @param left  starting index for reversal
     * @param right ending index for reversal
     */
    public static void reverse(int[] nums, int left, int right) {
        // Continue swapping while left index is less than right index.
        if (left < right) {
            // Swap the elements at the left and right indices without temporary variable.
            // Will break if overflow with really large numbers, but it should be fine for our purposes.
            nums[left] += nums[right];
            nums[right] = nums[left] - nums[right];
            nums[left] -= nums[right];
            // Shrink the boundaries.
            reverse(nums, left + 1, right - 1);
        }
    }

    /**
     * Computes the product of two integers using recursion (repeated addition).
     *
     * @param n the positive multiplicand
     * @param m the positive multiplier
     * @return the product of n and m
     */
    public static int product(int n, int m) {
        // Base case: if multiplier is 0, the product is 0.
        if (m == 0) {
            return 0;
        }
        // Recursive case: add n to the product of n and (m-1).
        return n + product(n, m - 1);
    }

    /**
     * Returns the reverse of the given string.
     *
     * @param s the original string
     * @return a new string which is the reverse of s
     */
    public static String anadrome(String s) {
        // Base case: an empty string returns an empty string.
        if (s.length() == 0) {
            return "";
        }
        // Recursive case: append the last character to the reverse of the substring excluding the last character.
        return s.charAt(s.length() - 1) + anadrome(s.substring(0, s.length() - 1));
    }

    /**
     * Determines if a given string is a palindrome.
     *
     * @param s the string to check
     * @return true if s is a palindrome; otherwise, false
     */
    public static boolean palindrome(String s) {
        // Base case: strings of length 0 or 1 are palindromes.
        if (s.length() <= 1) {
            return true;
        }
        // If the first and last characters match, check the substring that excludes them.
        if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return palindrome(s.substring(1, s.length() - 1));
        }
        // Characters do not match, so s is not a palindrome.
        return false;
    }

    /**
     * Main method to test the functionality of the various recursive methods.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Test factorial method with sample inputs.
        System.out.println("Factorial of 0: " + factorial(0));
        System.out.println("Factorial of 5: " + factorial(5));

        // Create and initialize an array with values from 1 to 10.
        int[] myNums = new int[10];
        for (int i = 1; i <= 10; i++) {
            myNums[i - 1] = i;
        }
        // Print the array.
        System.out.print("\nSample Array: ");
        for (int myNum : myNums) {
            System.out.print(myNum + " ");
        }

        // Test binary search on the sorted array.
        System.out.println("\n\nIndex of 0: " + binary_search(0, myNums));
        System.out.println("Index of 5: " + binary_search(5, myNums));
        System.out.println("* Note that -1 means it was not found.");

        // Reverse the array and print the reversed array.
        reverse(myNums);
        System.out.print("\nReversed Array: ");
        for (int myNum : myNums) {
            System.out.print(myNum + " ");
        }

        // Test the product method using repeated addition.
        System.out.println("\n\n0 x 5: " + product(0, 5));
        System.out.println("5 x 10: " + product(1, 10));

        // Test anadrome (string reversal) method.
        System.out.println("\nAnadrome of pots&pans: " + anadrome("pots&pans"));
        System.out.println("Anadrome of Rento: " + anadrome("Rento"));

        // Test the palindrome method.
        System.out.println("\nIs \"racecar\" a palindrome? " + palindrome("racecar"));
        System.out.println("Is \"Rento\" a palindrome? " + palindrome("Rento"));
    }
}