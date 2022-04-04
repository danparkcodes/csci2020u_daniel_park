package com.example.assignment_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AirlineSafetyApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AirlineSafetyApplication.class.getResource("hello-view.fxml"));

        AirlineCSVReader csvReader= new AirlineCSVReader("airline_safety.csv");
        csvReader.parseCSVData();

        // Process Data = Airline CSV Reader Data



        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    // Process Data

    // Calculate total number of incident

    // Calculuate The minimum, maximum, and average values for each column.

    // Calculuate The average number of incidents between 1985 – 1999 across all airlines.

    // Calculate The average number of incidents between 2000 – 2014 across all airlines.

    public static void main(String[] args) {
        launch();
    }
}