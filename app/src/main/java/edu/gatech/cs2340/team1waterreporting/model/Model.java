package edu.gatech.cs2340.team1waterreporting.model;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This model serves as the sole data access object for the controllers.
 */

public class Model extends FirebaseDAO {
    private static Model instance = new Model();

    private Model() {
        super();
    }

    public synchronized static Model getInstance() {
        if (instance == null)
            instance = new Model();
        return instance;
    }
}
