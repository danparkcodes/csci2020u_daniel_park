package com.example.lab06javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.geometry.*;

import java.io.IOException;

public class Lab06Application extends Application {
    final double SIZE = 270.0;
    final double RAIDUS = SIZE / 2.0;
    @Override
    public void start(Stage stage) throws IOException {


        // --------------- Bar Chart ---------------
        double[] avgHousingPriceByYear = new BarChartData().getAvgHousingPricesByYear();
        double[] avgCommercialPricesByYear = new BarChartData().getAvgCommercialPricesByYear();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);

        XYChart.Series series1 = new XYChart.Series();
        for (int i = 0; i <avgHousingPriceByYear.length; i++){
            series1.getData().add(new XYChart.Data(String.valueOf(i), avgHousingPriceByYear[i]));
        }

        XYChart.Series series2 = new XYChart.Series();
        for (int i = 0; i <avgHousingPriceByYear.length; i++){
            series2.getData().add(new XYChart.Data(String.valueOf(i), avgCommercialPricesByYear[i]));
        }

        bc.getYAxis().setTickLabelsVisible(false);
        bc.getYAxis().setOpacity(0);
        bc.getXAxis().setTickLabelsVisible(false);
        bc.getXAxis().setOpacity(0);
        bc.setLegendVisible(false);
        bc.setBarGap(0);
        bc.setHorizontalGridLinesVisible(false);
        bc.setVerticalGridLinesVisible(false);
        //set first bar color
        for(Node n:bc.lookupAll(".data0.chart-bar")) {
            n.setStyle("-fx-bar-fill: green;");
        }
        //second bar color
        for(Node n:bc.lookupAll(".default-color2.chart-bar")) {
            n.setStyle("-fx-bar-fill: blue;");
        }

        bc.getData().addAll(series1, series2);

        // ---------------- Pie Chart -----------------
        PieChartData pieChartData = new PieChartData();
        String[] ageGroups = pieChartData.getAgeGroups();
        int[] purchasesByAgeGroups = pieChartData.getPurchasesByAgeGroup();
        Color[] pieColors = pieChartData.getPieColours();

        // create pie slices
        double startAngle = 0;
        int totalSale = 11410;
        Arc eighteenTo25 = new Arc(RAIDUS, RAIDUS, RAIDUS,RAIDUS, startAngle, 360 * (648.0/11410.0));
        eighteenTo25.setType(ArcType.ROUND);
        eighteenTo25.setFill(Color.AQUA);
        startAngle += 360 * (648.0/11410.0);

        Arc twentySixto35 = new Arc(RAIDUS, RAIDUS, RAIDUS,RAIDUS, startAngle, 360 * (1021.0/11410.0));
        twentySixto35.setType(ArcType.ROUND);
        twentySixto35.setFill(Color.GOLD);
        startAngle += 360 * (1021.0/11410.0);

        Arc thirtySixto45 = new Arc(RAIDUS, RAIDUS, RAIDUS,RAIDUS, startAngle, 360 * (2453.0/11410.0));
        thirtySixto45.setType(ArcType.ROUND);
        thirtySixto45.setFill(Color.DARKORANGE);
        startAngle += 360 * (2453.0/11410.0);

        Arc fortySixto55 = new Arc(RAIDUS, RAIDUS, RAIDUS,RAIDUS, startAngle, 360 * (3173.0/11410.0));
        fortySixto55.setType(ArcType.ROUND);
        fortySixto55.setFill(Color.DARKSALMON);
        startAngle += 360 * (3173.0/11410.0);

        Arc fiftySixto65 = new Arc(RAIDUS, RAIDUS, RAIDUS,RAIDUS, startAngle, 360 * (1868.0/11410.0));
        fiftySixto65.setType(ArcType.ROUND);
        fiftySixto65.setFill(Color.LAWNGREEN);
        startAngle += 360 * (1868.0/11410.0);

        Arc sixtyFivePlus = new Arc(RAIDUS, RAIDUS, RAIDUS,RAIDUS, startAngle, 360 * (2247.0/11410.0));
        sixtyFivePlus.setType(ArcType.ROUND);
        sixtyFivePlus.setFill(Color.PLUM);

        Pane pie = new Pane();

        pie.getChildren().addAll(eighteenTo25, twentySixto35, thirtySixto45, fortySixto55, fiftySixto65, sixtyFivePlus);


/*
        PieChart pieChart = new PieChart();

        for (int i = 0; i < ageGroups.length;i++) {
            PieChart.Data data = new PieChart.Data(ageGroups[i], purchasesByAgeGroups[i]);
            pieChart.getData().add(data);
        }
        int colorIndex = 0;
        for (PieChart.Data data : pieChart.getData()) {
            data.getNode().setStyle(
                    "-fx-pie-color: " + pieColors[colorIndex % pieColors.length] + ";"
            );
            colorIndex++;
        }*/


        HBox hbox = new HBox(bc,pie);
        hbox.setMargin(pie,new Insets(100,30,50,30));
        hbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(hbox, 800, 450);
        stage.setTitle("Lab06 Daniel Park");
        stage.setScene(scene);
        stage.getScene().getStylesheets().add("style.css");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void createBarChart () {

    }
}

class BarChartData {
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };
    public double[] getAvgHousingPricesByYear () {return avgHousingPricesByYear; }
    public double[] getAvgCommercialPricesByYear () {return avgCommercialPricesByYear; }
}

class PieChartData {
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    public String[] getAgeGroups() {return ageGroups;}
    public int[] getPurchasesByAgeGroup() {return purchasesByAgeGroup;}
    public Color[] getPieColours() {return pieColours;}
}