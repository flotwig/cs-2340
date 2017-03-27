package edu.gatech.cs2340.team1waterreporting.model;

/**
 * Possible types of log events.
 */

public enum LogEventType {
    LOGIN_ATTEMPT("Login Attempt"),
    ACCOUNT_DELETE("Account Deleted"),
    USER_BAN("User Ban"),
    UNBLOCK_ACCOUNT("Unblock Account"),
    REPORT_DELETE("Report Deleted");

    private final String prettyName;

    LogEventType(String prettyName) {
        this.prettyName = prettyName;
    }

    @Override
    public String toString() {
        return prettyName;
    }
}
