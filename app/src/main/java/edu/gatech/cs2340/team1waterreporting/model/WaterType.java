package edu.gatech.cs2340.team1waterreporting.model;

/**
 * Possible water source types.
 */

public enum WaterType {
    BOTTLED("Bottled"),
    WELL("Well"),
    STREAM("Stream"),
    LAKE("Lake"),
    SPRING("Spring"),
    OTHER("Other");

    final String prettyName;

    WaterType(String prettyName) {
        this.prettyName = prettyName;
    }

    @Override
    public String toString() {
        return prettyName;
    }
}
