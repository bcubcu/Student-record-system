/*
Author: Rimsha Raheem 
Date: 28/10/2025 
File name: Unit.java

Statement of purpose:
Unit class is the parent class for Unit_research and Unit_Course
It defines the structure for unitsuni, including enrolment type and a basic
grade reporting method that will be overridden by derived classes.


Assumptions:input
-Each derived class will handle specific grading logic and override the reportFinalGrade() method.
Expected Output
-Displays a default grade report message if not overridden by subclasses.


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
// Stores the type of enrolment for the unit
public class Unit {
   private String enrolmentType;
   
   public Unit (String enrolmentType){
       this.enrolmentType = enrolmentType;
   }
   
   public String getEnrolmentType(){
       return enrolmentType;
   }
   
   // Displays the final grade report for the unit.
   public void reportFinalGrade(){
       System.out.println("Final grade report : NA");
   }
}
