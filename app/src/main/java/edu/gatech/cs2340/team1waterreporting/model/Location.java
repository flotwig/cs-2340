package edu.gatech.cs2340.team1waterreporting.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Model for a location consisting of latitude and longitude
 */

public class Location {
    private double longitude;
    private double latitude;

    // empty constructor required for Firebase
    private Location() {

    }

    /**
     * Constructor for a location.
     * @param latitude Latitude
     * @param longitude Longitude
     */
    public Location(double latitude, double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Convenience constructor for a location.
     * @param latitude Latitude as a float string
     * @param longitude Longitude as a float string
     */
    public Location(String latitude, String longitude) {
        this(Float.parseFloat(latitude), Float.parseFloat(longitude));
    }

    /**
     * Get longitude.
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set longitude.
     * @param longitude new longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Get latitude.
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set latitude.
     * @param latitude new latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Validates a latitude string input.
     * @param latitude Input to check
     * @throws UserInputException if the latitude is invalid
     */
    public static void validateLatitude(String latitude) throws UserInputException {
        if (latitude.isEmpty()) {
            throw new UserInputException("Latitude cannot be empty.");
        }
        try {
            Float lat = Float.parseFloat(latitude);
            if ((lat < -90) || (lat > 90)) {
                throw new UserInputException("Latitude must be between -90 and 90 degrees.");
            }
        } catch (NumberFormatException e) {
            throw new UserInputException("Latitude must be a number.");
        }
    }

    /**
     * Validates a longitude string input.
     * @param longitude Input to check
     * @throws UserInputException if the longitude is invalid
     */
    public static void validateLongitude(String longitude) throws UserInputException {
        if (longitude.isEmpty()) {
            throw new UserInputException("Longitude cannot be empty.");
        }
        try {
            Float lon = Float.parseFloat(longitude);
            if ((lon < -180) || (lon > 180)) {
                throw new UserInputException("Longitude must be between -180 and 180 degrees.");
            }
        } catch (NumberFormatException e) {
            throw new UserInputException("Longitude must be a number.");
        }
    }

    /**
     * Generate LatLng for google maps from this location
     * @return a LatLng corresponding to this Location
     */
    public LatLng toLatLng() {
        return new LatLng(latitude, longitude);
    }
}
