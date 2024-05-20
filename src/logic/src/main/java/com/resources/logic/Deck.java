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

    public int countEmptySlotsInActiveDeck() {
        int count = 0;
        for (int i = 0; i < totalActiveDeckSize; i++) {
            if (!ActiveCards[i].hasCard()) {
                count++;
            }
        }
        return count;
    }

    public boolean isAvailable() {
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

}
