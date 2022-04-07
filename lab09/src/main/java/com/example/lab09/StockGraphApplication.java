package com.example.lab09;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockGraphApplication extends Application {
    private String resourcePath = "src/main/resources/com/example/lab09/";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StockGraphApplication.class.getResource("hello-view.fxml"));

        downloadStockPrices("https://query1.finance.yahoo.com/v7/finance/download/GOO" +
                "G?period1=1262322000&period2=1451538000&interval=1mo&eve" +
                "nts=history&includeAdjustedClose=true ", "GOOG-history");

        List<Float> stockClosingPrices = getStockClosingPrices("GOOG-history");
        for(Float price: stockClosingPrices) {
            System.out.println(price);
        }

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    // This function takes a stock ticker symbol (e.g. GOOG), and downloads historical
    // stock data about that organization from Yahoo Finance
    public void downloadStockPrices(String fileDownloadURL, String filename) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(fileDownloadURL).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(resourcePath + filename)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            System.out.println("done");
        } catch (IOException e) {
            // handle exception
        }
    }


    public List<Float> getStockClosingPrices (String filename) {
        List<Float> stockAdjustedFinalPrices = new ArrayList<Float>();
        try {
            File inFile = new File(resourcePath + filename);
            if (inFile.exists()) {
                BufferedReader stockCSV = new BufferedReader(new FileReader(inFile));
                Scanner scanner = new Scanner(stockCSV).useDelimiter(",");
                scanner.nextLine(); // discard header
                while (scanner.hasNextLine()) {
                    String csvLine = scanner.nextLine();
                    String[] records = csvLine.split(",");
                    stockAdjustedFinalPrices.add(Float.valueOf(records[5]));
                }
            }
        } catch (Exception e) {
            System.out.println("Error when reading or processing file: \n" + e.getMessage());
        }
        return stockAdjustedFinalPrices;
    }

    public static void main(String[] args) {
        launch();
    }
}