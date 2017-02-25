package edu.gatech.cs2340.team1waterreporting.model;

import java.util.Date;

/**
 * Created by zbloo on 2/24/2017.
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
        this.number = Model.getInstance().getNewWaterSourceReportId();
    }

    public WaterSourceReport(User reporter, Location location, WaterType waterType, WaterCondition waterCondition) {
        this();
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
