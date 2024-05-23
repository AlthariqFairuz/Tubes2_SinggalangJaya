package com.resources.logic;

import com.resources.logic.product.ProductCard;

public class ShopItem {
    private ProductCard item;
    private int frequency;
    private int price;

    public ShopItem(ProductCard item, int frequency, int price) {
        this.item = item;
        this.frequency = frequency;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public ProductCard getItem() {
        return item;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setItem(ProductCard item) {
        this.item = item;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}