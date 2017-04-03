package edu.gatech.cs2340.team1waterreporting.model;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * General interface for data access objects for this app.
 */

public interface DAO {
    User getCurrentUser();
    void setCurrentUser(User user);
    /**
     * Try a username and password against auth backend.
     * currentUser will be set if this succeeds.
     * @param username username to check
     * @param password password to check
     * @return if the user could be logged in
     * @throws UserInputException on bad password
     * @throws NoSuchElementException on user not found
     */
    boolean attemptLogin(String username, String password) throws UserInputException, NoSuchElementException;
    int getNewWaterSourceReportId();
    int getNewWaterPurityReportId();
    List<WaterSourceReport> getWaterSourceReports();
    List<WaterPurityReport> getWaterPurityReports();

    /**
     * Gets a list of water source reports that match the criteria.
     * @param location Location to search for.
     * @param radiusMeters Meters around location to search. (square radius)
     * @param year Target year.
     * @return A list of matching water source reports.
     */
    List<WaterPurityReport> getWaterPurityReportsByLocationYear(Location location, double radiusMeters, int year);
    List<LogEvent> getLogEvents();
    /**
     * Adds a new water source report to the list.
     * @param waterSourceReport Water source report to add.
     */
    void addWaterSourceReport(WaterSourceReport waterSourceReport);
    /**
     * Adds a new water purity report to the list.
     * @param waterPurityReport Water purity report to add.
     */
    void addWaterPurityReport(WaterPurityReport waterPurityReport);
    /**
     * Given an id/username, return a user object with a matching id.
     * @param id The id to search for.
     * @throws NoSuchElementException if a matching user could not be found.
     * @return The found user object.
     */
    User getUserById(String id);
    /**
     * Adds a new user to the stored users list. Does not do validation of user info.
     * @param user The user object to append to the user list.
     */
    void addUser(User user);

    /**
     * Store a log event.
     * @param logEvent Log event to store
     */
    void addLogEvent(LogEvent logEvent);
}
