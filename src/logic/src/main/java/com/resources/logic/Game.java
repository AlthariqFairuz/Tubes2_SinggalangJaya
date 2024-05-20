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

}
