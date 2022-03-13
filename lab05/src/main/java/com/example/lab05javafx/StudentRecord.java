package com.example.lab05javafx;

public class StudentRecord {
    private String Student_ID;
    private float Midterm;
    private float Assignments;
    private float Final_Exam;
    private float Final_Mark;
    private float Letter_Grade;

    public StudentRecord(String id, float assignment, float midterm,float finalexam) {
        Student_ID = id;
        Assignments = assignment;
        Midterm = midterm;
        Final_Exam = finalexam;
    }
}
