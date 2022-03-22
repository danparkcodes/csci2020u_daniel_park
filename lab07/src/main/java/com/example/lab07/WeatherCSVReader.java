package com.example.lab07;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Class represents weather data
// Parses weather data from csv file
public class WeatherCSVReader {

    HashMap<String, Integer> weatherWarnings;
    File inFile;

    WeatherCSVReader () {
        weatherWarnings = new HashMap<String, Integer>();
        weatherWarnings.put("FLASH FLOOD", 0);
        weatherWarnings.put("SEVERE THUNDERSTORM", 0);
        weatherWarnings.put("SPECIAL MARINE",0);
        weatherWarnings.put("TORNADO",0);
    }

    // Parses weather warning data from CSV file
    public HashMap<String, Integer> parseWarningTypes () throws IOException {
    // Read from file
        try {
            inFile = new File("src/main/resources/com/example/lab07/weatherwarnings-2015.csv");
        } catch (Exception e) {
            System.out.println("Following error reading file: \n" + e.getMessage());
        }
    // store entries in a map
        if (inFile.exists()) {
            BufferedReader csvWeather = new BufferedReader(new FileReader(inFile));
            String fileLine = "";
            Scanner scannerLine = new Scanner(csvWeather).useDelimiter(",|\\n");
            int valueCounter = 0;
            String scannerValue = "";
            while (scannerLine.hasNext()) {
                valueCounter++;
                scannerValue = scannerLine.next();
                if (valueCounter == 6) {
                    countWarningType(scannerValue);
                } else if (7 == valueCounter) {
                    valueCounter = 0;
                }
            }
        }

        // print hashmap results
        for (Map.Entry<String, Integer> e: weatherWarnings.entrySet()) {
            System.out.println("Key: " + e.getKey()
                    + " Value: " + e.getValue());
        }
        return weatherWarnings;
    }

    // Count warning type
    private void countWarningType (String warning) {
        int valueCounter = 0;
        if ("FLASH FLOOD".equals(warning)) {
            valueCounter = weatherWarnings.get("FLASH FLOOD") + 1;
            weatherWarnings.replace("FLASH FLOOD", valueCounter);
        } else if ("SEVERE THUNDERSTORM".equals(warning)) {
            valueCounter = weatherWarnings.get("SEVERE THUNDERSTORM") + 1;
            weatherWarnings.replace("SEVERE THUNDERSTORM", valueCounter);
        } else if ("SPECIAL MARINE".equals(warning)) {
            valueCounter = weatherWarnings.get("SPECIAL MARINE") + 1;
            weatherWarnings.replace("SPECIAL MARINE", valueCounter);
        } else if ("TORNADO".equals(warning)) {
            valueCounter = weatherWarnings.get("TORNADO") + 1;
            weatherWarnings.replace("TORNADO", valueCounter);
        }
    }
}
