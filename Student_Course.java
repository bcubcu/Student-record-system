/*
Author: Rimsha Raheem 
Date: 28/10/2025 
File name: Student_Course.java

Statement of purpose:
Student_Course is derived from Student class. 
It handles coursework students information including enrolment type and course unit details,
and overrides the reportGrade method to display coursework student based grading..

Assumptions:Input 
-Enrolement type must be in Char "C"
-The student's information (Firstname,LastName and StudentID) should come from the Student class
-

Expected Output
-must display the the enrollment 
type "C", students name, last name,course unit , overall grade and final grade
- Displays overall mark as double
- Displays final grade as String
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
public class Student_Course extends Student {
    private String enrolmentType = "C";// Enrolment type identifier for coursework students — always "C".
    private Unit_Course courseUnit;// Composition: each coursework student is linked to a Unit_Course object.
    
    
    //Constructor to initialize a Student_Course object
    
    public Student_Course(String firstName, String lastName, long studentID,Unit_Course courseUnit ){
     super(firstName, lastName, studentID);//Calls the parent constructor Student
     this.courseUnit = courseUnit;  // Assign the course unit object
    }
    
    //Overrides the reportGrade() method from the Student class.
    //Prints the coursework student's enrolment details, marks, and grades.
    @Override
    public void reportGrade(){
       System.out.println();// For spacing between outputs
       System.out.println("Enrolment: " + enrolmentType);// Display enrolment type "C"
       System.out.println("Name: " + getFirstName() + " " + getLastName()); // Display students full name
       System.out.println("Student ID: "+ getStudentID());// Display unique student ID
       System.out.println("Course unit" + courseUnit.getUnitID());// Display course unit ID
       System.out.println("Overall mark" +courseUnit.getOverallMark()); // Display numeric mark
       System.out.println("Final Grade" + courseUnit.getFinalGrade()); // Display final letter grade
    }
    //Returns the overall mark of the coursework unit.
    public double getOverallMark(){
        return courseUnit.getOverallMark();
    }
    
    public String getFinalGrade(){
        return courseUnit.getFinalGrade();
    }
    
    //Returns the Unit_Course object associated with the student.
    public Unit_Course getCourseUnit() {
    return courseUnit;
    }
}
