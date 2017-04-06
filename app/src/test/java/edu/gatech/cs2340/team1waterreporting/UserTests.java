package edu.gatech.cs2340.team1waterreporting;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.team1waterreporting.model.User;
import edu.gatech.cs2340.team1waterreporting.model.UserInputException;
import edu.gatech.cs2340.team1waterreporting.model.UserRole;

import static org.junit.Assert.*;

/**
 * Created by amrutKulkarni on 4/5/17.
 */

public class UserTests {
    private User testUser;

    @Before
    public void setup() {
        testUser = new User("name", "ID", "password", UserRole.USER);
        testUser.setTitle("title");
        testUser.setEmailAddress("email@address.com");
        testUser.setHomeAddress("address");
    }

    @Test
    public void testValidateName() {
        try {
            testUser.validateName("");
            fail("validates incorrect information");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Name cannot be empty.");
        }
    }

    @Test
    public void testValidateID() {
        try {
            testUser.validateId("");
            fail("validates incorrect information");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "ID cannot be empty.");
        }

        try {
            testUser.validateId("@*#$&!");
            fail("validates Id with incorrect characters");
        }  catch (UserInputException e) {
            assertEquals(e.getMessage(), "ID must consist of alphanumeric characters, -, _, and . only.");
        }
    }

    @Test
    public void testValidatePassword() {
        try {
            testUser.validatePassword("");
            fail("validates incorrect information");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Password cannot be empty.");
        }
    }

    @Test
    public void testCheckPassword() {
        try {
            testUser.checkPassword("wrongPassword");
            fail("validates incorrect password");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Incorrect password.");
        }
    }

    @Test
    public void testValidateEmailAddress() {
        try {
            testUser.validateEmailAddress("");
            fail("validates incorrect information");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Email Address cannot be empty.");
        }

        try {
            testUser.validatePassword("emailAddress");
            fail("validates incorrect email format");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Invalid email address. " +
                    "Email must be of form example@domain.com");
        }
    }

    @Test
    public void testValdiateHomeAddress() {
        try {
            testUser.validateHomeAddress("");
            fail("validates incorrect information");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Home Address cannot be empty.");
        }
    }

    @Test
    public void testGetName() {
        assertEquals(testUser.getName(), "name");
    }

    @Test
    public void testGetID() {
        assertEquals(testUser.getId(), "ID");
    }

    @Test
    public void testGetPassword() {
        assertEquals(testUser.getPassword(), "password");
    }

    @Test
    public void testGetEmailAddress() {
        assertEquals(testUser.getEmailAddress(), "email@address.com");
    }

    @Test
    public void testGetHomeAddress() {
        assertEquals(testUser.getHomeAddress(), "address");
    }
}
