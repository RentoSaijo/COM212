/*
Rento Saijo
William Tarimo
Data Structures
4 Febbruary 2025
Homework 2

This code defines a Student class with a name and grade,
and demonstrates creation, modification, and display of
an array of Student objects using user input.
*/

// import scanner
import java.util.Scanner;

public class Student {
    // instance variables
    private String name;
    private int grade;

    // constructor
    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    // get name of student
    public String getName() {
        return this.name;
    }

    // set name of student
    public void setName(String name) {
        this.name = name;
    }

    // get grade of student
    public int getGrade() {
        return this.grade;
    }

    // set grade of student
    public void setGrade(int grade) {
        this.grade = grade;
    }

    // override toString() from Object superclass
    public String toString() {
        return this.name + "\'s grade: " + this.grade;
    }

    // main method
    public static void main(String[] args) {
        // create an array of 5 Student objects
        Student[] myStudents = new Student[5];
        // populate the array
        fillArray(myStudents);

        // verify
        System.out.println("\nStudents after fillArray():");
        for (Student student:myStudents) {
            System.out.println(student);
        }

        // change one student’s name
        System.out.println("\nChanging " + myStudents[0].getName() + "\'s name to Rento...");
        myStudents[0].setName("Rento");
        // change another student’s grade
        System.out.println("Changing " + myStudents[1].getName() + "\'s grade to 99...");
        myStudents[1].setGrade(99);

        // verify changes
        System.out.println("\nStudents after changes:");
        for (Student student:myStudents) {
            System.out.println(student);
        }
    }

    // method to fill array
    public static void fillArray(Student[] students) {
        // initialize & declare scanner
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < students.length; i++) {
            // collect student's name
            System.out.print("Enter name for student " + (i + 1) + ": ");
            String name = sc.nextLine();

            // collect student's grade
            int grade = 0;
            boolean isValid = false;
            while (!isValid) {
                System.out.print("Enter grade for " + name + ": ");
                try {
                    // attempt to convert the user input to an integer
                    grade = Integer.parseInt(sc.nextLine());
                    // exit loop if conversion is possible
                    isValid = true;
                } catch (NumberFormatException e) {
                    // inform user to try again if conversion is not possible
                    System.out.println("Invalid input! Please enter a valid integer for the grade.");
                }
            }

            // create new Student object and store in the array
            students[i] = new Student(name, grade);
        }
        // close scanner
        sc.close();
    }
}