package com.resources.logic;

public class Game {
    private static Game instance;
    private Player player1;
    private Player player2;
    private Shop shop;
    private boolean isPlayer1Turn;
    private int totalTurns;

    private Game() {
        this.player1 = new Player();
        this.player2 = new Player();
        this.shop = Shop.getInstance();
        this.isPlayer1Turn = true;
        this.totalTurns = 0;
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public int getTotalTurns() {
        return totalTurns;
    }

    public Player getCurrentPlayer() {
        if (isPlayer1Turn) {
            return player1;
        } else {
            return player2;
        }
    }

    public Player getOtherPlayer() {
        if (isPlayer1Turn) {
            return player2;
        } else {
            return player1;
        }
    }

    public boolean harvest(int row, int col) {
        CardSlot cardSlot = getCurrentPlayer().getLand().getCardSlots()[row][col];
        if (!cardSlot.hasCard()) {
            System.out.println("Can't harvest from empty slot");
            return false;
        }
        if (!cardSlot.getCard().canHarvest()) {
            System.out.println("Can't harvest from this card");
            return false;
        }
        if (!getCurrentPlayer().getDeck().isAvailable()) {
            System.out.println("Can't harvest from full deck");
            return false;
        }
        Card card = cardSlot.popCard();

        getCurrentPlayer().getDeck().addCardToActiveDeck(card.getHarvestProduct());
        return true;
    }

    
}
