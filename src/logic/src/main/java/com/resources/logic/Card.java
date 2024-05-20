package com.resources.logic;

import com.resources.logic.product.ProductCard;

public abstract class Card {
    private String name;
    private String imageLocation;
    protected ProductCard harvestProduct;
    protected int totalAccelerate;
    protected int totalDelay;


    public Card(String name, String imageLocation, ProductCard harvestProduct) {
        this.name = name;
        this.imageLocation = imageLocation;
        this.harvestProduct = harvestProduct;
        this.totalAccelerate = 0;
        this.totalDelay = 0;
    }

    public ProductCard getHarvestProduct() {
        return harvestProduct;
    }

    public int getTotalAccelerate() {
        return totalAccelerate;
    }

    public int getTotalDelay() {
        return totalDelay;
    }

    public int getNumber() {
        return 0;
    }

    public void accelerate() {
        return;
    }

    public void delay() {
        return;
    }

    public boolean canHarvest() {
        return false;
    }

}
