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

    public static String CoordinateToCode(Coordinate coordinate) {
        // r,c = 0,0 -> A01
        // r,c = 0, 1 -> B01
        // r,c = 1, 0 -> A01
        // r,c = 1, 1 -> B01

        // Convert column to character
        char colChar = (char) (coordinate.getCol() + 'A');

        // Convert row to number
        int row = coordinate.getRow() + 1;

        return String.format("%c%02d", colChar, row);
    }

    public static Coordinate CodeToCoordinate(String code) {
        // r,c = 0,0 -> A01
        // r,c = 0, 1 -> B01
        // r,c = 1, 0 -> A01
        // r,c = 1, 1 -> B01

        // Get column alphabet
        String colStr = code.substring(0, 1);
        int col = colStr.charAt(0) - 'A';

        // Get row number
        int row = Integer.parseInt(code.substring(1, 3)) - 1;

        return new Coordinate(row, col);
    }
}
