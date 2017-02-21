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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private User currentUser;

    private Model() {
        users = new ArrayList<>();
        populateDummyData();
    }

    private void populateDummyData() {
        users.add(new User("Test User", "user", "pass", UserRole.ADMIN));
    }

    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new NoSuchElementException("Could not find specified user");
    }

    public void addUser(User user) {
        users.add(user);
    }

    public static Model getInstance() {
        return instance;
    }
}
