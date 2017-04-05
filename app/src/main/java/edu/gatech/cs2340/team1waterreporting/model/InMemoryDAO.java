package edu.gatech.cs2340.team1waterreporting.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * An in-memory DAO for testing complete with dummy data.
 */

public class InMemoryDAO implements DAO {
    protected List<WaterPurityReport> waterPurityReports;
    protected List<WaterSourceReport> waterSourceReports;
    protected List<LogEvent> logEvents;
    protected List<User> users;
    protected User currentUser;

    // rough approximation of meters per lat-long
    private static final double METERS_IN_DEGREE = 111000.0;

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(User user) {
        currentUser = user;
    }

    @Override
    public boolean attemptLogin(String username, String password)
        throws UserInputException, NoSuchElementException{

        User u = Model.getInstance().getUserById(username);
        u.checkPassword(password);
        Model.getInstance().setCurrentUser(u);
        return true;
    }

    /**
     * Constructs a new instance of this DAO and populates it
     * with some dummy data.
     */
    public InMemoryDAO() {
        users = new ArrayList<>();
        waterSourceReports = new ArrayList<>();
        waterPurityReports = new ArrayList<>();
        logEvents = new ArrayList<>();
        populateDummyData();
    }

    private void populateDummyData() {
        users.add(new User("Test User", "user", "pass", UserRole.ADMIN));
        waterSourceReports.add(new WaterSourceReport(1, users.get(0),
            new Location(33.774358, -84.396463), WaterType.BOTTLED, WaterCondition.WASTE));

        waterPurityReports.add(new WaterPurityReport(1, users.get(0),
            new Location(34.774358, -85.396463),
            WaterCondition.TREATABLE_MUDDY, 1234.1234, 5678.5678));

        waterPurityReports.add(new WaterPurityReport(2, users.get(0),
            new Location(34.774358, -85.396463),
            WaterCondition.TREATABLE_MUDDY, 1234.1234, 5678.5678));
    }

    @Override
    public int getNewWaterSourceReportId() {
        if (waterSourceReports.size() == 0) {
            return 1;
        }
        return waterSourceReports.get(waterSourceReports.size() - 1).getNumber() + 1;

    }

    @Override
    public int getNewWaterPurityReportId() {
        if (waterPurityReports.size() == 0) {
            return 1;
        }
        return waterPurityReports.get(waterPurityReports.size() - 1).getNumber() + 1;

    }

    @Override
    public List<WaterSourceReport> getWaterSourceReports() {
        return waterSourceReports;
    }

    @Override
    public List<WaterPurityReport> getWaterPurityReports() {
        return waterPurityReports;
    }

    @Override
    public List<WaterPurityReport>
        getWaterPurityReportsByLocationYear(Location location, double radiusMeters, int year) {

        List<WaterPurityReport> result = new ArrayList();
        for(WaterPurityReport waterPurityReport : waterPurityReports) {
            if (waterPurityReport.getDate().getYear() == year && Math.abs(location.getLatitude() -
                waterPurityReport.getLocation().getLatitude()) <= radiusMeters / METERS_IN_DEGREE
                && Math.abs(location.getLongitude() -
                waterPurityReport.getLocation().getLongitude())
                <= radiusMeters / METERS_IN_DEGREE) {

                result.add(waterPurityReport);
            }
        }
        return result;
    }

    @Override
    public List<LogEvent> getLogEvents() {
        return logEvents;
    }

    @Override
    public void addWaterSourceReport(WaterSourceReport waterSourceReport) {
        waterSourceReports.add(waterSourceReport);
    }

    @Override
    public void addWaterPurityReport(WaterPurityReport waterPurityReport) {
        waterPurityReports.add(waterPurityReport);
    }

    @Override
    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new NoSuchElementException("Could not find specified user");
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void addLogEvent(LogEvent logEvent) {
        logEvents.add(logEvent);
    }
}
