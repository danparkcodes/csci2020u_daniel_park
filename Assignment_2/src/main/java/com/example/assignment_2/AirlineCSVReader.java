package com.example.assignment_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class AirlineCSVReader {

    private String csvFilename;
    File inFile;
    LinkedHashMap<String,AirlineData> airlineSafetyRecords;

    public AirlineCSVReader(String csvFilename) {
        this.csvFilename = csvFilename;
        this.airlineSafetyRecords = new LinkedHashMap<String,AirlineData>();
    }

    public void parseCSVData() throws FileNotFoundException {
        try {
            inFile = new File("src/main/resources/com/example/assignment_2/airline_safety.csv");
        } catch (Exception e) {
            System.out.println("Following error reading file: \n" + e.getMessage());
        }
        if (inFile.exists()) {
            BufferedReader csvWeather = new BufferedReader(new FileReader(inFile));
            String fileLine = "";
            Scanner scanner = new Scanner(csvWeather).useDelimiter(",");
            int header = 0;
            // Parse each line of tabular data from CSV file into AirlineData data class
            while (scanner.hasNextLine()) {
                if (0 == header) {
                    scanner.nextLine();
                    header++;
                    continue;
                }
                AirlineData airlineData = new AirlineData();
                String airlineRow = scanner.nextLine();
                String[] airlineRowCells = airlineRow.split(",");
                for (int column = 0; column < airlineRowCells.length; column++) {
                    if (0 == column) {
                        airlineData.setAirline(airlineRowCells[0]);
                    } else if (1==column) {
                        airlineData.setAvailSeatKMPerWeek(Long.parseLong(airlineRowCells[1]));
                    } else if (2 == column) {
                        airlineData.setIncidents85to99(Long.parseLong(airlineRowCells[2]));
                    } else if (3 == column) {
                        airlineData.setFatalAccidents85to99(Long.parseLong(airlineRowCells[3]));
                    } else if (4 == column) {
                        airlineData.setFatalities85to99(Long.parseLong(airlineRowCells[4]));
                    } else if (5 == column) {
                        airlineData.setIncidents00to14(Long.parseLong(airlineRowCells[5]));
                    } else if (6 == column) {
                        airlineData.setFatalAccidents00to14(Long.parseLong(airlineRowCells[6]));
                    } else {
                        airlineData.setFatalities00to14(Long.parseLong(airlineRowCells[7]));
                    }
                }
            }
        }
    }

    public LinkedHashMap<String,AirlineData> getAirlineSafetyRecords() {
        return airlineSafetyRecords;
    }

}
