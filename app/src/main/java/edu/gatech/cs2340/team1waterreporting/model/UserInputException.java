package edu.gatech.cs2340.team1waterreporting.model;

/**
 * A dummy exception class to handle bad user input.
 */

public class UserInputException extends Exception {
    public UserInputException(String message) {
        super(message);
    }
}
