package com.resources.logic;

import com.resources.logic.product.ProductCard;

public class Game {
    private static final Game instance = new Game();
    private Player player1;
    private Player player2;
    private int totalTurns;
    private boolean isPlayer1Turn;
    private Shop shop;

    private Game() {
        this.player1 = new Player();
        this.player2 = new Player();
        this.shop = Shop.getInstance();
        this.isPlayer1Turn = true;
        this.totalTurns = 1;
    }

    public static Game getInstance() {
        return instance;
    }

    // Buat testing di awal.
    public void seed() {
//        getInstance().getPlayer1().getLand().seed();
//        getInstance().getPlayer2().getLand().seed();

        getInstance().getPlayer1().getDeck().seedNonActiveDeck();
        getInstance().getPlayer2().getDeck().seedNonActiveDeck();
    }

    public void next() {
        isPlayer1Turn = !isPlayer1Turn;
        totalTurns++;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getTotalTurns() {
        return totalTurns;
    }

    public boolean isPlayer1Turn() {
        return isPlayer1Turn;
    }

    public Player getCurrentPlayer() {
        if (getIsPlayer1Turn()) {
            return player1;
        } else {
            return player2;
        }
    }

    public Player getOtherPlayer() {
        if (getIsPlayer1Turn()) {
            return player2;
        } else {
            return player1;
        }
    }

    public boolean getIsPlayer1Turn() {
        if (totalTurns % 2 == 1) {
            return false;
        } else {
            return true;
        }
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setTotalTurns(int totalTurns) {
        this.totalTurns = totalTurns;
    }

    public boolean harvest(CardSlot cardSlot) {
        if (!cardSlot.hasCard()) {
            System.out.println("Can't harvest from empty slot");
            return false;
        }
        if (!cardSlot.getCard().canHarvest()) {
            System.out.println("Can't harvest from this card");
            return false;
        }
        if (!getCurrentPlayer().getDeck().isActiveDeckAvailable()) {
            System.out.println("Can't harvest from full deck");
            return false;
        }
        Card card = cardSlot.popCard();

        getCurrentPlayer().getDeck().addCardToActiveDeck(card.getHarvestProduct());
        return true;
    }

    public boolean sell(CardSlot cardSlot) {
        if (!cardSlot.hasCard()) {
            System.out.println("Can't sell from empty slot");
            return false;
        }
        if (!(cardSlot.getCard() instanceof ProductCard)) {
            System.out.println("Can't sell a non product card");
            return false;
        }

        getCurrentPlayer().addGold(cardSlot.getCard().getPrice());
        Shop.getInstance().sellShopItem((ProductCard) cardSlot.popCard());

        return true;
    }

    public boolean buy(String name) {
        if (!getCurrentPlayer().getDeck().isActiveDeckAvailable()) {
            System.out.println("Can't buy because the active deck is full");
            return false;
        }
        boolean result = Shop.getInstance().buyShopItem(name);
        if (!result) {
            System.out.println("Can't buy because there is no card in the shop with the specified name " + name);
            return false;
        }
        getCurrentPlayer().getDeck().addCardToActiveDeck(CardAssets.toCard(name));
        return true;
    }
}
