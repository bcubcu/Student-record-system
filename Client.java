/*
Author: Rimsha Raheem 
Date: 28/10/2025 
File name: Client.java

Statement of purpose:
This is the main driver class for the student management system. 
It loads student data (Course and Research students) from a CSV file, 
stores them in an ArrayList, and allows the user to manage, analyze, 
and save student records via a menu-driven interface. 
Operations include displaying students, removing records, sorting by ID, 
and saving sorted data to a CSV file.

Assumptions:Input 
-Program does not proceed until valid input is given
-Inputs outside of menu options show error message
-Shows invalid or missing data in the .CSV file
-All student details are read from Student.csv
-The program continues looping until the user chooses to quit.

Expected Output
-Displays student information and menu options.
-Loads and validates data from CSV file.
-Displays student details, grades, and average analysis.
-Reports confirmation before deleting student records.
-Sorts students by ID using insertion sort and saves to CSV file.


*/
package student;

import java.util.*;
import java.io.*;

public class Client {
    private static ArrayList<Student> students = new ArrayList<>();
    private static boolean isSorted = false; //Flag to track sorting status 

    public static void main(String[] args) {
        StudentInfo();                     // show student/program info
        loadFromCSV("student.csv");        // load test data from this file
        menu();                             //menul loop
    }
    
   
    
    public static void StudentInfo() {
        System.out.println("Student: Rimsha Raheem");
        System.out.println("StudentID : 35170856");
        System.out.println("Mode: Internal");
        System.out.println("Tutor: Rajasree Rajamohanan");
        System.out.println("Tutorial Day: 28/10/2025 ");
    }

    
    //Load students from a CSV file.

    //Course: C,first,last,id,unitID,level,a1,a2,a3,a4,exam
    //Research: R,first,last,id,proposal,dissertation
  
    public static void loadFromCSV(String filename) {
        File f = new File(filename);
        if (!f.exists()) { // Checks if file exists before reading
            System.out.println("Input file not found: " + filename);
            return;
        }

        try (Scanner input = new Scanner(f)) {
            while (input.hasNextLine()) {
                String line = input.nextLine().trim();
                if (line.isEmpty()) continue; // Skip empty lines
                String[] data = line.split(",");
                if (data.length == 0) continue;
                
                // Identify and handle Course or Research students
                if (data[0].equalsIgnoreCase("C")) {
                    // Validate sufficient fields for coursework
                    if (data.length < 11) {
                        System.out.println("Skipping malformed coursework line: " + line);
                        continue;
                    }
                    try {
                        // Parse assignment marks into an ArrayList
                        ArrayList <Double> assignments = new ArrayList<>();
                        assignments.add(Double.parseDouble(data[6]));
                        assignments.add(Double.parseDouble(data[7]));
                        assignments.add(Double.parseDouble(data[8]));
                        assignments.add(Double.parseDouble(data[9]));
                        
                        // Create Unit_Course object and then Student_Course
                        Unit_Course unitCourse = new Unit_Course(
                            data[4],
                            Integer.parseInt(data[5]),
                            assignments,
                            Double.parseDouble(data[10])
                        );
                        students.add(new Student_Course(data[1],data[2],Long.parseLong(data[3]),unitCourse));
                    } catch (NumberFormatException nfe) {
                        System.out.println("Number format error parsing line, skipping: " + line);
                    }
                } else if (data[0].equalsIgnoreCase("R")) {
                    // Validate sufficient fields for research
                    if (data.length < 6) {
                        System.out.println("Skipping malformed research line: " + line);
                        continue;
                    }
                    try {
                        //Create Unit_Research object and then Student_Research
                        Unit_Research researchUnit = new Unit_Research(
                            Double.parseDouble(data[4]),
                            Double.parseDouble(data[5])
                        );
                        students.add(new Student_Research(data[1], data[2],Long.parseLong(data[3]),researchUnit));
                    } catch (NumberFormatException nfe) {
                        System.out.println("Number format error parsing line, skipping: " + line);
                    }
                } else {
                    System.out.println("Unknown record type, skipping line: " + line);
                }
            }
            System.out.println("Students loaded from " + filename);
        } catch (Exception e) {
            System.out.println("Error loading CSV: " + e.getMessage());
        }
    }
    //Displays the menu options 
    public static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Quit");
        System.out.println("2. Remove student by ID");
        System.out.println("3. Display all students");
        System.out.println("4. Course students above/below average");
        System.out.println("5. Report grade by ID");
        System.out.println("6. Sort by student ID");
        System.out.println("7. Save sorted list to CSV");
        System.out.print("Enter your choice: ");
    }
    //main loop keeps prompting the user until they quit.
    public static void menu() {
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            
            //Input validation for numeric choice
            while (!input.hasNextInt()) {
                System.out.print("Please enter a number: ");
                input.next();
            }
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Exited from the program!");
                    break;
                case 2:
                    removeStudent(input);
                    break;
                case 3:
                    displayAll();
                    break;
                case 4:
                    courseAverageAnalysis();
                    break;
                case 5:
                    reportGradeByID(input);
                    break;
                case 6:
                    insertionSort();
                    break;
                case 7:
                    saveToCSV("Sorted_studentRecords.csv");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 1);// Loop ends when user selects Quit
    }
    
    // Removes a student based on student ID after confirmation.
    public static void removeStudent(Scanner input) {
        System.out.print("Enter student ID to remove: ");
        while (!input.hasNextLong()) {
            System.out.print("Please enter a valid numeric student ID: ");
            input.next();
        }
        long ID = input.nextLong();
        
        //To search through list to find matching student
        for (int i = 0; i < students.size(); i++) {
            Student theStudent = students.get(i);
            if (theStudent.getStudentID() == ID) {
                System.out.println("Are you sure you want to delete " + theStudent + " ? (Y/N)");
                String ans = input.next();
                if (ans.equalsIgnoreCase("Y")) {
                    students.remove(i);
                    System.out.println("Student record deleted successfully.");
                } else {
                    System.out.println("Delete cancelled.");
                }
                return;
            }
        }
        System.out.println("Student not found.");
    }
    
    //Displays grade information of all students.
    public static void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            s.reportGrade();
        }
    }
    //Calculates and displays how many coursework students scored above or below the average overall mark
    public static void courseAverageAnalysis() {
        double total = 0;
        int count = 0;
        
        //sum total marks of course students
        for (int i = 0; i < students.size(); i++) {
            Student studentAvg = students.get(i);
            if (studentAvg instanceof Student_Course) {
                total += ((Student_Course) studentAvg).getOverallMark();
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No coursework students found.");
            return;
        }

        double avg = total / count;
        int above = 0;
        int below = 0;

        //compares each coursework students mark with the average 
        for (int i = 0; i < students.size(); i++) {
            Student st = students.get(i);
            if (st instanceof Student_Course) {
                double mark = ((Student_Course) st).getOverallMark();
                if (mark >= avg) above++;
                else below++;
            }
        }

        System.out.printf("Average mark: %.2f%n", avg);
        System.out.println("Above/equal average: " + above + ", Below average: " + below);
    }

    // Insertion sort by studentID in ascending order
    public static void insertionSort() {
        for (int i = 1; i < students.size(); i++) {
            Student current = students.get(i);// "current" refers to the current student 
            int j = i - 1;//compares to the previous element
            
            //shifts elements greater than "current" student by one postion ahead
            while (j >= 0 && students.get(j).getStudentID() > current.getStudentID()) {
                students.set(j + 1, students.get(j));//shift the larger element to the right 
                j--;
            }
            //insert the "current" into its now corrected sorted position 
            students.set(j + 1, current);
        }
        isSorted = true;//indicates if the list is sorted properly
        System.out.println("ArrayList sorted by student ID.");
    }

    //Saves the sorted student list to a CSV file
    public static void saveToCSV(String filename) {
        //Prevents saving if the list is unsorted.
        if (!isSorted) {
            System.out.println("File should be sorted before saving");
            return;
        }

        try (PrintWriter output = new PrintWriter(new FileWriter(filename))) {
 
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                if (s instanceof Student_Course) {
                    Student_Course sc = (Student_Course) s;
                    Unit_Course unit = sc.getCourseUnit();
                    output.print("C," + sc.getFirstName() + "," + sc.getLastName() + "," + sc.getStudentID() + ",");
                    output.print(unit.getUnitID() + "," + unit.getProgress() + ",");

                    
                    ArrayList<Double> assignments = unit.getAssignments();
                    for (int j = 0; j < assignments.size(); j++) {
                    output.print(assignments.get(j) + ",");
                    }
                    output.println(String.format("%.2f", unit.getOverallMark())+ "," + unit.getFinalGrade());
                
                
                
                } else if (s instanceof Student_Research) {
                    Student_Research sr = (Student_Research) s;
                    double overall = sr.getOverallMark();
                    String grade = gradeFromMark(overall); // compute grade for research student
                    output.println("R," + sr.getFirstName() + "," + sr.getLastName() + "," + sr.getStudentID()+ "," + String.format("%.2f", overall) + "," + grade);
                }
            
            }
            System.out.println("Saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    // helper to convert numeric mark to grade string
    private static String gradeFromMark(double mark) {
        if (mark >= 80) return "HD";
        if (mark >= 70) return "D";
        if (mark >= 60) return "C";
        if (mark >= 50) return "P";
        return "N";
    }

    // search by ID and report grade
    public static void reportGradeByID(Scanner input) {
        System.out.print("Enter student ID: ");
        while (!input.hasNextLong()) {
            System.out.print("Please enter a valid numeric student ID: ");
            input.next();
        }
        long id = input.nextLong();
        
        // Search for the student and call reportGrade() if found
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getStudentID() == id) {
                s.reportGrade();
                return;
            }
        }
        System.out.println("Student not found.");
    }
}