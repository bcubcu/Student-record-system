/*
Author: Rimsha Raheem 
Date: 28/10/2025 
File name: Unit_Research.java

Statement of purpose:
Research unit is derived from Unit class. It handles 
research grading systme including proposal and dissertation marks,
calculates the overall mark, and determines the final grade.

Assumptions:input
-OverallMark is double for accurate grade determination
-proposal and dissertation marks are double
-proposal and dissertation can not be above or below 0-100 marks

Expected Output
-Displays the overall mark and final grade for the research student when reportFinalGrade() is called.


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
public class Unit_Research extends Unit {
    private double proposal;// Mark for the research proposal (40% weight)
    private double dissertation; // Mark for the dissertation (60% weight)
    private double OverallMark;// Calculated overall mark
    private String finalGrade; // Final grade (HD, D, C, P, or N)
    
    public Unit_Research (double proposal, double dissertation){
        super("R");
        this.proposal = proposal;
        this.dissertation = dissertation;
        calculateOverallMark();// calculate the overall mark
        determineGrade(); // determine the grade
    }
    
    private void calculateOverallMark(){
        OverallMark = proposal * 0.4 + dissertation * 0.6; 
    }
    
    private void determineGrade(){
        if (OverallMark >= 80) finalGrade = "HD";// High Distinction
        else if (OverallMark >= 70) finalGrade = "D";// Distinction
        else if (OverallMark >= 60) finalGrade = "C";// Credit
        else if (OverallMark >= 50) finalGrade = "P";// Pass
        else finalGrade = "N";//Fail
    }
    
    public double getOverallMark(){
        return OverallMark;
    }
    
    public String getFinalGrade(){
        return finalGrade;
    }
    
    @Override
    public void reportFinalGrade(){
        System.out.println(OverallMark+" " + finalGrade);//Prints the overall mark and final grade for this research unit.
    }
}
