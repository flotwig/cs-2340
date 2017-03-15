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
    protected List<User> users;
    protected User currentUser;

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(User user) {
        currentUser = user;
    }

    @Override
    public boolean attemptLogin(String username, String password) {
        try {
            User u = Model.getInstance().getUserById(username);
            u.checkPassword(password);
            Model.getInstance().setCurrentUser(u);
        } catch (NoSuchElementException e) {
            return false;
        } catch (UserInputException e) {
            return false;
        }
        return true;
    }

    InMemoryDAO() {
        users = new ArrayList<>();
        waterSourceReports = new ArrayList<>();
        waterPurityReports = new ArrayList<>();
        populateDummyData();
    }

    private void populateDummyData() {
        users.add(new User("Test User", "user", "pass", UserRole.ADMIN));
        waterSourceReports.add(new WaterSourceReport(1, users.get(0), new Location(33.774358, -84.396463), WaterType.BOTTLED, WaterCondition.WASTE));
        waterPurityReports.add(new WaterPurityReport(1, users.get(0), new Location(34.774358, -85.396463), WaterCondition.TREATABLE_MUDDY, 1234.1234, 5678.5678));
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
}
