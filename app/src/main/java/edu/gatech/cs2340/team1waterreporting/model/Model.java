package edu.gatech.cs2340.team1waterreporting.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This model serves as the sole data access object for the controllers.
 */

public class Model {
    private static final Model instance = new Model();
    private List<User> users;

    public List<WaterSourceReport> getWaterSourceReports() {
        return waterSourceReports;
    }

    private List<WaterSourceReport> waterSourceReports;

    public int getNewWaterSourceReportId() {
        return waterSourceReports.get(waterSourceReports.size() - 1).getNumber() + 1;
    }

    public void addWaterSourceReport(WaterSourceReport waterSourceReport) {
        waterSourceReports.add(waterSourceReport);
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
        populateDummyData();
    }

    private void populateDummyData() {
        User u = new User("Test User", "user", "pass", UserRole.ADMIN);
        users.add(u);
        waterSourceReports.add(new WaterSourceReport(u, new Location(33.774358, 84.396463), WaterType.BOTTLED, WaterCondition.WASTE));
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

    public static Model getInstance() {
        return instance;
    }
}
