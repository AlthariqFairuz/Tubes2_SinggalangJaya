package com.resources.logic;

public class Land {
    private int row;
    private int col;

    private CardSlot[][] cardSlots;

    public Land(int row, int col) {
        this.row = row;
        this.col = col;
        this.cardSlots = new CardSlot[row][col];
    }

    public Land(int row, int col, CardSlot[][] cardSlots) {
        this.row = row;
        this.col = col;
        this.cardSlots = cardSlots;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CardSlot[][] getCardSlots() {
        return cardSlots;
    }
}
