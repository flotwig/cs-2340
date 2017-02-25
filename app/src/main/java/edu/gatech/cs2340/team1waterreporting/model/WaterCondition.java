package edu.gatech.cs2340.team1waterreporting.model;

/**
 * Created by zbloo on 2/24/2017.
 */

public enum WaterCondition {
    WASTE("Waste"),
    TREATABLE_CLEAR("Treatable-Clear"),
    TREATABLE_MUDDY("Treatable-Muddy"),
    POTABLE("Potable");

    String prettyName;

    WaterCondition(String prettyName) {
        this.prettyName = prettyName;
    }

    @Override
    public String toString() {
        return prettyName;
    }
}
