package com.resources.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.resources.logic.CardAssets.getRandomCard;
import static com.resources.logic.CardAssets.getRandomCard2;

public class Deck {
    private int totalDeckCapacity; // Selalu 40. Ketika start game, nonActiveDeck terisi penuh oleh 40 kartu yang digenerate secara random.
    private int totalActiveDeckCapacity; // Selalu 6. Ketika start game, ActiveDeck masih kosong.
    private CardSlot[] nonActiveCards; // Size is totalDeckCapacity
    private CardSlot[] ActiveCards; // Size is totalActiveDeckCapacity

    public Deck(int totalDeckCapacity, int totalActiveDeckCapacity) {
        this.totalDeckCapacity = totalDeckCapacity;
        this.totalActiveDeckCapacity = totalActiveDeckCapacity;

        nonActiveCards = new CardSlot[totalDeckCapacity];

        for (int i = 0; i < nonActiveCards.length; i++) {
            nonActiveCards[i] = new CardSlot();
        }

        ActiveCards = new CardSlot[totalActiveDeckCapacity];

        for (int i = 0; i < ActiveCards.length; i++) {
            ActiveCards[i] = new CardSlot();
        }
    }

    public CardSlot[] getNonActiveCards() {
        return nonActiveCards;
    }

    public CardSlot[] getActiveCards() {
        return ActiveCards;
    }

    public int getTotalDeckCapacity() {
        return totalDeckCapacity;
    }

    public void seedNonActiveDeck() {
        for (int i = 0; i < totalDeckCapacity; i++) {
            nonActiveCards[i].setCard(getRandomCard2());
        }
    }

    public int countEmptySlotsInActiveDeck() {
        int count = 0;
        for (int i = 0; i < totalActiveDeckCapacity; i++) {
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
        for (int i = 0; i < totalActiveDeckCapacity; i++) {
            if (!ActiveCards[i].hasCard()) {
                ActiveCards[i].setCard(card);
                return;
            }
        }
    }
    public void deleteCardFromInactiveDeck(Card card) {
        for (int i = 0; i < nonActiveCards.length; i++) {
            if (nonActiveCards[i].hasCard() && nonActiveCards[i].getCard() == card) {
                nonActiveCards[i].setCard(null);
                return;
            }
        }
        System.out.println("Fail to delete the card from inactive deck");
    }

    public List<Card> getNrandomCardsFromInactiveDeck(int n) {
        ArrayList<Card> availableCards = new ArrayList<>();
        for (int i = 0; i < nonActiveCards.length; i++) {
            if (nonActiveCards[i].hasCard()) {
                availableCards.add(nonActiveCards[i].getCard());
            }
        }
        if (n > availableCards.size()) {
            System.out.println("Error: n is wrong");
            return null;
        } else {
            Collections.shuffle(availableCards, new Random());
            return availableCards.subList(0, n);
        }
    }

    public int countCardsInNonActiveDeck() {
        int count = 0;
        for (int i = 0; i < nonActiveCards.length; i++) {
            if (nonActiveCards[i].hasCard()) {
                count++;
            }
        }
        return count;
    }

    public void setCardToActiveDeck(Card card, int index) {
        if (index >= 0 && index < totalActiveDeckCapacity) {
            // Set card
            ActiveCards[index].setCard(card);
        }
    }
    public int getNonActiveCardsCount() {
        int count = 0;
        for (CardSlot c : nonActiveCards) {
            if (c.hasCard()) {
                count++;
            }
        }
        return count;
    }
    public int getActiveCardsCount() {
        int count = 0;
        for (CardSlot c : ActiveCards) {
            if (c.hasCard()) {
                count++;
            }
        }
        return count;
    }

    public void addCardToNonActiveDeck(Card randCard) {
        for (int i = 0; i < nonActiveCards.length; i++) {
            if (!nonActiveCards[i].hasCard()) {
                nonActiveCards[i].setCard(randCard);
                return;
            }
        }
    }

//    public void shuffle() {
//
//        int nonActiveSize = totalDeckCapacity;
//        int activeSize = countEmptySlotsInActiveDeck();
//        CardSlot[] temp = new CardSlot[6];
//
//        int ptr1 = 0;
//        for (int i = 0; i < totalActiveDeckSize - activeSize; i++) {
//            int random = (int) (Math.random() * (nonActiveSize));
//            while (!nonActiveCards[random].hasCard()) {
//                random = (int) (Math.random() * (nonActiveSize));
//            }
//            if (nonActiveCards[random].hasCard()) {
//                temp[ptr1] = nonActiveCards[random];
//                nonActiveCards[random].popCard();
//                ptr1++;
//            }
//
//        }
//        int ptr = 0;
//        for (int i = 0; i < totalActiveDeckSize; i++) {
//            if (!ActiveCards[i].hasCard()) {
//                ActiveCards[i].setCard(temp[ptr].getCard());
//                ptr++;
//            }
//        }
//    }

}
