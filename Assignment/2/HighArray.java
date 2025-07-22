/*
Rento Saijo
William Tarimo
Data Structures
23 Febbruary 2025
Assignment 2

This program defines the HighArray class and tests its methods in the HighArrayTest class.
*/

/**
 * The HighArray class represents an array of long integers that provides basic operations
 * such as insertion, search, deletion, retrieving the maximum element, removing the maximum,
 * reversing the array, and checking for distinct elements.
 */
public class HighArray {

    // Instance variable for underlying array (container) to store values
    private long[] a;
    // Instance variable for number of valid elements in the array
    private int nElems;

    /**
     * Constructs a HighArray with a specified maximum capacity.
     *
     * @param max the maximum number of elements the array can hold
     */
    public HighArray(int max) {
        this.a = new long[max];
        this.nElems = 0;
    }

    /**
     * Displays the current elements in the array.
     */
    public void display() {
    	// Loop over each valid element in the array
        for (int j = 0; j < nElems; j++) {
        	// Print the current element followed by a space
            System.out.print(this.a[j] + " ");
        }
        // Print a newline character (for formatting)
        System.out.println();
    }

    /**
     * Returns the number of elements currently stored in the array.
     *
     * @return the size of the array (number of elements)
     */
    public int size() {
        return this.nElems;
    }

    /**
     * Searches for a given value in the array.
     *
     * @param searchKey the value to search for
     * @return true if the value is found; false otherwise
     */
    public boolean find(long searchKey) {
    	// Loop over each valid element in the array
        for (int j = 0; j < this.nElems; j++) {
        	// If the element matches the searchKey
            if (this.a[j] == searchKey)
            	// return true immediately
                return true;
        }
        return false;
    }

    /**
     * Inserts a new value into the array; if the array is full, prints an error message.
     *
     * @param value the value to insert
     */
    public void insert(long value) {
    	// Check if the array is full
        if (this.nElems == this.a.length) {
        	// Print an error message
            System.out.println("Cannot insert; the array is full.");
            return;
        }
        // Insert the new value at the next available index
        this.a[nElems] = value;
        // Increment the count of valid elements
        nElems++;
    }

    /**
     * Deletes the first occurrence of the specified value from the array.
     * Shifts subsequent elements to fill the gap.
     *
     * @param value the value to delete
     * @return true if the value was found and deleted; false otherwise
     */
    public boolean delete(long value) {
    	// Iterate over each valid element
        for (int j = 0; j < nElems; j++) {
        	// If the current element matches the value to be deleted
            if (value == this.a[j]) {
                // shift all elements after the found index one position to the left
                for (int k = j; k < nElems - 1; k++) {
                    this.a[k] = this.a[k + 1];
                }
                // Decrement the count of valid elements
                nElems--;
                return true;
            }
        }
        return false;
    }

    /**
     * Finds and returns the maximum value in the array.
     *
     * @return the maximum value; -1 if the array is empty
     */
    public long getMax() {
    	// If the array is empty
        if (this.nElems == 0) {
            return -1;
        }

        // Assume the first element is the maximum initially
        long max = this.a[0];

        // Loop through the rest of the valid elements
        for (int i = 1; i < this.nElems; i++) {
        	// If the current element is greater than the current maximum
            if (this.a[i] > max) {
            	// update max
                max = this.a[i];
            }
        }
        return max;
    }

    /**
     * Removes and returns the maximum value from the array.
     * After removal, shifts elements to fill the gap.
     *
     * @return the removed maximum value; -1 if the array is empty
     */
    public long removeMax() {
    	// If the array is empty
        if (this.nElems == 0) {
            return -1;
        }

        // Assume the first element is the maximum initially
        long max = this.a[0];
        int maxIndex = 0;

        // Loop through the rest of the valid elements
        for (int i = 1; i < this.nElems; i++) {
        	// If the current element is greater than the current maximum
            if (this.a[i] > max) {
            	// update max
                max = this.a[i];
                // update maxIndex
                maxIndex = i;
            }
        }
        
        // Shift elements left to remove the max element
        for (int j = maxIndex; j < this.nElems - 1; j++) {
            this.a[j] = this.a[j + 1];
        }
        // Decrement the count of valid elements
        this.nElems--;
        return max;
    }

    /**
     * Reverses the order of elements in the array.
     */
    public void reverse() {
    	// Loop through half of the valid elements
        for (int i = 0; i < this.nElems / 2; i++) {
        	// Store the element from the left side in a temporary variable
            long left = this.a[i];
            // Replace the left side element with the corresponding right side element
            this.a[i] = this.a[this.nElems - 1 - i];
            // Assign the temporary variable to the corresponding right side position
            this.a[this.nElems - 1 - i] = left;
        }
    }

    /**
     * Checks if all elements in the array are distinct.
     *
     * @return true if all elements are distinct; false otherwise
     */
    public boolean allDistinct() {
    	// Loop through each element except the last one
        for (int i = 0; i < this.nElems - 1; i++) {
        	// Store the current element to compare with subsequent elements
            long val = this.a[i];
            // Loop through each element that comes after i
            for (int j = i + 1; j < this.nElems; j++) {
            	// If a duplicate is found
                if (this.a[j] == val) {
                	// return false immediately
                    return false;
                }
            }
        }
        return true;
    }
}

/**
 * The HighArrayTest class tests all the methods provided by the HighArray class.
 */
class HighArrayTest {
    public static void main(String[] args) {
        // Create a HighArray with capacity for 10 elements
        HighArray arr = new HighArray(10);

        // Insert elements into the array
        arr.insert(80);
        arr.insert(70);
        arr.insert(60);
        arr.insert(50);
        arr.insert(30);
        arr.insert(40);
        arr.insert(10);
        arr.insert(20);

        System.out.println("After insertions:");
        arr.display();
        System.out.println("Size: " + arr.size());
        System.out.println();

        // Test the find method
        System.out.println("Find 30: " + arr.find(30));
        System.out.println("Find 100: " + arr.find(100));
        System.out.println();

        // Test the getMax method
        System.out.println("Find max: " + arr.getMax());
        System.out.println();

        // Test the removeMax method
        arr.removeMax();
        System.out.println("After max removal:");
        arr.display();
        System.out.println("Size: " + arr.size());
        System.out.println();

        // Test the delete method
        System.out.println("Deleting element 30: " + arr.delete(30));
        System.out.println("After deletion:");
        arr.display();
        System.out.println("Size: " + arr.size());
        System.out.println();

        // Test the reverse method
        arr.reverse();
        System.out.println("After reversal:");
        arr.display();
        System.out.println("Size: " + arr.size());
        System.out.println();

        // Test the allDistinct method
        System.out.println("All distinct: " + arr.allDistinct());
        System.out.println();

        // Insert a duplicate element to test allDistinct again
        arr.insert(10);
        System.out.println("After inserting a duplicate (10):");
        arr.display();
        System.out.println("Size: " + arr.size());
        System.out.println("All distinct: " + arr.allDistinct());
        System.out.println();

        // Test insertion in a full array scenario
        HighArray fullArr = new HighArray(5);
        fullArr.insert(1);
        fullArr.insert(2);
        fullArr.insert(3);
        fullArr.insert(4);
        fullArr.insert(5);
        System.out.println("Full array:");
        fullArr.display();
        System.out.println("Size: " + fullArr.size());
        System.out.println("Attempting to insert into full array:");
        fullArr.insert(6);
    }
}