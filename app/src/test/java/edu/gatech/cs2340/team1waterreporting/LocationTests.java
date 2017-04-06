package edu.gatech.cs2340.team1waterreporting;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import edu.gatech.cs2340.team1waterreporting.model.Location;
import edu.gatech.cs2340.team1waterreporting.model.User;
import edu.gatech.cs2340.team1waterreporting.model.UserInputException;
import edu.gatech.cs2340.team1waterreporting.model.UserRole;

import static org.junit.Assert.*;

/**
 * Test cases for the Locations
 * Author: Trevor Sorrells
 */

public class LocationTests {
    private Location location;

    @Before
    public void setup() {
        location = new Location(30.0000, 90.0000);
    }

    /**
     * Test to make sure getLongitude returns the correct value
     */
    @Test
    public void getLongitudeTest() {
        assertEquals(location.getLongitude(), 90.0000, 0);
    }

    /**
     * Test to make sure the getLatitude returns the correct value
     */
    @Test
    public void getLatitudeTest() {
        assertEquals(location.getLatitude(), 30.0000,0) ;
    }

    @Test
    public void validateLongitudeTest() {
        User user = new User("Test User", "user", "pass", UserRole.ADMIN);
        assertEquals("Test User", user.getName());
        try {
            location.validateLongitude("");
            fail("validated on incorrect data");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Longitude cannot be empty.");
        }
        try {
            location.validateLongitude("181");
            fail("validated on incorrect data");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Longitude must be between -180 and 180 degrees.");
        }
        try {
            location.validateLongitude("abc");
            fail("validated on incorrect data");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Longitude must be a number.");
        }
    }

    @Test
    public void validateLatitudeTest() {
        User user = new User("Test User", "user", "pass", UserRole.ADMIN);
        assertEquals("Test User", user.getName());
        try {
            location.validateLatitude("");
            fail("validated on incorrect data");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Latitude cannot be empty.");
        }
        try {
            location.validateLatitude("91");
            fail("validated on incorrect data");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Latitude must be between -90 and 90 degrees.");
        }
        try {
            location.validateLatitude("abc");
            fail("validated on incorrect data");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Latitude must be a number.");
        }
    }
}
