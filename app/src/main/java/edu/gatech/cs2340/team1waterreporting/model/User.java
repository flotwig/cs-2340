package edu.gatech.cs2340.team1waterreporting.model;

import java.io.Serializable;

/**
 * Model for a user object.
 */
public class User implements Serializable {
    private String name;
    private String id;
    private String password;
    private String emailAddress;
    private String homeAddress;
    private String title;
    private UserRole userRole;

    /**
     * Constructor for a User object
     * @param name Real name
     * @param id Username
     * @param password Password (plaintext)
     * @param userRole Role in the system
     */
    public User(String name, String id, String password, UserRole userRole) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.userRole = userRole;
    }

    /**
     * Validates a name, throws an exception on error.
     * @param name Name to validate.
     * @throws UserInputException Has a user-friendly error message.
     */
    public static void validateName(String name) throws UserInputException {
        if (name.length() == 0) {
            throw new UserInputException("Name cannot be empty.");
        }
    }

    /**
     * Validates an id, throws an exception on error.
     * @param id Id to validate.
     * @throws UserInputException Has a user-friendly error message.
     */
    public static void validateId(String id) throws UserInputException {
        if (id.length() == 0) {
            throw new UserInputException("ID cannot be empty.");
        }
        if (!id.matches("[A-z0-9\\-_\\.]+")) {
            throw new UserInputException("ID must consist of alphanumeric characters, -, _, and . only.");
        }
    }

    /**
     * Validates a password, throws an exception on error.
     * @param password Password to validate.
     * @throws UserInputException Has a user-friendly error message.
     */
    public static void validatePassword(String password) throws UserInputException {
        if (password.length() == 0) {
            throw new UserInputException("Password cannot be empty.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Validates a password and checks if it is correct on the current user.
     * @param password The password to test.
     * @throws UserInputException if the password is invalid or incorrect.
     */
    public void checkPassword(String password) throws UserInputException {
        validatePassword(password);
        if (!password.equals(this.password)) {
            throw new UserInputException("Incorrect password.");
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Validates an Email Address, throws an exception on error.
     * @param emailAddress Email Address to validate.
     * @throws UserInputException Has a user-friendly error message.
     */
    public static void validateEmailAddress(String emailAddress) throws UserInputException {
        if (emailAddress.length() == 0) {
            throw new UserInputException("Email Address cannot be empty.");
        }
        if (!emailAddress.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")) {
            throw new UserInputException("Invalid email address. Email must be of form example@domain.com");
        }
    }

    public String getEmailAddress() {return emailAddress;}

    public void setEmailAddress(String emailAddress) {this.emailAddress = emailAddress;}

    /**
     * Validates an Home Address, throws an exception on error.
     * @param homeAddress Home Address to validate.
     * @throws UserInputException Has a user-friendly error message.
     */
    public static void validateHomeAddress(String homeAddress) throws UserInputException {
        if (homeAddress.length() == 0) {
            throw new UserInputException("Home Address cannot be empty.");
        }
    }

    public String getHomeAddress() {return homeAddress;}

    public void setHomeAddress(String homeAddress) {this.homeAddress = homeAddress;}

    public String getTitle () {return title;}

    public void setTitle(String title) {this.title = title;}

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
