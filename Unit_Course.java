/*
Author: Rimsha Raheem 
Date: 28/10/2025 
File name: Unit_Course.java

Statement of purpose:
unit_Course derived from Unit class. It handles 
course student four assignments and a final exam grades,it
calculates the overall mark, and determines the final grade based on determineGrade()
criteria with 60% weight for assignments and 40% for the final exam.

Assumptions:input
-4 assigemnt must be provided 
-Assignment marks must be stored in ArrayList
-course year progress should not be negititve

Expected Output
-Displays the unit ID, progress year, overall mark, and final grade 
  when reportFinalGrade() is called.


*/



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student;

import java.util.ArrayList;
/**
 *
 * @author rimsh
 */
public class Unit_Course extends Unit{
    private String unitID;
    private int progress;
    private ArrayList<Double> assignments;
    private double finalExam; 
    private double OverallMark;
    private String finalGrade;
    
    //constructor initializes the coursework unit data, calculates the overall mark and final grade.
    public Unit_Course(String unitID, int progress, ArrayList<Double> assignments, double finalExam){
        super("C");
        this.unitID = unitID;
        this.progress = progress;
        this.assignments = new ArrayList<>();
        //        // Validate assignment input to ensure exactly 4 marks are provided
        if(assignments != null && assignments.size() == 4 ){
            this.assignments.addAll(assignments);
        }else {
            // Throw error if 4 assignment marks are not given
            throw new IllegalArgumentException("4 assigemnt marks must be entered");
        
        }
        
        this.finalExam = finalExam;
        calculateOverallMark();
        determineGrade();// Determine final grade 
        
    }
    //alculates the overall mark using weighted averages
    private void calculateOverallMark(){
        double assignmentTotal = 0;
        
         // Loop through all assignments to sum their marks
        for (int i = 0; i < assignments.size();i++){
            assignmentTotal += assignments.get(i);
        }
        // Compute overall mark using weighted formula
        OverallMark = (assignmentTotal / 4) * 0.6 + finalExam * 0.4;
    }
    
    private void determineGrade(){
        if (OverallMark >= 80) finalGrade = "HD";// High Distinction
        else if (OverallMark >= 70) finalGrade = "D";// Distinction
        else if (OverallMark >= 60) finalGrade = "C";// Credit
        else if (OverallMark >= 50) finalGrade = "P";// Pass
        else finalGrade = "N";// Fail
                
    }
    
    public ArrayList<Double> getAssignments() {
    return assignments;
    }

    
    public double getOverallMark(){
        return OverallMark;
    }
    
    public String getFinalGrade(){
        return finalGrade;
    }
    
    public String getUnitID(){
        return unitID;
    }
    
    public int getProgress() {
    return progress;
    }
    
    //Overrides reportFinalGrade()
    //Prints the unit ID, progress, overall mark, and final grade.
    @Override
    public void reportFinalGrade(){
        System.out.println(unitID +" "+ progress+ " " + OverallMark+ " "  + finalGrade);
    }
}

