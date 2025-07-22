/*
Rento Saijo
William Tarimo
Data Structures
28 January 2025
Homework 1
*/

public class HelloPrinter {
    public static void main(String[] args) {
        // call the for-loop version method
        printCount(5);
        // call the while-loop version method
        printCountWhile(5);
    }
    // For-loop version
    public static void printCount(int n) {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                // i is divisible by 2
                System.out.print((i / 2 + n) + " ");
            } else if (i % 3 == 0) {
                // i is divisible by 3
                System.out.print("0 ");
            } else {
                // otherwise
                System.out.print(i + " ");
            }
        }
        // start a new line for formatting (optional)
        System.out.println();
    }
    // While-loop version
    public static void printCountWhile(int n) {
        // counter
        int i = 0;
        while (i < 10) {
            if (i % 2 == 0) {
                // i is divisible by 2
                System.out.print((i / 2 + n) + " ");
            } else if (i % 3 == 0) {
                // i is divisible by 3
                System.out.print("0 ");
            } else {
                // otherwise
                System.out.print(i + " ");
            }
            // increment counter by 1
            i++;
        }
        // start a new line for formatting (optional)
        System.out.println();
    }
}