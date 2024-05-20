package com.resources.logic;

public class Deck {
    private int totalDeckSize; // Non-active + active
    private int totalActiveCards; // Active only
    private Card[] nonActiveCards; // Size is totalDeckSize - totalActiveCards
    private Card[] ActiveCards; // Size is totalActiveCards

    public Deck(int totalDeckSize, int totalActiveCards) {
        this.totalDeckSize = totalDeckSize;
        this.totalActiveCards = totalActiveCards;

        nonActiveCards = new Card[totalDeckSize - totalActiveCards];
        ActiveCards = new Card[totalActiveCards];
    }
    
}
