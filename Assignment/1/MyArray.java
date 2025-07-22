/*
Rento Saijo
William Tarimo
Data Structures
9 Febbruary 2025
Assignment 1

This program defines the MyArray class with a constructor and several methods
and tests these methods in the MyArrayTest class.
*/

/**
 * The MyArray class provides utility methods to perform various operations on arrays.
 * It includes methods to find the maximum and minimum values in an integer array,
 * calculate the sum and average of its elements, and find the oldest person given
 * parallel arrays of names and ages.
 */
public class MyArray {

    /**
     * Default constructor for the MyArray class.
     */
    public MyArray() {
    }

    /**
     * Finds the maximum value in an array of integers.
     *
     * @param nums The array of integers to search.
     * @return The maximum integer found in the array.
     */
    public int findMax(int[] nums) {
    	// Initialize max to the first element in the array
        int max = nums[0];
        // Iterate through each element in the array
        for (int num : nums) {
        	// If the current number is greater than max
            if (num > max) {
            	// update max to the current number
                max = num;
            }
        }
        // Return the maximum value found
        return max;
    }

    /**
     * Finds the minimum value in an array of integers.
     *
     * @param nums The array of integers to search.
     * @return The minimum integer found in the array.
     */
    public int findMin(int[] nums) {
    	// Initialize min to the first element in the array
        int min = nums[0];
        // Iterate through each element in the array
        for (int num : nums) {
        	// If the current number is less than min
            if (num < min) {
            	// update min to the current number
                min = num;
            }
        }
        // Return the minimum value found
        return min;
    }

    /**
     * Calculates the total sum of all integers in an array.
     *
     * @param nums The array of integers.
     * @return The sum of all integers in the array.
     */
    public int total(int[] nums) {
    	// Initialize total to 0
        int total = 0;
        // Iterate through each element in the array
        for (int num : nums) {
        	// Add the current element to the total sum
            total += num;
        }
        // Return the computed total
        return total;
    }

    /**
     * Calculates the average value of the integers in an array.
     *
     * @param nums The array of integers.
     * @return The average of the integers as a double.
     */
    public double average(int[] nums) {
    	// Calculate the total sum using the total method
        int total = total(nums);
        // Compute and return the average as a double
        return (double) total / nums.length;
    }

    /**
     * Finds the name of the oldest person given parallel arrays of names and ages.
     *
     * @param names The array of names corresponding to persons.
     * @param ages  The array of ages corresponding to persons.
     * @return The name of the oldest person, or null if no match is found.
     */
    public String findOldestPerson(String[] names, int[] ages) {
    	// Find the maximum age using the findMax method
        int max = findMax(ages);
        // Iterate through the ages array
        for (int i = 0; i < ages.length; i++) {
        	// If the current age is equal to the maximum age
            if (ages[i] == max) {
            	// return the corresponding name from the names array
                return names[i];
            }
        }
        // Return null if no oldest person is found (this should not occur with valid input)
        return null;
    }
}

/**
 * The MyArrayTest class demonstrates the usage of the MyArray class methods.
 * It creates test data and prints the results of various operations.
 */
class MyArrayTest {
    /**
     * The main method to run tests for the MyArray class.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Test data for integer operations
        int[] testNums = {5, 10, 11, 54, 60, 12, 4, 8, 1, 20};
        // Test data for the ages of persons
        int[] testAges = {30, 25, 50, 12, 6, 10, 55, 36, 21, 31};
        // Test data for the names corresponding to the ages above
        String[] testNames = {"James", "Tom", "Jessica", "Jones", "Betty", "Ted", "Jane", "Bruce", "Clark", "Daniel"};

        // Create an instance of MyArray to test its methods
        MyArray testArray = new MyArray();

        // Display the results of various array operations using the MyArray methods
        System.out.println("The maximum value in the array is " + testArray.findMax(testNums));
        System.out.println("The minimum value in the array is " + testArray.findMin(testNums));
        System.out.println("The total sum of values in the array is " + testArray.total(testNums));
        System.out.println("The average value is " + testArray.average(testNums));
        System.out.println("The oldest person is " + testArray.findOldestPerson(testNames, testAges));
    }
}