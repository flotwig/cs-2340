package edu.gatech.cs2340.team1waterreporting;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import edu.gatech.cs2340.team1waterreporting.model.InMemoryDAO;
import edu.gatech.cs2340.team1waterreporting.model.User;

import static org.junit.Assert.*;

/**
 * Test cases for the in-memory DAO.
 */
@SuppressWarnings("ChainedMethodCall")
public class InMemoryDAOTests {
    private InMemoryDAO dao;

    @Before
    public void setUp() {
        dao = new InMemoryDAO();
        // DAO is populated with dummy data, InMemoryDAO.populateDummyData
    }

    /**
     * Simple test which just checks to see if an incremented ID is returned.
     */
    @Test
    public void getNewWaterSourceReportIdTest() {
        assertEquals(dao.getNewWaterPurityReportId(), dao.getWaterPurityReports().size() + 1);
    }

    /**
     * Test on user searching method. Tests case where user exists and case
     * where user does not exist.
     *
     * @author Zach Bloomquist <bloomquist@gatech.edu>
     */
    @Test
    public void getUserByIdTest() {
        User user = dao.getUserById("user");
        assertEquals("Test User", user.getName());
        try {
            dao.getUserById("non-existent");
            fail("user returned for non-existent ID");
        } catch (NoSuchElementException e) {
            assertEquals(e.getMessage(), "Could not find specified user");
        }
    }
}