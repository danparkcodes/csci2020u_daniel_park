package com.example.lab05javafx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentGrades extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Retrieve student records
        DataSource studentData = new DataSource();
        ObservableList<StudentRecord> studentMarks= studentData.getAllMarks();

        TableView tableview = new TableView();

        TableColumn<StudentRecord, String> SID_column = new TableColumn<>("SID");
        SID_column.setCellValueFactory(new PropertyValueFactory<>("Student_ID"));

        TableColumn<StudentRecord, String> Assignments_column = new TableColumn<>("Assignments");
        Assignments_column.setCellValueFactory(new PropertyValueFactory<>("Assignments"));

        TableColumn<StudentRecord, String> Midterm_column = new TableColumn<>("Midterm");
        Midterm_column.setCellValueFactory(new PropertyValueFactory<>("Midterm"));

        TableColumn<StudentRecord, String> Final_exam_column = new TableColumn<>("Final Exam");
        Final_exam_column.setCellValueFactory(new PropertyValueFactory<>("finalExam"));

        TableColumn<StudentRecord, String> Final_mark_column = new TableColumn<>("Final Mark");
        Final_mark_column.setCellValueFactory(new PropertyValueFactory<>("finalMark"));

        TableColumn<StudentRecord, String> Letter_grade_column = new TableColumn<>("Letter Grade");
        Letter_grade_column.setCellValueFactory(new PropertyValueFactory<>("LetterGrade"));

        tableview.getColumns().add(SID_column);
        tableview.getColumns().add(Assignments_column);
        tableview.getColumns().add(Midterm_column);
        tableview.getColumns().add(Final_exam_column);
        tableview.getColumns().add(Final_mark_column);
        tableview.getColumns().add(Letter_grade_column);


        for (StudentRecord record: studentMarks) {
            tableview.getItems().add(record);
        }

        // Choicebox

        Menu menu1 = new Menu("File");
        MenuItem menuItem1 = new MenuItem("New");
        MenuItem menuItem2 = new MenuItem("Open");
        MenuItem menuItem3 = new MenuItem("Save");
        MenuItem menuItem4 = new MenuItem("Save As");
        MenuItem menuItem5 = new MenuItem("Exit");
        menu1.getItems().add(menuItem1);
        menu1.getItems().add(menuItem2);
        menu1.getItems().add(menuItem3);
        menu1.getItems().add(menuItem4);
        menu1.getItems().add(menuItem5);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu1);

        VBox vbox = new VBox(menuBar,tableview);
        Scene scene = new Scene(vbox);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}