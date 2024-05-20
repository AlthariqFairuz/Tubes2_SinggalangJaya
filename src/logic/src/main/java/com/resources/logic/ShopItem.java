package com.resources.logic;

import com.resources.logic.product.ProductCard;

public class ShopItem {
    private ProductCard item;
    private int frequency;

    public ShopItem(ProductCard item, int frequency) {
        this.item = item;
        this.frequency = frequency;
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