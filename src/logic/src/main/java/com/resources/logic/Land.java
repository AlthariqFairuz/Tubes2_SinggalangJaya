package com.resources.logic;

import static com.resources.logic.CardAssets.getRandomCard;

public class Land {
    private int row;
    private int col;

    private CardSlot[][] cardSlots;

    public Land() {
        this.row = 4;
        this.col = 5;
        this.cardSlots = new CardSlot[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.cardSlots[i][j] = new CardSlot();
            }
        }
    }

    public void seed() {
        while (!isFull()) {
            addRandomCard();
        }
    }

    public void setLandSlot(int row, int col, Card card) {
        if (row < 0 || row >= this.row || col < 0 || col >= this.col) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        cardSlots[row][col].setCard(card);
    }

    public int getFilledSlotCount() {
        int count = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (cardSlots[i][j].hasCard()) {
                    count++;
                }
            }
        }

        return count;
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

    public void addRandomCard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!cardSlots[i][j].hasCard()) {
                    cardSlots[i][j].setCard(getRandomCard());
                    return;
                }
            }
        }
        System.out.println("Can't add a random card to the land because it is already full.");
    }


    public boolean isFull() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!cardSlots[i][j].hasCard()) {
                    return false;
                }
            }
        }
        return true;
    }
}
