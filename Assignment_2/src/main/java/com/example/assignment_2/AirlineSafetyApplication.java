package com.example.assignment_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Set;

public class AirlineSafetyApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AirlineSafetyApplication.class.getResource("hello-view.fxml"));

        CSVProcessor csvProcessor= new CSVProcessor("airline_safety.csv");
        csvProcessor.parseCSVData();
        csvProcessor.appendTotalToCSV();

        // Write to xml file
        AirlineXMLCreator xmlCreator = new AirlineXMLCreator();
        try {
            xmlCreator.convertAirlineSafetyCSVtoXML("converted_airline_safety.xml");
            xmlCreator.createAirlineSummaryStatisticsXML("airline_summary_statistic.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }


        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}