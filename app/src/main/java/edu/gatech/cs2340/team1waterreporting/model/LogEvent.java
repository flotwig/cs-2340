package edu.gatech.cs2340.team1waterreporting.model;

import java.util.Date;
import java.util.HashMap;

/**
 * Log of security events.
 */

public class LogEvent {
    LogEventType type;
    Date date;
    User user;
    HashMap<String, String> metadata;

    private LogEvent() {

    }

    public LogEvent(User user, LogEventType type) {
        date = new Date();
        this.user = user;
        this.type = type;
    }

    public LogEvent(User user, LogEventType type, HashMap<String, String> metadata) {
        this(user, type);
        this.metadata = metadata;
    }

    public LogEventType getType() {
        return type;
    }

    public void setType(LogEventType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HashMap<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(HashMap<String, String> metadata) {
        this.metadata = metadata;
    }
}
