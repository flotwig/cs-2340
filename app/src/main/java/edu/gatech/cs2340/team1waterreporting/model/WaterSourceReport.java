package edu.gatech.cs2340.team1waterreporting.model;

import java.util.Date;

/**
 * Model to contain a report on a water source.
 */

public class WaterSourceReport {
    private Date date;
    private int number;
    private User reporter;
    private Location location;
    private WaterType waterType;
    private WaterCondition waterCondition;

    private WaterSourceReport() {
        this.date = new Date();
    }

    public WaterSourceReport(int number, User reporter, Location location, WaterType waterType, WaterCondition waterCondition) {
        this();
        this.number = number;
        this.reporter = reporter;
        this.location = location;
        this.waterType = waterType;
        this.waterCondition = waterCondition;
    }

    public Date getDate() {
        return date;
    }

    public int getNumber() {
        return number;
    }

    public User getReporter() {
        return reporter;
    }

    public Location getLocation() {
        return location;
    }

    public WaterType getWaterType() {
        return waterType;
    }

    public WaterCondition getWaterCondition() {
        return waterCondition;
    }
}
