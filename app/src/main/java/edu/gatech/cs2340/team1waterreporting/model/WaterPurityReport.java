package edu.gatech.cs2340.team1waterreporting.model;

import java.util.Date;

/**
 * Class to contain a water purity report.
 */

public class WaterPurityReport {
    private Date date;
    private int number;
    private User reporter;
    private Location location;
    private WaterCondition waterCondition;
    private double virusPpm;
    private double contaminentPpm;

    private WaterPurityReport() {
        this.date = new Date();
    }

    public WaterPurityReport(int number, User reporter, Location location, WaterCondition waterCondition, double virusPpm, double contaminentPpm) {
        this();
        this.number = number;
        this.reporter = reporter;
        this.location = location;
        this.waterCondition = waterCondition;
        this.virusPpm = virusPpm;
        this.contaminentPpm = contaminentPpm;
    }

    public static void validatePpm(String ppm) throws UserInputException {
        if (Float.parseFloat(ppm) > 1E6) {
            throw new UserInputException("Cannot have more than a million ppm");
        } else if (Float.parseFloat(ppm) < 0) {
            throw new UserInputException("Cannot have negative ppm");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public WaterCondition getWaterCondition() {
        return waterCondition;
    }

    public void setWaterCondition(WaterCondition waterCondition) {
        this.waterCondition = waterCondition;
    }

    public double getVirusPpm() {
        return virusPpm;
    }

    public void setVirusPpm(double virusPpm) {
        this.virusPpm = virusPpm;
    }

    public double getContaminentPpm() {
        return contaminentPpm;
    }

    public void setContaminentPpm(double contaminentPpm) {
        this.contaminentPpm = contaminentPpm;
    }
}
