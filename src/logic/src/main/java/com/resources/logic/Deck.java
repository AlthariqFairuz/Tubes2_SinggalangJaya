package com.resources.logic;

public class Deck {
    private int totalDeckSize; // Non-active + active
    private int totalActiveDeckSize; // Active only
    private CardSlot[] nonActiveCards; // Size is totalDeckSize - totalActiveCards
    private CardSlot[] ActiveCards; // Size is totalActiveCards

    public Deck() {
        this.totalDeckSize = 40;
        this.totalActiveDeckSize = 6;

        nonActiveCards = new CardSlot[totalDeckSize - totalActiveDeckSize];
        ActiveCards = new CardSlot[totalActiveDeckSize];

        for (int i = 0; i < totalDeckSize - totalActiveDeckSize; i++) {
            nonActiveCards[i] = new CardSlot();
        }

        for (int i = 0; i < totalActiveDeckSize; i++) {
            ActiveCards[i] = new CardSlot();
        }
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

    public void addCardToNonActiveDeck(Card card) {
        for (int i = 0; i < totalDeckSize - totalActiveDeckSize; i++) {
            if (!nonActiveCards[i].hasCard()) {
                nonActiveCards[i].setCard(card);
                return;
            }
        }
    }

    public int getActiveCardsCount() {
        int count = 0;
        for (int i = 0; i < totalActiveDeckSize; i++) {
            if (ActiveCards[i].hasCard()) {
                count++;
            }
        }
        return count;
    }

    public int getNonActiveCardsCount() {
        int count = 0;
        for (int i = 0; i < totalDeckSize - totalActiveDeckSize; i++) {
            if (nonActiveCards[i].hasCard()) {
                count++;
            }
        }
        return count;
    }

    public void setCardToActiveDeck(Card card, int index) {
        if (index >= 0 && index < totalActiveDeckSize) {
            // Set card
            ActiveCards[index].setCard(card);
        }
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
