package com.example.midtermpart2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MidtermController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}