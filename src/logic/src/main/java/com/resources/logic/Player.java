package com.resources.logic;

public class Player {
    private int gold;
    private Land land;
    private Deck deck;

    public Player() {
        this.gold = 0;
        this.land = new Land();
        this.deck = new Deck(40, 5);
    }

    public int getGold() {
        return gold;
    }

    public Land getLand() {
        return land;
    }
}