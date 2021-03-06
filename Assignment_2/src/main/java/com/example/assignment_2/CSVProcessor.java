package com.example.assignment_2;

import java.io.*;
import java.util.*;
/**
 * Parses, calculates, summarizes, and stores airline incident data from .csv
 * Appends .csv file with calculated colum "total incidents"
 */
public class CSVProcessor {

    private final String resourcePath = "src/main/resources/com/example/assignment_2/";
    private String csvFilename;
    private File inFile;
    private double averageIncidents85to99;
    private double averageIncidents00to14;
    public String[] csvColumns = new CSVAirlineRowData().getAllColumns();;
    /**
     * Entirety of the .csv file of airline incident statistics
     * key is name of airline
     */
    private LinkedHashMap<String, CSVAirlineRowData> airlineSafetyRecords; // Key is airline name, Value is it's row
    /**
     * Calculated summary statistics for each column from .csv file of airline incident statistics
     * key is name of airline
     */
    private LinkedHashMap<String,CSVColumnSummaryData> csvColumnSummaryData; // key is column name, value is col summaries


    public CSVProcessor(String csvFilename) {
        this.csvFilename = csvFilename;
        this.airlineSafetyRecords = new LinkedHashMap<String, CSVAirlineRowData>();
        this.csvColumnSummaryData = new LinkedHashMap<String, CSVColumnSummaryData>();
    }
    /**
     * Parses .csv of airline incident data and stores in LinkedHashMap
     * Calculates column summary and average statistics
     */
    public void parseCSVData() throws FileNotFoundException {
        try {
            inFile = new File(resourcePath + csvFilename);
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
                CSVAirlineRowData airlineRowData = new CSVAirlineRowData();
                String airlineRow = scanner.nextLine();
                String[] airlineRowCells = airlineRow.split(",");
                // Add data for one row to CSVAirlineData data class
                for (int column = 0; column < airlineRowCells.length; column++) {
                    if (0 == column) {
                        airlineRowData.setAirline(airlineRowCells[0]);
                    } else if (1==column) {
                        airlineRowData.setAvailSeatKMPerWeek(Long.parseLong(airlineRowCells[1]));
                    } else if (2 == column) {
                        airlineRowData.setIncidents85to99(Long.parseLong(airlineRowCells[2]));
                    } else if (3 == column) {
                        airlineRowData.setFatalAccidents85to99(Long.parseLong(airlineRowCells[3]));
                    } else if (4 == column) {
                        airlineRowData.setFatalities85to99(Long.parseLong(airlineRowCells[4]));
                    } else if (5 == column) {
                        airlineRowData.setIncidents00to14(Long.parseLong(airlineRowCells[5]));
                    } else if (6 == column) {
                        airlineRowData.setFatalAccidents00to14(Long.parseLong(airlineRowCells[6]));
                    } else {
                        airlineRowData.setFatalities00to14(Long.parseLong(airlineRowCells[7]));
                        // calculate total incidents column
                        long totalIncidents;
                        totalIncidents = airlineRowData.getIncidents85to99() + airlineRowData.getIncidents00to14();
                        airlineRowData.setTotalIncidents(totalIncidents);
                    }
                }
                // Add to the LinkedHashMap
                airlineSafetyRecords.put(airlineRowData.getAirline(), airlineRowData);
            }
        }
        calculateColumnSummaryStatistics();
        calculateAverageIncidentStatistics();
    }
    /**
     * Processes csv column summary data
     * Calculates min, max, and average for each column from .csv and stores in LinkedHashMap
     */
    private void calculateColumnSummaryStatistics () {
        // Summary stats for each column
        for (int columnInd = 1; columnInd < csvColumns.length; columnInd++) {
            CSVColumnSummaryData columnSummary = new CSVColumnSummaryData();
            // Each Min/Max/Avg for each column
            for (int summaryTypeInd = 0; summaryTypeInd < 3; summaryTypeInd++) {
                long minimum = Long.MAX_VALUE;
                long maximum = 0;
                double total = 0.0;
                Set<String> keys = airlineSafetyRecords.keySet();
                // MINIMUM
                if (0 == summaryTypeInd) {
                    for (String key : keys) {
                        if (airlineSafetyRecords.get(key).getStatByIndex(columnInd) < minimum) {
                            minimum = airlineSafetyRecords.get(key).getStatByIndex(columnInd);
                        }
                    }
                    columnSummary.setStatMin(minimum);
                }
                // MAXIMUM
                if (1 == summaryTypeInd) {
                    for (String key : keys) {
                        if (airlineSafetyRecords.get(key).getStatByIndex(columnInd) > maximum) {
                            maximum = airlineSafetyRecords.get(key).getStatByIndex(columnInd);
                        }
                    }
                    columnSummary.setStatMax(maximum);
                }
                // AVERAGE
                if (2 == summaryTypeInd) {
                    for (String key : keys) {
                        total += airlineSafetyRecords.get(key).getStatByIndex(columnInd);
                    }
                    columnSummary.setStatAverage(total/keys.size());
                }
            }
            columnSummary.setColumnName(csvColumns[columnInd]);
            csvColumnSummaryData.put(csvColumns[columnInd],columnSummary);
        }
    }
    /**
     * Calculates average incident statistics for all airlines
     */
    private void calculateAverageIncidentStatistics () {
        Set<String> keys = airlineSafetyRecords.keySet();
        double total = 0.0;
        int incidents85to99Index = 2;
        int incidents00to14Index = 5;
        for (String key : keys) {
            total += airlineSafetyRecords.get(key).getStatByIndex(incidents85to99Index);
        }
        averageIncidents85to99 = (total / keys.size());
        total = 0.0;
        for (String key : keys) {
            total += airlineSafetyRecords.get(key).getStatByIndex(incidents00to14Index);
        }
        averageIncidents00to14 = (total / keys.size());
    }
    /**
     * Calculates total incidents for each airline and appends results to .csv
     */
    public void appendTotalToCSV () {
        List<String> lines = new ArrayList<>();
        try {
            String line;
            int keyIndex = 0;
            FileWriter csvWriter = new FileWriter("filename");
            BufferedReader csvAirline = new BufferedReader(new FileReader(resourcePath + "airline_safety.csv"));
            Scanner scanner = new Scanner(csvAirline);

            List<String> keys = new ArrayList<>(airlineSafetyRecords.keySet());

            if (scanner.hasNextLine()) { // scan header row
                lines.add(scanner.nextLine().trim() + ",total_incidents");
            }

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                line = line.trim() + "," + airlineSafetyRecords.get(keys.get(keyIndex)).getTotalIncidents();
                lines.add(line);
                keyIndex++;
            }

            // create buffered writer and wring line
            FileWriter newFile = new FileWriter(resourcePath + "airline_safety.csv");
            BufferedWriter bw = new BufferedWriter(newFile);

            for(String newLine: lines) {
                bw.write(newLine);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Exception message: " + e.getMessage());
        }
    }

    public double getAverageIncidents85to99() {
        return averageIncidents85to99;
    }

    public double getAverageIncidents00to14() {
        return averageIncidents00to14;
    }

    public LinkedHashMap<String, CSVAirlineRowData> getAirlineSafetyRecords() {
        return airlineSafetyRecords;
    }

    public LinkedHashMap<String, CSVColumnSummaryData> getCsvColumnSummaryData() {
        return csvColumnSummaryData;
    }
}

