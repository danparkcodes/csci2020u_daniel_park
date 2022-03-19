package com.example.midtermpart2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/** Represents JavaFX Scene for About view
 */
public class AboutScene {
    private Stage appStage;
    private Scene homeScene;
    public Button homeButton;
    public VBox aboutLayout;
    public Scene aboutScene;

    public AboutScene (){}

    /** Initializes AboutScene with
     * @param stage representing JavaFX app home stage
     * @param home representing JavaFX app home scene
     */
    public AboutScene (Stage stage, Scene home){
        appStage = stage;
        homeScene = home;
    }

    /** Adds all text, controls, and layouts for About Scene
     */
    public void setScene () {
        System.out.println("Clicked on About button");

        aboutLayout = new VBox();
        aboutLayout.setAlignment(Pos.CENTER);
        aboutScene = new Scene(aboutLayout,300,300);
         homeButton = new Button("Back to Main");
        homeButton.setMinWidth(200);
        homeButton.setOnAction(e -> {
            aboutLayout.getChildren().clear();
            appStage.setScene(homeScene);
        });

        // add text
        XMLReader xmlReader = new XMLReader();
        xmlReader.parseData();
        Text studentIDText = new Text();
        Text nameText = new Text();
        Text emailText = new Text();
        Text softDescTest = new Text();

        studentIDText.setText("\n\nStudent ID: " + xmlReader.getStudentID());
        nameText.setText("Name: " + xmlReader.getName());
        emailText.setText("Email: " + xmlReader.getEmail());
        softDescTest.setText("Software Description:" + xmlReader.getSoftwareDescription());

        aboutLayout.getChildren().addAll(homeButton, studentIDText, nameText, emailText,softDescTest);
        appStage.setScene(aboutScene);

    }
}
