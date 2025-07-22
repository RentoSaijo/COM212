/*
Rento Saijo
William Tarimo
Data Structures
23 Febbruary 2025
Assignment 2

This program defines the OrdArray class and tests its methods in the OrderedArrayTest class.
*/

// Import Random class for generating random numbers
import java.util.Random;

/**
 * The OrdArray class represents an ordered array of long values.
 * It maintains its elements in sorted order and provides operations such as
 * binary search, insertion (using binary search), deletion, sorting, merging with another
 * ordered array, and removing duplicates.
 */
public class OrdArray {

    // Instance variable for underlying array (container) to store values
    private long[] a;
    // Instance variable for number of valid elements in the array
    private int nElems;
    
    /**
     * Constructs an OrdArray with a specified maximum capacity.
     *
     * @param max the maximum number of elements the array can hold
     */
    public OrdArray(int max) {
        // Initialize the underlying array with the given maximum capacity
        this.a = new long[max];
        // Set the initial number of valid elements to 0
        this.nElems = 0;
    }

     /**
     * Constructs an OrdArray with a specified maximum capacity and optionally
     * populates the first 'count' elements with random long values in the range [-100, 100].
     *
     * @param max    the maximum capacity of the underlying array
     * @param random if true, populate the array with random values; otherwise, leave default zeros
     * @param count  the number of elements to initialize and mark as valid
     */
    public OrdArray(int max, boolean random, int count) {
        // Initialize the underlying array with the given maximum capacity
        this.a = new long[max];

        // Create an instance of Random to generate random numbers
        Random rnd = new Random();

        // If the random flag is set to true
        if (random) {
            // initialize the first 'count' elements with random values
            for (int i = 0; i < count; i++) {
                // Generate a random long between -100 and 100
                this.a[i] = rnd.nextLong(201) - 100;
            }
        }

        // Set the number of active elements in the array.
        this.nElems = count;
    }

    /**
     * Displays the current elements in the array.
     */
    public void display() {
        // Loop over each valid element in the array
        for (int j = 0; j < this.nElems; j++) {
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
     * Uses binary search to find the index of the specified searchKey in the ordered array.
     * Returns the index if found; nElems if not found.
     *
     * @param searchKey the value to search for
     * @return the index of searchKey if found; otherwise, returns nElems
     */
    public int find(long searchKey) {
        // Lower bound index for the binary search
        int lowerBound = 0;
        // Upper bound index for the binary search
        int upperBound = this.nElems - 1;
        // Middle index of the current search range
        int mid;

        // Continue while there is a valid range to search
        while (lowerBound <= upperBound) {
            // Calculate the midpoint
            mid = (lowerBound + upperBound) / 2;
            // Check if the searchKey is at the midpoint
            if (this.a[mid] == searchKey) {
                return mid;
            }
            // If the searchKey is greater than the midpoint element
            else if (this.a[mid] < searchKey) {
                // adjust lowerBound
                lowerBound = mid + 1;
            }
            // Otherwise
            else {
                // adjust upperBound
                upperBound = mid - 1;
            }
        }
        return this.nElems;
    }

    /**
     * Inserts a new value into the ordered array while maintaining its sorted order.
     * Uses binary search to find the correct insertion point.
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
        // Lower bound index for the binary search
        int lowerBound = 0;
        // Upper bound index for the binary search
        int upperBound = this.nElems - 1;
        // Middle index of the current search range
        int mid;

        // Continue while there is a valid range to search
        while (lowerBound <= upperBound) {
            // Calculate the midpoint
            mid = (lowerBound + upperBound) / 2;
            // If the mid element is less than or equal to the value
            if (this.a[mid] <= value) {
                // adjust the lowerBound
                lowerBound = mid + 1;
            // Otherwise
            } else {
                // adjust the upperBound
                upperBound = mid - 1;
            }
        }
        
        // Shift elements to the right of lowerBound
        for (int k = this.nElems; k > lowerBound; k--) {
            this.a[k] = this.a[k - 1];
        }

        // Insert the new value at the found index
        this.a[lowerBound] = value;
        // Increment the count of valid elements
        this.nElems++;
    }

    /**
     * Deletes the first occurrence of a specific value from the ordered array.
     * Uses binary search to locate the item.
     *
     * @param value the value to delete
     * @return true if the value was found and deleted; false otherwise
     */
    public boolean delete(long value) {
        // locate the value
        int index = find(value);
        // If the value is not found
        if (index == this.nElems) {
            return false;
        }

        // Shift elements left to fill the gap left by the deleted element
        for (int k = index; k < this.nElems - 1; k++) {
            this.a[k] = this.a[k + 1];
        }

        // Decrement the count of valid elements
        this.nElems--;
        return true;
    }

    /**
     * Sorts the array in ascending order using an optimized insertion sort algorithm.
     * This method shifts all larger elements one position to the right and then inserts
     * the current element into its correct slot, rather than swapping repeatedly.
     */
    public void sort() {
        // Loop through each element starting from index 1 since the element at index 0 is considered already sorted.
        for (int i = 1; i < this.nElems; i++) {
            // Set aside the current element to be inserted in the sorted portion.
            long temp = this.a[i];
            // Initialize j to the current index.
            int j = i;
            // Loop until we reach the beginning or find an element not greater than temp.
            while (j > 0 && this.a[j - 1] > temp) {
                // Shift the element one position to the right.
                this.a[j] = this.a[j - 1];
                // Move the insertion index one position to the left.
                j--;
            }
            // Place the temporary element into its correct position.
            this.a[j] = temp;
        }
    }

    /**
     * Merges an already-ordered array b into the current ordered array, maintaining sorted order.
     * This is achieved in linear time using a two-pointer merge technique.
     * A new array is created to hold the merged result.
     * 
     * @param b an already sorted array of longs to be merged into the current array
     */
    public void merge(long[] b) {
        // Number of valid elements in array a
        int m = this.nElems;
        // Number of elements in array b
        int n = b.length;
        // Create a temporary array to hold the merged result with size equal a.length
        long[] c = new long[this.a.length];

        if (this.a.length < m + n) {
            System.out.println("Cannot merge since it will exceed the capacity of the array.");
            return;
        }
        
        // Pointer for the array a
        int i = 0;
        // Pointer for the array b
        int j = 0;
        // Pointer for the merged array c
        int k = 0;
        
        // Merge elements from a and b until one of the arrays is exhausted
        while (i < m && j < n) {
            // Comparing current elements of a and b, if element in a is smaller
            if (this.a[i] <= b[j]) {
                // place it in c
                c[k] = this.a[i];
                // Move to the next element in a
                i++;
            // If element in b is smaller
            } else {
                // place it in c
                c[k] = b[j];
                // Move to the next element in b
                j++;
            }
            // Move to the next position in the merged array
            k++;
        }
        
        // Copy any remaining elements from a, if any
        while (i < m) {
            c[k] = this.a[i];
            i++;
            k++;
        }
        
        // Copy any remaining elements from b, if any
        while (j < n) {
            c[k] = b[j];
            j++;
            k++;
        }
        
        // Replace the original array with the merged array
        this.a = c;
        // Update the number of valid elements to reflect the merged result
        nElems = m + n;
    }

    /**
     * EXTRA CREDIT
     * 
     * Removes duplicate values from the ordered array.
     * Since the array is sorted, duplicates are adjacent.
     * The method shifts unique elements to the beginning and updates nElems.
     */
    public void removeDuplicates() {
        // Index to place the next unique element.
        int uniqueIndex = 1;
        // Loop through the array starting from the second element.
        for (int i = 1; i < this.nElems; i++) {
            // If the current element is not equal to the last unique element
            if (this.a[i] != this.a[uniqueIndex - 1]) {
                // move it to the unique section
                this.a[uniqueIndex] = this.a[i];
                // Increase the count of unique elements.
                uniqueIndex++;
            }
        }
        // Update nElems
        this.nElems = uniqueIndex;
    }
}

/**
 * The OrderedArrayTest class tests all methods of the OrdArray class.
 * It demonstrates insertion, finding, deletion, merging, duplicate removal, sorting,
 * and the use of the constructor that initializes with random values.
 */
class OrderedArrayTest {
    public static void main(String[] args) {
        // Create an OrdArray with capacity for 20 elements
        OrdArray ordArray = new OrdArray(20);

        // Insert elements into the array
        ordArray.insert(55);
        ordArray.insert(23);
        ordArray.insert(78);
        ordArray.insert(12);
        ordArray.insert(34);
        ordArray.insert(78);
        ordArray.insert(90);
        ordArray.insert(23);

        System.out.println("After insertions:");
        ordArray.display();
        System.out.println("Size: " + ordArray.size());
        System.out.println();

        // Test the find method (expected to find)
        int idx = ordArray.find(78);
        if (idx < ordArray.size()) {
            System.out.println("Found 78 at index: " + idx);
        } else {
            System.out.println("78 not found.");
        }

        // Test the find method (expected to not find)
        idx = ordArray.find(100);
        if (idx < ordArray.size()) {
            System.out.println("Found 100 at index: " + idx);
        } else {
            System.out.println("100 not found.");
        }
        System.out.println();

        // Test the delete method
        System.out.println("Delete 23: " + ordArray.delete(23));
        System.out.println("After deletion:");
        ordArray.display();
        System.out.println("Size: " + ordArray.size());
        System.out.println();

        // Test the merge method
        // Create a pre-ordered array to merge with ordArray.
        long[] mergeArray = {5, 35, 80, 95};
        ordArray.merge(mergeArray);
        System.out.println("After merge with 5, 35, 80, and 95:");
        ordArray.display();
        System.out.println("Size: " + ordArray.size());
        System.out.println();

        // Test the removeDuplicates method
        ordArray.removeDuplicates();
        System.out.println("After duplicate removal:");
        ordArray.display();
        System.out.println("Size: " + ordArray.size());
        System.out.println();

        // Test the sort method
        // Create a new OrdArray object that are made up of random longs
        OrdArray rndArray = new OrdArray(20, true, 15);
        System.out.println("Create a separate OrdArray object with random longs.\nBefore sort:");
        rndArray.display();
        System.out.println("Size: " + rndArray.size());
        // Sort
        rndArray.sort();
        System.out.println("After sort:");
        rndArray.display();
        System.out.println("Size: " + rndArray.size());
    }
}