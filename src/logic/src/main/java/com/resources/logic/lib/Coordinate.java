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
        // 0, 0 -> A01
        // 0, 1 -> A02
        // 1, 0 -> B01
        // 1, 1 -> B02
        // ...

        // Convert row to character
        char row = (char) ('A' + coordinate.getRow());

        // Convert col to number
        int col = coordinate.getCol() + 1;

        return String.format("%c%02d", row, col);
    }

    public static Coordinate CodeToCoordinate(String code) {
        // A01 -> 0, 0
        // A02 -> 0, 1
        // B01 -> 1, 0
        // B02 -> 1, 1
        // ...

        // Convert character to row
        String codeChar = code.substring(0, 1);
        int row = codeChar.charAt(0) - 'A';

        // Convert number to col
        int col = Integer.parseInt(code.substring(1)) - 1;

        return new Coordinate(row, col);
    }
}
