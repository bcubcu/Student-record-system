/*
Author: Rimsha Raheem 
Date: 28/10/2025 
File name: Student.java

Statement of purpose:
The Student class serves as the parent class for Student_Research and Student_Course, encapsulating basic student information
first name, last name, and student ID, it can be accessed and modified through getters and setters
report grades default is "There is no grade here" that will be overridden by Student_Course and Student_Research.

Assumptions:Input 
-Each student has a unique student ID.
-Student IDs can not be negitive or decimal numbers
-Equals method is based on the studentID
-First name, last name and student ID must be passed through constructors and setters
-First name and last name can be empty set until given 
-Equals checks for douplecate students ID
-can not be directly accessed by other classes

Expected Output
-displays first name lastname and studentID
-Report grade prints out message if there is no grade given 

*/
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package student;

/**
 *
 * @author rimsh
 */
public class Student {
    // Each student has a first name, last name, and a unique student ID
    private String firstName;
    private String lastName;
    private long studentID;
    
    // Initializes all fields with default placeholder values
    public Student(){
        firstName = "noFirstName";
        lastName = "noLastName";
        studentID = 0L;
    }
    
    // Creates a student object with the given first name, last name, and student ID
    public Student(String firstName, String lastName, long studentID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
    }
    
    // Returns the student's first name
    public String getFirstName(){
        return firstName;
    }
    
    // Returns the student's last name
    public String getLastName(){
        return lastName;     
    }
    
    // Returns the student's ID number
    public long getStudentID(){
        return studentID;
    }
    
    // Sets the student's first name
    public void setFirstName(String firstName){
        this.firstName = firstName;
 
    }
    
    // Sets the student's last name
    public void setLastName( String lastName){
        this.lastName = lastName;
    }
    
    // Sets the student's ID
    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }
   
    // Displays a message when a student's grade is not specified.
    public void reportGrade(){
        System.out.println("There is no grade here");
    }
    
    //method that checks if another student object has the same student ID.
    public boolean equals(Student other){
        if( other == null) return false;        
        return this.studentID == other.studentID;
    }
    
    // Converts the student object into a readable text format for display.
    @Override
    public String toString(){
    return (firstName + " " + lastName + " Student ID: " + studentID);
    }
}
