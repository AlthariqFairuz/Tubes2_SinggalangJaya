//package com.resources.logic;
//
//import java.util.ArrayList;
//
//
//public abstract class LoadGame {
//
//    private String content;
//
//    public LoadGame(String content) {
//        this.content = content;
//        // ...
//    }
//
//    // game state
//
//    public int getCurrentTurn() {
//        return 0;
//    }
//
//    public int getTotalShopProducts() {
//        return 0;
//    }
//
//    public ArrayList<LoadShopItem> getShopProducts() {
//        return null;
//    }
//
//    // player 1
//
//    public int getPlayer1Gold() {
//        return 0;
//    }
//
//    public int getPlayer1TotalInactiveCards() {
//        return 0;
//    }
//
//    public int getPlayer1TotalActiveCards() {
//        return 0;
//    }
//
//    public ArrayList<LoadCard> getPlayer1ActiveCards() {
//        return null;
//    }
//
//    public int getPlayer1TotalCardsInLand() {
//        return 0;
//    }
//
//    public ArrayList<LoadCard> getPlayer1CardsInLand() {
//        return null;
//    }
//
//    // player 2
//
//    public int getPlayer2Gold() {
//        return 0;
//    }
//
//    public int getPlayer2TotalInactiveCards() {
//        return 0;
//    }
//
//    public int getPlayer2TotalActiveCards() {
//        return 0;
//    }
//
//    public ArrayList<LoadCard> getPlayer2ActiveCards() {
//        return null;
//    }
//
//    public int getPlayer2TotalCardsInLand() {
//        return 0;
//    }
//
//    public ArrayList<LoadCard> getPlayer2CardsInLand() {
//        return null;
//    }
//}
//
//class LoadCard {
//    private int row;
//    private int col;
//    private String name;
//    private int number; // weight kalau Animal, turns kalau Plant
//    private int totalItems;
//    private ArrayList<String> items; // Seperti ACCELERATE, DELAY, PROTECT, dll
//
//    public LoadCard(int row, int col, String name, int number, int totalItems, ArrayList<String> items) {
//        this.row = row;
//        this.col = col;
//        this.name = name;
//        this.number = number;
//        this.totalItems = totalItems;
//        this.items = items;
//    }
//
//    public int getRow() {
//        return row;
//    }
//
//    public int getCol() {
//        return col;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getNumber() {
//        return number;
//    }
//
//    public int getTotalItems() {
//        return totalItems;
//    }
//
//    public ArrayList<String> getItems() {
//        return items;
//    }
//
//}
//
//
//class LoadShopItem {
//    private String name;
//    private int frequency;
//
//    public LoadShopItem(String name, int frequency) {
//        this.name = name;
//        this.frequency = frequency;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getFrequency() {
//        return frequency;
//    }
//}