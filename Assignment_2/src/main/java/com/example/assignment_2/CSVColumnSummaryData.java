package com.example.assignment_2;
/**
 * Data class that represents summary statistics for a single column
 * Each column is an airline incident statistic.
 * Summary statistics include minimum, maximum, and average values for each column
 * For example: Maximum of (fatalities from 1985-1999)
 * */
public class CSVColumnSummaryData {
    private String columnName;
    private long statMin;
    private long statMax;
    private double statAverage;

    public CSVColumnSummaryData(String columnName, long statMin, long statNax, long statAverage) {
        this.columnName = columnName;
        this.statMin = statMin;
        this.statMax = statNax;
        this.statAverage = statAverage;
    }

    public CSVColumnSummaryData() {
    }

    public String getcolumnName() {
        return columnName;
    }

    public long getStatMin() {
        return statMin;
    }

    public long getStatMax() {
        return statMax;
    }

    public double getStatAverage() {
        return statAverage;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setStatMin(long statMin) {
        this.statMin = statMin;
    }

    public void setStatMax(long statMax) {
        this.statMax = statMax;
    }

    public void setStatAverage(double statAverage) {
        this.statAverage = statAverage;
    }
}
