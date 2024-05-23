package com.resources.logic;

import com.resources.logic.product.ProductCard;

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

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
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

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setPlayer1Turn(boolean isPlayer1Turn) {
        this.isPlayer1Turn = isPlayer1Turn;
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
