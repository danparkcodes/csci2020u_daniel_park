package com.example.lab06javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Lab06Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}