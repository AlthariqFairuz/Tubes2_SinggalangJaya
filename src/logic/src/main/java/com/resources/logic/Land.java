package com.resources.logic;

public class Land {
    private int row;
    private int col;

    private CardSlot[][] cardSlots;

    public Land(int row, int col) {
        this.row = row;
        this.col = col;
        this.cardSlots = new CardSlot[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cardSlots[i][j] = new CardSlot();
            }
        }
    }

    public Land(int row, int col, CardSlot[][] cardSlots) {
        this.row = row;
        this.col = col;
        this.cardSlots = cardSlots;
    }

    public void setLandSlot(int row, int col, Card card) {
        if (row < 0 || row >= this.row || col < 0 || col >= this.col) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        cardSlots[row][col].setCard(card);
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
