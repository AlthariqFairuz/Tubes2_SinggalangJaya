package com.resources.logic.lib;

public class Coordinate {
    // Attribute
    private int row;
    private int col;

    // Constructor
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public static Coordinate CodeToCoordinate(String code) {
        // A1
        // A2
        // B1
        // ...
        // A -> 0
        // 1 -> 0
        // B -> 1
        // 2 -> 1
        // ...

        int row = code.charAt(0) - 'A';
        int col = code.charAt(1) - '1';

        return new Coordinate(row, col);
    }
}
