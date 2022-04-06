package com.example.lab05javafx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StudentController {
    private static String resourcePath = "src/main/resources/com/example/lab05javafx/";
    public static String currentFilename = "student-records.csv";



    public static EventHandler<ActionEvent> newHandler(TableView tableView) {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //
                tableView.getItems().clear();
            }
        };
    }


    //Write a function save() that will pull all data from the TableView’s data structure, and save it to
    //the current filename as a CSV file.  This function will save only the following fields:
    //• SID
    //• Assignments
    //• Midterm
    //• Final Exam
    public static EventHandler<ActionEvent> save(ObservableList<StudentRecord> studentMarks) {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                // take all Student records data and write to csv
                try {
                    FileWriter csvWriter = new FileWriter(resourcePath + currentFilename);
                    BufferedWriter bw = new BufferedWriter(csvWriter);
                    for(StudentRecord record: studentMarks) {
                        bw.write(record.getCsvOf_SID_assig_midt_final());
                        bw.newLine();
                    }
                    bw.close();
                    System.out.println("Student records saved to: " + currentFilename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // save it line by line to csv

            }
        };
    }

    //Write a function load() that will load the CSV data (as above) from the current filename, and
    //populate the TableView
    public static EventHandler<ActionEvent> load() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Exit is working!");
                System.exit(0);
            }
        };
    }

    // show a file chooser , which will do the following
    //when used:
    //• Update the currentFilename variable to the user-selected file’s filename
    //• Call load()
    public static EventHandler<ActionEvent> open() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Exit is working!");
                System.exit(0);
            }
        };
    }

    public static EventHandler<ActionEvent> saveAs() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Exit is working!");
                System.exit(0);
            }
        };
    }

    public static EventHandler<ActionEvent> exit() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Exit is working!");
                System.exit(0);
            }
        };
    }
}