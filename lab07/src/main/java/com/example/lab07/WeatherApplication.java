package com.example.lab07;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WeatherApplication extends Application {
    final double SIZE = 270.0;
    final double RAIDUS = SIZE / 2.0;
    @Override
    public void start(Stage stage) throws IOException {

        PieChartData pieChartData = new PieChartData();
        Color[] pieChartColors = pieChartData.getPieColours();

        WeatherCSVReader weatherCSVReader = new WeatherCSVReader();
        HashMap<String, Integer> weatherWarnings = weatherCSVReader.parseWarningTypes();

        String[] warningsType = new String[weatherWarnings.size()];
        Integer[] warningsCount = new Integer[weatherWarnings.size()];
        int mapIndex = 0;
        for(Map.Entry<String, Integer> entry : weatherWarnings.entrySet()) {
            warningsType[mapIndex] = entry.getKey();
            warningsCount[mapIndex] = entry.getValue();
            mapIndex++;
        }

        // --- PIE CHART -------------
        PieChart pieChart = new PieChart();
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data(warningsType[0],warningsCount[0]),
                new PieChart.Data(warningsType[1],warningsCount[1]),
                new PieChart.Data(warningsType[2],warningsCount[2]),
                new PieChart.Data(warningsType[3],warningsCount[3])
        );

        pieChart.setData(pieData);

        // set pie chart colors
        int colIndex = 0;
        for (PieChart.Data d : pieData) {
            d.getNode().setStyle(
                    "-fx-pie-color: " + pieChartColors[colIndex % pieChartColors.length] + ";"
            );
            colIndex++;
        }
        pieChart.setLegendSide(Side.LEFT);

        HBox hbox = new HBox(pieChart);
        hbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(hbox, 400, 300);
        stage.setTitle("Lab07 Daniel Park");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

// Class represents PieChart color data
class PieChartData {
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };
    public Color[] getPieColours() {return pieColours;}
}