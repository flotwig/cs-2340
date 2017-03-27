package edu.gatech.cs2340.team1waterreporting.model;

/**
 * Enum to store current condition of water.
 */

public enum WaterCondition {
    WASTE("Waste"),
    TREATABLE_CLEAR("Treatable-Clear"),
    TREATABLE_MUDDY("Treatable-Muddy"),
    POTABLE("Potable");

    final String prettyName;

    WaterCondition(String prettyName) {
        this.prettyName = prettyName;
    }

    @Override
    public String toString() {
        return prettyName;
    }
}
