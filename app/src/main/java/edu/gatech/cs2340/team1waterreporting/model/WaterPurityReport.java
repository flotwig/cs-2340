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
    private double contaminantPpm;

    private WaterPurityReport() {
        this.date = new Date();
    }

    /**
     * Constructs a new WaterPurityReport with the current date and time and these parameters.
     * @param number property value
     * @param reporter property value
     * @param location property value
     * @param waterCondition property value
     * @param virusPpm property value
     * @param contaminantPpm property value
     */
    @SuppressWarnings("ConstructorWithTooManyParameters")
    public WaterPurityReport(int number, User reporter, Location location,
        WaterCondition waterCondition, double virusPpm, double contaminantPpm) {

        this();
        this.number = number;
        this.reporter = reporter;
        this.location = location;
        this.waterCondition = waterCondition;
        this.virusPpm = virusPpm;
        this.contaminantPpm = contaminantPpm;
    }

    /**
     * Validates a ppm to be less than or equal to a million and greater than 0.
     * @param ppm PPM to verify
     * @throws UserInputException if the conditions are not met
     */
    public static void validatePpm(String ppm) throws UserInputException {
        if (Float.parseFloat(ppm) >= 1E6) {
            throw new UserInputException("Cannot have more than a million ppm");
        } else if (Float.parseFloat(ppm) < 0) {
            throw new UserInputException("Cannot have negative ppm");
        }
    }

    /**
     * Get report date
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set report date
     * @param date new date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get report number
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set report number
     * @param number new number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Get reporter
     * @return reporting user
     */
    public User getReporter() {
        return reporter;
    }

    /**
     * Set reporter
     * @param reporter new reporting user
     */
    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    /**
     * Get location
     * @return report location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Set location
     * @param location new location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Get water condition
     * @return water condition
     */
    public WaterCondition getWaterCondition() {
        return waterCondition;
    }

    /**
     * Set water condition
     * @param waterCondition new water condition
     */
    public void setWaterCondition(WaterCondition waterCondition) {
        this.waterCondition = waterCondition;
    }

    /**
     * Get virus PPM
     * @return virus PPM
     */
    public double getVirusPpm() {
        return virusPpm;
    }

    /**
     * Set virus PPM
     * @param virusPpm new virus PPM
     */
    public void setVirusPpm(double virusPpm) {
        this.virusPpm = virusPpm;
    }

    /**
     * Get contaminant PPM
     * @return contaminant PPM
     */
    public double getContaminantPpm() {
        return contaminantPpm;
    }

    /**
     * Set contaminant PPM
     * @param contaminantPpm new contaminant PPM
     */
    public void setContaminantPpm(double contaminantPpm) {
        this.contaminantPpm = contaminantPpm;
    }
}
