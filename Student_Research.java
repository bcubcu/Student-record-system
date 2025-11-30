/*
Author: Rimsha Raheem 
Date: 28/10/2025 
File name: Student_Research.java

Statement of purpose:
Student_Research is derived from Student class. It handles 
student who aare enrolled in research including enrolment type and research unit details,
and overrides the reportGrade method to display research students based grading.

Assumptions:Input 
-Enrolement type must be in Char "R"
-The student's information (Firstname,LastName and StudentID) should come from the Student class
-Student_Research class retrieves and displays research students info

Expected Output
-must display the the enrollment 
type "R", students name, last name, overall grade and final grade
- Displays the overall research mark (formatted to 2 decimal places)
- Displays the final grade for the research student

*/
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student;

/**
 *
 * @author rimsh
 */
public class Student_Research extends Student {
    // Instance variable storing enrolment type. For research students, it is always "R".
    private String enrolmentType = "R";
    
    // Composition: each research student is associated with one Unit_Research object.
    private Unit_Research researchUnit;
    
    
    //Constructor to initialize a Student_Research object.
    public Student_Research(String firstName, String lastName, long studentID, Unit_Research researchUnit){
        super(firstName, lastName, studentID);//calls the parent constructor (Student) 
        this.researchUnit = researchUnit; // Assign the research unit to the student
    }
    
    //Overrides the reportGrade() method from the Student class.
    //Prints out research student details, overall mark, and final grade.
    @Override
    public void reportGrade(){
        System.out.println();// For spacing between outputs
        System.out.println("Enrolment: " + enrolmentType);// Displays enrolment type "R"
        System.out.println("Name: " + getFirstName()+ " " +getLastName());//// Displays student full name
        System.out.println("Student ID: " + getStudentID());// Displays student ID
        System.out.println("Overall Mark: " + String.format("%.2f", researchUnit.getOverallMark()));// Prints mark with 2 decimal precision
        System.out.println("Final Grade: " + researchUnit.getFinalGrade());// Displays letter grade
    }
    
    // returns overall mark calculated in the Unit_Research class.
    public double getOverallMark(){
        return researchUnit.getOverallMark();
    }
    
}
