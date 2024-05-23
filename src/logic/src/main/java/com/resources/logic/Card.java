package com.resources.logic;

import com.resources.logic.product.ProductCard;

public abstract class Card {
    private String name;
    private String imageLocation;
    protected ProductCard harvestProduct;
    protected int totalAccelerate;
    protected int totalDelay;
    protected int price;


    public Card(String name, String imageLocation, ProductCard harvestProduct, int price) {
        this.name = name;
        this.imageLocation = imageLocation;
        this.harvestProduct = harvestProduct;
        this.totalAccelerate = 0;
        this.totalDelay = 0;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImageLocation() { return imageLocation; }

    public ProductCard getHarvestProduct() {
        return harvestProduct;
    }

    public int getTotalAccelerate() {
        return totalAccelerate;
    }

    public int getTotalDelay() {
        return totalDelay;
    }

    public int getPrice() {
        return price;
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
