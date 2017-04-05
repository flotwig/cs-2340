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

    private User() {

    }

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
            throw new
                UserInputException("ID must consist of alphanumeric characters, -, _, and . only.");
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

    /**
     * Get real name of user.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set real name of user.
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get user ID (username)
     * @return user ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set user ID (username)
     * @param id user ID
     */
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

    /**
     * Returns current password. Needed for Firebase ORM to work properly.
     * DO NOT REMOVE
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets user password.
     * @param password user's new password
     */
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
            throw new UserInputException("Invalid email address. " +
                "Email must be of form example@domain.com");
        }
    }

    /**
     * Get user's email address
     * @return email address
     */
    public String getEmailAddress() {return emailAddress;}

    /**
     * Set user's email address
     * @param emailAddress new email address
     */
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

    /**
     * Get home address
     * @return home address
     */
    public String getHomeAddress() {return homeAddress;}

    /**
     * Set user's new home address.
     * @param homeAddress new home address
     */
    public void setHomeAddress(String homeAddress) {this.homeAddress = homeAddress;}

    /**
     * Get user's title (Mr, Mrs, etc.)
     * @return user's title
     */
    public String getTitle() {return title;}

    /**
     * Set user's title
     * @param title user's new title
     */
    public void setTitle(String title) {this.title = title;}

    /**
     * Get user's role
     * @return user's role
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Set user's role
     * @param userRole new user role
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
