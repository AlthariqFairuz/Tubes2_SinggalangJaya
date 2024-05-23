package com.resources.gui;

public class DragDropContent {
    String name;
    int row;
    int col;

    public DragDropContent(String name, int row, int col) {
        this.name = name;
        this.row = row;
        this.col = col;
    }

    public String getName() {
        return name;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return name + "," + Integer.valueOf(row) + "," + Integer.valueOf(col);
    }

    public static DragDropContent parseString(String input) {
        String[] parts = input.split(",");
        return new DragDropContent(parts[0], Integer.valueOf(parts[1]), Integer.valueOf(parts[2]));
    }
}
