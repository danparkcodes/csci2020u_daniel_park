package com.example.assignment_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Set;

public class AirlineSafetyApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AirlineSafetyApplication.class.getResource("hello-view.fxml"));

        // Process CSV data, calculate statistics, append totals to csv file
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

        // Graph bar chart
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        createBarChart(bc,xAxis,yAxis, csvProcessor);

        Scene scene = new Scene(bc,1200,600);
        stage.setTitle("Airline Fatal Incidents (1985-2014)");
        stage.setScene(scene);
        stage.show();
    }

    public void createBarChart (BarChart<String,Number> bc, CategoryAxis xAxis, NumberAxis yAxis, CSVProcessor processor) {
        // TODO
        LinkedHashMap<String,CSVAirlineRowData> airlineSafetyRecords = processor.getAirlineSafetyRecords();
        bc.setTitle("Airline Fatal Incidents (1985-2014)");
        xAxis.setLabel("Airline");
        yAxis.setLabel("Fatal Incidents");

        // Series 1: fatal incidents 85-99
        // Series 2: fatal incident 00-14
        XYChart.Series fatalIncidents85to99Series = new XYChart.Series();
        XYChart.Series fatalIncidents00to14Series = new XYChart.Series();
        fatalIncidents85to99Series.setName("1985-1999");
        fatalIncidents00to14Series.setName("2000-2014");
        Set<String> keys = airlineSafetyRecords.keySet();
        for(String key: keys) {
            addDataToXYChartSeries(fatalIncidents85to99Series, airlineSafetyRecords, key, 3);
            addDataToXYChartSeries(fatalIncidents00to14Series, airlineSafetyRecords, key, 6);
        }
        bc.getData().addAll(fatalIncidents85to99Series,fatalIncidents00to14Series);
    }

    private void addDataToXYChartSeries (XYChart.Series series, LinkedHashMap<String, CSVAirlineRowData> airlineData, String airlineKey, int statIndex){
        series.getData().add(new XYChart.Data(airlineKey, airlineData.get(airlineKey).getStatByIndex(statIndex)));

    }

    public static void main(String[] args) {
        launch();
    }


}