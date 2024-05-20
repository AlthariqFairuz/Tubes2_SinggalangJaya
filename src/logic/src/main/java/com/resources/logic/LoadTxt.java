package com.resources.logic;

import java.util.ArrayList;
import java.util.List;

public abstract class LoadTxt {
    private String content;
    private int currentTurn;
    private int totalShopProducts;
    private List<LoadShopItem> shopProducts;
    private int player1Gold;
    private int player1TotalCards;
    private int player1TotalActiveCards;
    private List<LoadCard> player1ActiveCards;
    private int player1TotalFarmCards;
    private List<LoadCard> player1FarmCards;
    private int player2Gold;
    private int player2TotalCards;
    private int player2TotalActiveCards;
    private List<LoadCard> player2ActiveCards;
    private int player2TotalFarmCards;
    private List<LoadCard> player2FarmCards;

    public LoadTxt(String content) {
        this.content = content;
        parseContent();
    }

    private void parseContent() {
        String[] lines = content.split("\n");
        int lineIndex = 0;
        
        // Game state
        currentTurn = Integer.parseInt(lines[lineIndex++].trim());
        
        // Shop
        totalShopProducts = Integer.parseInt(lines[lineIndex++].trim());
        shopProducts = new ArrayList<>();
        for (int i = 0; i < totalShopProducts; i++) {
            String[] shopItemData = lines[lineIndex++].split(" ");
            String itemName = shopItemData[0];
            int itemPrice = Integer.parseInt(shopItemData[1].trim());
            shopProducts.add(new LoadShopItem(itemName, itemPrice));
        }
        
        // Player 1
        player1Gold = Integer.parseInt(lines[lineIndex++].trim());
        player1TotalCards = Integer.parseInt(lines[lineIndex++].trim());
        player1TotalActiveCards = Integer.parseInt(lines[lineIndex++].trim());
        player1ActiveCards = new ArrayList<>();
        for (int i = 0; i < player1TotalActiveCards; i++) {
            String[] activeCardData = lines[lineIndex++].split(" ");
            String location = activeCardData[0];
            String cardName = activeCardData[1];
            player1ActiveCards.add(new LoadCard(location, cardName));
        }
        player1TotalFarmCards = Integer.parseInt(lines[lineIndex++].trim());
        player1FarmCards = new ArrayList<>();
        for (int i = 0; i < player1TotalFarmCards; i++) {
            String[] cardInLandData = lines[lineIndex++].split(" ");
            String location = cardInLandData[0];
            String cardName = cardInLandData[1];
            int ageOrWeight = Integer.parseInt(cardInLandData[2].trim());
            int totalItems = Integer.parseInt(cardInLandData[3].trim());
            List<String> items = new ArrayList<>();
            for (int j = 0; j < totalItems; j++) {
                items.add(cardInLandData[4 + j]);
            }
            player1FarmCards.add(new LoadCard(location, cardName, ageOrWeight, items));
        }
        
        // Player 2
        player2Gold = Integer.parseInt(lines[lineIndex++].trim());
        player2TotalCards = Integer.parseInt(lines[lineIndex++].trim());
        player2TotalActiveCards = Integer.parseInt(lines[lineIndex++].trim());
        player2ActiveCards = new ArrayList<>();
        for (int i = 0; i < player2TotalActiveCards; i++) {
            String[] activeCardData = lines[lineIndex++].split(" ");
            String location = activeCardData[0];
            String cardName = activeCardData[1];
            player2ActiveCards.add(new LoadCard(location, cardName));
        }
        player2TotalFarmCards = Integer.parseInt(lines[lineIndex++].trim());
        player2FarmCards = new ArrayList<>();
        for (int i = 0; i < player2TotalFarmCards; i++) {
            String[] cardInLandData = lines[lineIndex++].split(" ");
            String location = cardInLandData[0];
            String cardName = cardInLandData[1];
            int ageOrWeight = Integer.parseInt(cardInLandData[2].trim());
            int totalItems = Integer.parseInt(cardInLandData[3].trim());
            List<String> items = new ArrayList<>();
            for (int j = 0; j < totalItems; j++) {
                items.add(cardInLandData[4 + j]);
            }
            player2FarmCards.add(new LoadCard(location, cardName, ageOrWeight, items));
        }
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public int getTotalShopProducts() {
        return totalShopProducts;
    }

    public ArrayList<LoadShopItem> getShopProducts() {
        return new ArrayList<>(shopProducts);
    }

    public int getPlayer1Gold() {
        return player1Gold;
    }

    public int getplayer1TotalCards() {
        return player1TotalCards;
    }

    public int getPlayer1TotalActiveCards() {
        return player1TotalActiveCards;
    }

    public ArrayList<LoadCard> getPlayer1ActiveCards() {
        return new ArrayList<>(player1ActiveCards);
    }

    public int getplayer1TotalFarmCards() {
        return player1TotalFarmCards;
    }

    public ArrayList<LoadCard> getplayer1FarmCards() {
        return new ArrayList<>(player1FarmCards);
    }

    public int getPlayer2Gold() {
        return player2Gold;
    }

    public int getplayer2TotalCards() {
        return player2TotalCards;
    }

    public int getPlayer2TotalActiveCards() {
        return player2TotalActiveCards;
    }

    public ArrayList<LoadCard> getPlayer2ActiveCards() {
        return new ArrayList<>(player2ActiveCards);
    }

    public int getplayer2TotalFarmCards() {
        return player2TotalFarmCards;
    }

    public ArrayList<LoadCard> getplayer2FarmCards() {
        return new ArrayList<>(player2FarmCards);
    }
}

class LoadShopItem {
    private String name;
    private int price;

    public LoadShopItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

class LoadCard {
    private String location;
    private String name;
    private int ageOrWeight;
    private List<String> items;

    public LoadCard(String location, String name) {
        this.location = location;
        this.name = name;
    }

    public LoadCard(String location, String name, int ageOrWeight, List<String> items) {
        this.location = location;
        this.name = name;
        this.ageOrWeight = ageOrWeight;
        this.items = items;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public int getAgeOrWeight() {
        return ageOrWeight;
    }

    public List<String> getItems() {
        return items;
    }
}
