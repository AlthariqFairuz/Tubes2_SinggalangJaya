package com.resources.logic.item;

import com.resources.logic.Card;
import com.resources.logic.product.ProductCard;

public abstract class ItemCard extends Card {

    public ItemCard(String name, String imageLocation) {
        super(name, imageLocation, null, 0);
    }

    @Override
    public boolean canReceiveItem(ItemCard card) {
        return false;
    }

    @Override
    public boolean canEat(ProductCard food) {
        return false;
    }
}
