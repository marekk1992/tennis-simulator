package org.example.tennissimulator.match;

public enum Point {

    LOVE("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), A("A"), GAME("GAME");

    String value;

    Point(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
