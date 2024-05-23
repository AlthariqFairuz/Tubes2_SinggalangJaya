package com.resources.logic;

public class CardSlot {
    private Card card;

    public CardSlot() {
        this.card = null;
    }

    public CardSlot(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public Card popCard() {
        Card card = this.card;
        this.card = null;
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean hasCard() {
        return card != null;
    }
}