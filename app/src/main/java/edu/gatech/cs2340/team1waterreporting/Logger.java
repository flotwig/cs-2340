package edu.gatech.cs2340.team1waterreporting;

import java.util.HashMap;

import edu.gatech.cs2340.team1waterreporting.model.LogEvent;
import edu.gatech.cs2340.team1waterreporting.model.LogEventType;
import edu.gatech.cs2340.team1waterreporting.model.Model;

/**
 * Logging shortcuts.
 * TODO: figure out if this should go somewhere else
 */

@SuppressWarnings({"UtilityClass", "unchecked"})
class Logger {
    /**
     * Store a login attempt log event
     * @param outcome "Unknown ID", "Bad Password", "Success"
     * @param userId userId the user tried to login with
     */
    public static void logLoginAttempt(String outcome, String userId) {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("outcome", outcome);
        hashMap.put("userId", userId);
        log(LogEventType.LOGIN_ATTEMPT, hashMap);
    }

    /**
     * Store log event without metadata.
     * @param logEventType Type for log event
     */
    public static void log(LogEventType logEventType) {
        LogEvent logEvent = new LogEvent(Model.getInstance().getCurrentUser(), logEventType);
        Model.getInstance().addLogEvent(logEvent);
    }

    /**
     * Store log event with metadata.
     * @param logEventType Type for log event
     * @param metadata HashMap for metadata
     */
    private static void log(LogEventType logEventType, HashMap<String, String> metadata) {
        LogEvent logEvent =
            new LogEvent(Model.getInstance().getCurrentUser(), logEventType, metadata);
        Model.getInstance().addLogEvent(logEvent);
    }
}
