package com.example.lab05javafx;

public class StudentRecord {
    private String Student_ID;
    private float Midterm;
    private float Assignments;
    private float finalExam;
    private float finalMark;
    private String LetterGrade;
    public String currentFilename; // stores currently viewed filename

    public StudentRecord(String id, float assignment, float midterm,float finalExam) {
        Student_ID = id;
        Assignments = assignment;
        Midterm = midterm;
        this.finalExam = finalExam;
        finalMark = (0.2f * Assignments) + (0.3f*Midterm) + (0.5f*finalExam);
        if (finalMark < 50) {
            LetterGrade = "F";
        } else if (finalMark < 60) {
            LetterGrade = "D";
        } else if (finalMark < 70) {
            LetterGrade = "C";
        } else if (finalMark < 80) {
            LetterGrade = "B";
        }  else
            LetterGrade = "A";
    }

    public String getStudent_ID() {return Student_ID;}
    public float getMidterm() {return Midterm;}
    public float getAssignments() {return Assignments;}
    public float getFinalExam() {return finalExam;}
    public float getFinalMark() {return finalMark;}
    public String getLetterGrade() {return LetterGrade;}

    public String getCsvOf_SID_assig_midt_final() {
        String[] csvStrings =
                {Student_ID,Float.toString(Assignments),Float.toString(Midterm),Float.toString(finalExam)};
        String joinedCsvString = String.join(",",csvStrings);
        return joinedCsvString;
    }

}
