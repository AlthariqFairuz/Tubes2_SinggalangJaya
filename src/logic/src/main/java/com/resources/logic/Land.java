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

    public CardSlot[][] getCardSlots() {
        return cardSlots;
    }
}
