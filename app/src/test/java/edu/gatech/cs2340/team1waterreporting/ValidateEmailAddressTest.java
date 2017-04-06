package edu.gatech.cs2340.team1waterreporting;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.team1waterreporting.model.Location;
import edu.gatech.cs2340.team1waterreporting.model.User;
import edu.gatech.cs2340.team1waterreporting.model.UserInputException;
import edu.gatech.cs2340.team1waterreporting.model.UserRole;

/**
 * Test cases for the validateEmailAddress method in the User.java file
 * Author: Matthew Coleman
 */

public class ValidateEmailAddressTest {

    // Variable declaration
    private String emailAddress;

    // Set the test emailAddress to RIP_Firefly@gatech.edu
    @Before
    public void setup() {
        emailAddress = "RIP_Firefly@gatech.edu";
    }

    // Test for the value "" as an email address
    @Test
    public void nullTest() {

        // Variable declaration
        // Make a test user
        User user = new User("User Name", "UserName", "PassWord", UserRole.ADMIN);
        user.setEmailAddress(emailAddress);
        assertEquals("RIP_Firefly@gatech.edu", user.getEmailAddress());

        try {
            user.validateEmailAddress("");
            fail("Validated with an incorrect email address of null" +
                    "Email must be of form example@domain.com");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Email Address cannot be empty.");
        }
    }

    // Test for the value "onlyLetters" as an email address
    @Test
    public void onlyLettersTest() {

        // Variable declaration
        // Make a test user
        User user = new User("User Name", "UserName", "PassWord", UserRole.ADMIN);
        user.setEmailAddress(emailAddress);
        assertEquals("RIP_Firefly@gatech.edu", user.getEmailAddress());

        try {
            user.validateEmailAddress("onlyLetters");
            fail("Validated with an incorrect email address that was only letters. " +
                    "Email must be of form example@domain.com");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Invalid email address. " +
                    "Email must be of form example@domain.com");
        }
    }

    // Test for the value "1234567890" as an email address
    @Test
    public void onlyNumbersTest () {

        // Variable declaration
        // Make a test user
        User user = new User("User Name", "UserName", "PassWord", UserRole.ADMIN);
        user.setEmailAddress(emailAddress);
        assertEquals("RIP_Firefly@gatech.edu", user.getEmailAddress());

        try {
            user.validateEmailAddress("1234567890");
            fail("Validated with an incorrect email address that was only numbers. " +
                    "Email must be of form example@domain.com");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Invalid email address. " +
                    "Email must be of form example@domain.com");
        }
    }


    // Test for the value "example@" as an email address
    @Test
    public void exampleAtTest () {

        // Variable declaration
        // Make a test user
        User user = new User("User Name", "UserName", "PassWord", UserRole.ADMIN);
        user.setEmailAddress(emailAddress);
        assertEquals("RIP_Firefly@gatech.edu", user.getEmailAddress());

        try {
            user.validateEmailAddress("example@");
            fail("Validated with an incorrect email address that was missing the domain.com part. "
                    + "Email must be of form example@domain.com");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Invalid email address. " +
                    "Email must be of form example@domain.com");
        }
    }

    // Test for the value "example@domaincom" as an email address
    @Test
    public void exampleAtdomaincomTest () {

        // Variable declaration
        // Make a test user
        User user = new User("User Name", "UserName", "PassWord", UserRole.ADMIN);
        user.setEmailAddress(emailAddress);
        assertEquals("RIP_Firefly@gatech.edu", user.getEmailAddress());

        try {
            user.validateEmailAddress("example@domaincom");
            fail("Validated with an incorrect email address that was missing the 'dot' part. "
                    + "Email must be of form example@domain.com");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Invalid email address. " +
                    "Email must be of form example@domain.com");
        }
    }

    // Test for the value "exampledomain.com" as an email address
    @Test
    public void exampledomainDotcomTest () {

        // Variable declaration
        // Make a test user
        User user = new User("User Name", "UserName", "PassWord", UserRole.ADMIN);
        user.setEmailAddress(emailAddress);
        assertEquals("RIP_Firefly@gatech.edu", user.getEmailAddress());

        try {
            user.validateEmailAddress("exampledomain.com");
            fail("Validated with an incorrect email address that was missing the @ part. "
                    + "Email must be of form example@domain.com");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Invalid email address. " +
                    "Email must be of form example@domain.com");
        }
    }

    // Test for the value "exampledomaincom" as an email address
    @Test
    public void exampledomaincomTest () {

        // Variable declaration
        // Make a test user
        User user = new User("User Name", "UserName", "PassWord", UserRole.ADMIN);
        user.setEmailAddress(emailAddress);
        assertEquals("RIP_Firefly@gatech.edu", user.getEmailAddress());

        try {
            user.validateEmailAddress("exampledomaincom");
            fail("Validated with an incorrect email address that was missing both the " +
                    "'dot' part and the @ part. Email must be of form example@domain.com");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Invalid email address. " +
                    "Email must be of form example@domain.com");
        }
    }

    // Test for the value "example()[]{}<>$^*&!?,@domain.com" as an email address
    @Test
    public void specialCharacterTest () {

        // Variable declaration
        // Make a test user
        User user = new User("User Name", "UserName", "PassWord", UserRole.ADMIN);
        user.setEmailAddress(emailAddress);
        assertEquals("RIP_Firefly@gatech.edu", user.getEmailAddress());

        try {
            user.validateEmailAddress("example()[]{}<>$^*&!?,@domain.com");
            fail("Validated with an incorrect special character: ()[]{}<>$^*&!?. :"
                    + "Email must be of form example@domain.com");
        } catch (UserInputException e) {
            assertEquals(e.getMessage(), "Invalid email address. " +
                    "Email must be of form example@domain.com");
        }
    }
}
