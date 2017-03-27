package edu.gatech.cs2340.team1waterreporting.model;

/**
 * Different user roles available in the applications.
 */

public enum UserRole {
    USER("User"),
    WORKER("Worker"),
    MANAGER("Manager"),
    ADMIN("Administrator");

    private final String prettyName;

    UserRole(String prettyName) {
        this.prettyName = prettyName;
    }

    @Override
    public String toString() {
        return prettyName;
    }
}
