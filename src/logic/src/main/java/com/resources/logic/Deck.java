package com.resources.logic;

public class Deck {
    private int totalDeckSize; // Non-active + active
    private int totalActiveDeckSize; // Active only
    private CardSlot[] nonActiveCards; // Size is totalDeckSize - totalActiveCards
    private CardSlot[] ActiveCards; // Size is totalActiveCards

    public Deck(int totalDeckSize, int totalActiveDeckCards) {
        this.totalDeckSize = totalDeckSize;
        this.totalActiveDeckSize = totalActiveDeckCards;

        nonActiveCards = new CardSlot[totalDeckSize - totalActiveDeckCards];
        ActiveCards = new CardSlot[totalActiveDeckCards];
    }

    public CardSlot[] getNonActiveCards() {
        return nonActiveCards;
    }

    public CardSlot[] getActiveCards() {
        return ActiveCards;
    }

    public int countEmptySlotsInActiveDeck() {
        int count = 0;
        for (int i = 0; i < totalActiveDeckSize; i++) {
            if (!ActiveCards[i].hasCard()) {
                count++;
            }
        }
        return count;
    }

    public boolean isActiveDeckAvailable() {
        return countEmptySlotsInActiveDeck() > 0;
    }

    public void addCardToActiveDeck(Card card) {
        for (int i = 0; i < totalActiveDeckSize; i++) {
            if (!ActiveCards[i].hasCard()) {
                ActiveCards[i].setCard(card);
                return;
            }
        }
    }

    public void addCardToActiveDeck(Card card, int index) {
        // If index is out of bounds, throw error
        if (index < 0 || index >= totalActiveDeckSize) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        // If already has card, throw error
        if (ActiveCards[index].hasCard()) {
            throw new IllegalArgumentException("Card slot already has card");
        }

        // Set card
        ActiveCards[index].setCard(card);
    }

    public void shuffle() {

        int nonActiveSize = totalDeckSize - totalActiveDeckSize;
        int activeSize = countEmptySlotsInActiveDeck();
        CardSlot[] temp = new CardSlot[6];

        int ptr1 = 0;
        for (int i = 0; i < totalActiveDeckSize - activeSize; i++) {
            int random = (int) (Math.random() * (nonActiveSize));
            while (!nonActiveCards[random].hasCard()) {
                random = (int) (Math.random() * (nonActiveSize));
            }
            if (nonActiveCards[random].hasCard()) {
                temp[ptr1] = nonActiveCards[random];
                nonActiveCards[random].popCard();
                ptr1++;
            }

        }
        int ptr = 0;
        for (int i = 0; i < totalActiveDeckSize; i++) {
            if (!ActiveCards[i].hasCard()) {
                ActiveCards[i].setCard(temp[ptr].getCard());
                ptr++;
            }
        }

    }

}
