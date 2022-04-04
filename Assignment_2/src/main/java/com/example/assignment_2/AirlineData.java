package com.example.assignment_2;

public class AirlineData {
    private String airline;
    private long availSeatKMPerWeek;
    private long incidents85to99;
    private long fatalAccidents85to99;
    private long fatalities85to99;
    private long incidents00to14;
    private long fatalAccidents00to14;
    private long fatalities00to14;
    private long totalIncidents;

    public AirlineData () {}

    public AirlineData(String airline, long availSeatKMPerWeek, long incidents85to99, long fatalAccidents85to99, long fatalities85to99, long incidents00to14, long fatalAccidents00to14, long fatalities00to14, long totalIncidents) {
        this.airline = airline;
        this.availSeatKMPerWeek = availSeatKMPerWeek;
        this.incidents85to99 = incidents85to99;
        this.fatalAccidents85to99 = fatalAccidents85to99;
        this.fatalities85to99 = fatalities85to99;
        this.incidents00to14 = incidents00to14;
        this.fatalAccidents00to14 = fatalAccidents00to14;
        this.fatalities00to14 = fatalities00to14;
        this.totalIncidents = totalIncidents;
    }

    public long getAvailSeatKMPerWeek() {
        return availSeatKMPerWeek;
    }

    public long getFatalAccidents00to14() {
        return fatalAccidents00to14;
    }

    public String getAirline() {
        return airline;
    }

    public long getIncidents85to99() {
        return incidents85to99;
    }

    public long getFatalAccidents85to99() {
        return fatalAccidents85to99;
    }

    public long getFatalities85to99() {
        return fatalities85to99;
    }

    public long getIncidents00to14() {
        return incidents00to14;
    }

    public long getFatalities00to14() {
        return fatalities00to14;
    }

    public long getTotalIncidents() {
        return totalIncidents;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setAvailSeatKMPerWeek(long availSeatKMPerWeek) {
        this.availSeatKMPerWeek = availSeatKMPerWeek;
    }

    public void setIncidents85to99(long incidents85to99) {
        this.incidents85to99 = incidents85to99;
    }

    public void setFatalAccidents85to99(long fatalAccidents85to99) {
        this.fatalAccidents85to99 = fatalAccidents85to99;
    }

    public void setFatalities85to99(long fatalities85to99) {
        this.fatalities85to99 = fatalities85to99;
    }

    public void setIncidents00to14(long incidents00to14) {
        this.incidents00to14 = incidents00to14;
    }

    public void setFatalAccidents00to14(long fatalAccidents00to14) {
        this.fatalAccidents00to14 = fatalAccidents00to14;
    }

    public void setFatalities00to14(long fatalities00to14) {
        this.fatalities00to14 = fatalities00to14;
    }

    public void setTotalIncidents(long totalIncidents) {
        this.totalIncidents = totalIncidents;
    }

    public void printAll () {
        System.out.println(
        airline +
        availSeatKMPerWeek+
        incidents85to99+
        fatalAccidents85to99+
        fatalities85to99+
        incidents00to14+
        fatalAccidents00to14+
        fatalities00to14+
        totalIncidents);
    }
}
