package edu.gatech.cs2340.team1waterreporting.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This model serves as the sole data access object for the controllers.
 */

public class Model {
    private static Model instance = new Model();
    private List<User> users;

    public List<WaterSourceReport> getWaterSourceReports() {
        return waterSourceReports;
    }

    private List<WaterPurityReport> waterPurityReports;
    private List<WaterSourceReport> waterSourceReports;

    public List<WaterPurityReport> getWaterPurityReports() {
        return waterPurityReports;
    }

    public int getNewWaterSourceReportId() {
        if (waterSourceReports.size() == 0) {
            return 1;
        }
        return waterSourceReports.get(waterSourceReports.size() - 1).getNumber() + 1;
    }

    public int getNewWaterPurityReportId() {
        if (waterPurityReports.size() == 0) {
            return 1;
        }
        return waterPurityReports.get(waterPurityReports.size() - 1).getNumber() + 1;
    }

    /**
     * Adds a new water source report to the list.
     * @param waterSourceReport Water source report to add.
     */
    public void addWaterSourceReport(WaterSourceReport waterSourceReport) {
        waterSourceReports.add(waterSourceReport);
    }

    /**
     * Adds a new water purity report to the list.
     * @param waterPurityReport Water purity report to add.
     */
    public void addWaterPurityReport(WaterPurityReport waterPurityReport) {
        waterPurityReports.add(waterPurityReport);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private User currentUser;

    private Model() {
        users = new ArrayList<>();
        waterSourceReports = new ArrayList<>();
        waterPurityReports = new ArrayList<>();
        populateDummyData();
    }

    private void populateDummyData() {
        users.add(new User("Test User", "user", "pass", UserRole.ADMIN));
        waterSourceReports.add(new WaterSourceReport(1, users.get(0), new Location(33.774358, 84.396463), WaterType.BOTTLED, WaterCondition.WASTE));
        waterPurityReports.add(new WaterPurityReport(1, users.get(0), new Location(34.774358, 85.396463), WaterCondition.TREATABLE_MUDDY, 1234.1234, 5678.5678));
    }

    /**
     * Given an id/username, return a user object with a matching id.
     * @param id The id to search for.
     * @throws NoSuchElementException if a matching user could not be found.
     * @return The found user object.
     */
    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new NoSuchElementException("Could not find specified user");
    }

    /**
     * Adds a new user to the stored users list. Does not do validation of user info.
     * @param user The user object to append to the user list.
     */
    public void addUser(User user) {
        users.add(user);
    }

    public synchronized static Model getInstance() {
        if (instance == null)
            instance = new Model();
        return instance;
    }
}
