package com.resources.logic.product;

import com.resources.logic.Card;
import com.resources.logic.item.ItemCard;

public class ProductCard extends Card {
    private int gold;
    private int addedWeight;

    public ProductCard(String name, String imageLocation, int price, int addedWeight) {
        super(name, imageLocation, null, price);
        this.addedWeight = addedWeight;
    }

    @Override
    public boolean canEat(ProductCard food) {
        return false;
    }

    @Override
    public boolean canReceiveItem(ItemCard item) {
        return false;
    }

    public int getAddedWeight() {
        return addedWeight;
    }

    public int getGold() {
        return gold;
    }

    public int getAddedWeight() {
        return addedWeight;
    }
}
