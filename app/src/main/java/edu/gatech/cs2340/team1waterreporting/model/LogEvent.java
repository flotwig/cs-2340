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

    /**
     * Constructor for new log event with today's date.
     * @param user property value
     * @param type property value
     */
    public LogEvent(User user, LogEventType type) {
        date = new Date();
        this.user = user;
        this.type = type;
    }

    /**
     * Constructor for new log event with today's date.
     * @param user property value
     * @param type property value
     * @param metadata property value
     */
    public LogEvent(User user, LogEventType type, HashMap<String, String> metadata) {
        this(user, type);
        this.metadata = metadata;
    }

    /**
     * Get log event type.
     * @return log event type
     */
    public LogEventType getType() {
        return type;
    }

    /**
     * Set log event type.
     * @param type new log event type
     */
    public void setType(LogEventType type) {
        this.type = type;
    }

    /**
     * Get date.
     * @return log event data
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set date.
     * @param date new log event date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get user. Can be null.
     * @return user belonging to log event.
     */
    public User getUser() {
        return user;
    }

    /**
     * Set user.
     * @param user new user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get log event metadata.
     * @return event metadata
     */
    public HashMap<String, String> getMetadata() {
        return metadata;
    }

    /**
     * Set log event metadata.
     * @param metadata new metadata
     */
    public void setMetadata(HashMap<String, String> metadata) {
        this.metadata = metadata;
    }
}
