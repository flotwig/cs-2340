package edu.gatech.cs2340.team1waterreporting.model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A DAO backed by firebase.
 */

public class FirebaseDAO extends InMemoryDAO implements ValueEventListener {
    FirebaseDatabase database;
    DatabaseReference root;

    FirebaseDAO() {
        database = FirebaseDatabase.getInstance();
        root = database.getReference();
        root.child("users").addValueEventListener(this);
        root.child("waterSourceReports").addValueEventListener(this);
        root.child("waterPurityReports").addValueEventListener(this);
        database.goOnline();
    }

    @Override
    public void addWaterSourceReport(WaterSourceReport waterSourceReport) {
        root.child("waterSourceReports").push().setValue(waterSourceReport);
    }

    @Override
    public void addWaterPurityReport(WaterPurityReport waterPurityReport) {
        root.child("waterPurityReports").push().setValue(waterPurityReport);
    }

    @Override
    public void addUser(User user) {
        root.child("users").child(user.getId()).setValue(user);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        switch (dataSnapshot.getRef().getKey()) {
            case "waterPurityReports":
                waterPurityReports = iterableToList(WaterPurityReport.class, dataSnapshot.getChildren());
            case "waterSourceReports":
                waterSourceReports = iterableToList(WaterSourceReport.class, dataSnapshot.getChildren());
            case "users":
                users = iterableToList(User.class, dataSnapshot.getChildren());
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        // TODO: handle an error
        throw databaseError.toException();
    }

    private <T> List<T> iterableToList(Class cls, Iterable<DataSnapshot> iterable) {
        List<T> list = new ArrayList<T>();
        for(DataSnapshot dataSnapshot : iterable) {
            list.add((T) dataSnapshot.getValue(cls));
        }
        return list;
    }
}
