package com.example.lab05javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

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
    public static void load(ObservableList<StudentRecord> cachedRecords, TableView tableview) {
        // load csv data from currentFilename
        try {
            File inFile = new File(resourcePath + currentFilename);
            if (inFile.exists()) {
                // ObservableList<StudentRecord> loadedStudentMarks = FXCollections.observableArrayList();
                BufferedReader csvWeather = new BufferedReader(new FileReader(inFile));
                Scanner scanner = new Scanner(csvWeather).useDelimiter(",");
                // Parse each csv line into StudentRecord record
                while (scanner.hasNextLine()) {
                    String csvLine = scanner.nextLine();
                    if (csvLine.trim().length() > 0);
                    {
                        String[] records = csvLine.split(",");
                        StudentRecord studentRecord = new StudentRecord(
                                records[0]
                                ,Float.valueOf(records[1])
                                ,Float.valueOf(records[2])
                                ,Float.valueOf(records[3]));
                        cachedRecords.add(studentRecord);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Following error reading file: \n" + e.getMessage());
        }
        // add records to tableview
        for (StudentRecord record: cachedRecords) {
            tableview.getItems().add(record);
        }
        // populate tableView
    }

    // show a file chooser , which will do the following
    //when used:
    //• Update the currentFilename variable to the user-selected file’s filename
    //• Call load()
    public static EventHandler<ActionEvent> open(ObservableList<StudentRecord> cachedRecords, Stage stage, TableView tableview) {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Oepn is working!");
                try {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setInitialDirectory(new File(resourcePath));
                    File selectedFile = fileChooser.showOpenDialog(stage);
                    currentFilename = selectedFile.getName();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                load(cachedRecords,tableview);
            }
        };
    }

    public static EventHandler<ActionEvent> saveAs() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("SaveAs is working!");
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