package edu.gatech.cs2340.team1waterreporting.model;

/**
 * Different roles a user may have.
 */

public enum UserRole {
    USER("User"),
    WORKER("Worker"),
    MANAGER("Manager"),
    ADMIN("Administrator");

    private String prettyName;

    UserRole(String prettyName) {
        this.prettyName = prettyName;
    }

    @Override
    public String toString() {
        return prettyName;
    }
}
