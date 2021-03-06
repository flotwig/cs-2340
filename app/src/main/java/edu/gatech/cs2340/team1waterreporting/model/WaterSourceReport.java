package edu.gatech.cs2340.team1waterreporting.model;

import java.util.Date;
import java.util.Locale;

/**
 * Model to contain a report on a water source.
 */

public class WaterSourceReport {
    private final Date date;
    private int number;
    private User reporter;
    private Location location;
    private WaterType waterType;
    private WaterCondition waterCondition;

    private WaterSourceReport() {
        this.date = new Date();
    }

    /**
     * Constructs a new WaterSourceReport with current date
     * @param number property value
     * @param reporter property value
     * @param location property value
     * @param waterType property value
     * @param waterCondition property value
     */
    public WaterSourceReport(int number, User reporter, Location location, WaterType waterType,
         WaterCondition waterCondition) {

        this();
        this.number = number;
        this.reporter = reporter;
        this.location = location;
        this.waterType = waterType;
        this.waterCondition = waterCondition;
    }

    /**
     * Get report date
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get report number
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Get reporter
     * @return reporting user
     */
    public User getReporter() {
        return reporter;
    }

    /**
     * Get location
     * @return report location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Get water type
     * @return water type
     */
    public WaterType getWaterType() {
        return waterType;
    }

    /**
     * Get water condition
     * @return water condition
     */
    public WaterCondition getWaterCondition() {
        return waterCondition;
    }

    @Override
    public String toString() {
        return String.format(
                Locale.getDefault(),
                "#%d: %s, %s at %f, %f. \nReported by %s at %s.",
                number,
                waterCondition.toString(),
                waterType.toString(),
                location.getLatitude(),
                location.getLongitude(),
                reporter.getName(),
                date.toString());
    }
}
