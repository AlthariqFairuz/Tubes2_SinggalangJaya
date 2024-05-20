package com.resources.logic;

public class Shop {
    private static Shop instance;

    private Shop() {
        
    }

    public static Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
        }
        return instance;
    }
}

class ShopItem {
    private Card item;
    private int frequency;

    public ShopItem(Card item, int frequency) {
        this.item = item;
        this.frequency = frequency;
    }

    public Card getItem() {
        return item;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setItem(Card item) {
        this.item = item;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}