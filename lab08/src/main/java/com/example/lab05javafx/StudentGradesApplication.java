package com.example.lab05javafx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentGradesApplication extends Application {
    static ObservableList<StudentRecord> studentMarks;
    @Override
    public void start(Stage stage) throws IOException {

        // Retrieve student records
        DataSource studentData = new DataSource();
        studentMarks= studentData.getAllMarks();

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

        // add records to tableview
        for (StudentRecord record: studentMarks) {
            tableview.getItems().add(record);
        }

        // Create menu, add menu items
        Menu file_menu = new Menu("File");
        MenuItem new_menuItem = new MenuItem("New");
        MenuItem open_menuItem = new MenuItem("Open");
        MenuItem save_menuItem = new MenuItem("Save");
        MenuItem saveAs_menuItem = new MenuItem("Save As");
        MenuItem exit_menuItem = new MenuItem("Exit");
        file_menu.getItems().add(new_menuItem);
        file_menu.getItems().add(open_menuItem);
        file_menu.getItems().add(save_menuItem);
        file_menu.getItems().add(saveAs_menuItem);
        file_menu.getItems().add(exit_menuItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(file_menu);

        // MenuItem Events
        new_menuItem.setOnAction(StudentController.newHandler(tableview));
        open_menuItem.setOnAction(StudentController.open(studentMarks,stage,tableview)); // open picker
        save_menuItem.setOnAction(StudentController.save(studentMarks));
        saveAs_menuItem.setOnAction(StudentController.saveAs(studentMarks,stage));
        exit_menuItem.setOnAction(StudentController.exit());

        VBox vbox = new VBox(menuBar,tableview);
        Scene scene = new Scene(vbox);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static EventHandler<ActionEvent> test() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                // see if pass by reference worked in Controller event handler
                // try to print out the studentRecord obersvable list after trying to clear it from Controller metho
                for(StudentRecord record: studentMarks) {
                    System.out.println(record.getCsvOf_SID_assig_midt_final());
                }
                System.out.println("Testing!");

            }
        };
    }
    public static void main(String[] args) {
        launch();
    }

}