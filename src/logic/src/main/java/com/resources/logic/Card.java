package com.resources.logic;

import java.util.ArrayList;
import java.util.List;

import com.resources.logic.item.ItemCard;
import com.resources.logic.product.ProductCard;

public abstract class Card {
    private String name;
    private String imageLocation;
    protected ProductCard harvestProduct;

    protected int totalAccelerate;
    protected int totalDelay;
    protected boolean protectedFromBear;
    protected boolean trapSet;

    protected int price;

    public Card(String name, String imageLocation, ProductCard harvestProduct, int price) {
        this.name = name;
        this.imageLocation = imageLocation;
        this.harvestProduct = harvestProduct;

        this.totalAccelerate = 0;
        this.totalDelay = 0;
        this.protectedFromBear = false;
        this.trapSet = false;

        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public boolean canHarvestInstantly() {
        return false;
    }

    public boolean canHarvest() {
        return false;
    }

    public ProductCard getHarvestProduct() {
        return harvestProduct;
    }


    public int getTotalActiveItem() {
        // accelerate + delay
        int total = totalAccelerate + totalDelay;

        // bear protection
        if (protectedFromBear) {
            total++;
        }

        // trap
        if (trapSet) {
            total++;
        }

        return total;
    }

    public List<String> getActiveItems() {
        List<String> activeItems = new ArrayList<>();

        for (int i = 0; i < totalAccelerate; i++) {
            activeItems.add("ACCELERATE");
        }

        for (int i = 0; i < totalDelay; i++) {
            activeItems.add("DELAY");
        }

        if (protectedFromBear) {
            activeItems.add("PROTECT");
        }

        if (trapSet) {
            activeItems.add("TRAP");
        }

        return activeItems;
    }

    public void setActiveItems(List<String> activeItems) {
        totalAccelerate = 0;
        totalDelay = 0;
        protectedFromBear = false;
        trapSet = false;

        for (String item : activeItems) {
            switch (item) {
                case "ACCELERATE":
                    totalAccelerate++;
                    break;
                case "DELAY":
                    totalDelay++;
                    break;
                case "PROTECT":
                    protectedFromBear = true;
                    break;
                case "TRAP":
                    trapSet = true;
                    break;
            }
        }
    }

    public int getTotalAccelerate() {
        return totalAccelerate;
    }

    public int getTotalDelay() {
        return totalDelay;
    }

    public boolean isProtectedFromBear() {
        return protectedFromBear;
    }

    public boolean isTrapSet() {
        return trapSet;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return 0;
    }

    public boolean accelerate() {
        return false;
    }

    public boolean delay() {
        return false;
    }

    public boolean protectFromBear() {
        return false;
    }

    public boolean installATrap() {
        return false;
    }

    public boolean canEat(ProductCard food) {
        return false;
    }

    // Asumsi bisa di makan foodnya
    public void eat(ProductCard food) {
    }

    public boolean canReceiveItem(ItemCard item) {
        return false;
    }

    // Asumsi bisa direceive
    public boolean receiveItem(ItemCard card) {
        return false;
    }
}
