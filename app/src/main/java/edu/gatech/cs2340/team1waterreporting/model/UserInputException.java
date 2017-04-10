package edu.gatech.cs2340.team1waterreporting.model;

/**
 * A dummy exception class to handle bad user input.
 */

@SuppressWarnings("ALL")
public class UserInputException extends Exception {
    /**
     * Constructor to set a message.
     * @param message Message to set.
     */
    public UserInputException(String message) {
        super(message);
    }
}
