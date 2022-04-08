package com.example.lab09;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class StockGraphApplication extends Application {
    public final double WIDTH = 1200.0;
    public final double HEIGHT = 1000.0;
    public final double PADDING = 50.0;
    private String resourcePath = "src/main/resources/com/example/lab09/";

    @Override
    public void start(Stage stage) throws IOException {

        List<Float> googlePrices = downloadStockPrices("GOOG", "GOOG-history");
        List<Float> applePrices = downloadStockPrices("AAPL", "AAPL-history");
        List<Float> amazonPrices = downloadStockPrices("AMZN", "AMZN-history");
        List<Float> netflixPrices = downloadStockPrices("NFLX", "NFLX-history");

        var root = new Pane();
        root.setStyle("-fx-background-color: ghostwhite  ");
        var canvas = new Canvas(WIDTH + (2*PADDING), HEIGHT + (2*PADDING));
        var gc = canvas.getGraphicsContext2D();

        drawLinePlot(gc,googlePrices,applePrices, amazonPrices, netflixPrices );

        root.getChildren().add(canvas);
        System.out.println(WIDTH + (2*PADDING));

        Scene scene = new Scene(root, WIDTH + (2*PADDING) ,HEIGHT + (2*PADDING) );
        stage.setTitle("Historical Closing Stock Prices: GOOG, AAPL, AMZN, NFLX!");
        stage.setScene(scene);
        stage.show();

    }

    // Append Yahoo finance stock history query download and provided stock name symobl
    public String createStockDownloadURL (String stockName) {
        String download = "https://query1.finance.yahoo.com/v7/finance/download/";
        String parameters = "?period1=1262322000&period2=1451538000&interval=1mo&eve"
                + "nts=history&includeAdjustedClose=true";
        return  download + stockName + parameters;
    }

    // This function takes a stock ticker symbol (e.g. GOOG), and downloads historical
    // stock data about that organization from Yahoo Finance
    public List<Float> downloadStockPrices(String stockName, String filename) {
        String fileDownloadURL = createStockDownloadURL(stockName);
        try (BufferedInputStream in = new BufferedInputStream(new URL(fileDownloadURL).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(resourcePath + filename)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getStockClosingPrices(filename);
    }

    // Retrieve adjusted closing price column
    // Parse price from downloaded csv stock price file
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

    // This function takes two lists of floating point values, which are stock closing price values
    // Plots those lists of prices
    public void drawLinePlot(GraphicsContext gc, List<Float> stock1, List<Float> stock2,List<Float> stock3,List<Float> stock4) {
        // draw x and y axis
        drawAxes(gc);
        plotLine(gc,stock1, Color.RED);
        plotLine(gc,stock2, Color.BLUE);
        plotLine(gc,stock3, Color.ORANGE);
        plotLine(gc,stock4, Color.PALEGREEN);

    }

    // Use 2D graphics to draw lines between each closing price
    // 50 pixel padding
    public void plotLine(GraphicsContext gc,List<Float> stock, Color color) {
        double deltaX = WIDTH/71.0;
        System.out.println("delta x is : " + deltaX);
        double xPos = PADDING;
        double yPos = PADDING + HEIGHT;
        double yPosNext;
        gc.beginPath();
        gc.moveTo(xPos,yPos - stock.get(0));
        // for each point in array move the line
        for (int stockInd = 1; stockInd < stock.size(); stockInd++) {
            xPos += deltaX;
            yPosNext = yPos - stock.get(stockInd);
            gc.lineTo(xPos,yPosNext);
        }
        gc.setStroke(color);
        gc.stroke();
        gc.closePath();
    }

    // Draw x-y axes with PADDING
    public void drawAxes (GraphicsContext gc) {
        gc.beginPath();
        gc.moveTo(PADDING, PADDING);
        gc.lineTo(PADDING, HEIGHT+PADDING);
        gc.lineTo(WIDTH+PADDING, HEIGHT+PADDING);
        gc.stroke();
        gc.closePath();
    }

    public static void main(String[] args) {
        launch();
    }
}