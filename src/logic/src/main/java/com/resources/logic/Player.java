package com.resources.logic;

public class Player {
    private int gold;
    private Land land;
    private Deck deck;

    public Player() {
        this.gold = 0;
        this.land = new Land();
        this.deck = new Deck(40, 6);
    }

    public Player(int gold, Land land, Deck deck) {
        this.gold = gold;
        this.land = land;
        this.deck = deck;
    }

    public int getGold() {
        return gold;
    }

    public Land getLand() {
        return land;
    }

    public Deck getDeck() {
        return deck;
    }

    public void addGold(int amount) {
        gold += amount;
    }
}
