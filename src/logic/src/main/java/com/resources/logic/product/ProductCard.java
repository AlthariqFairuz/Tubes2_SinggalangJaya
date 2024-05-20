package com.resources.logic.product;

import com.resources.logic.Card;

public class ProductCard extends Card {
    private int gold;
    private int addedWeight;

    public ProductCard(String name, String imageLocation, ProductCard productCard) {
        super(name, imageLocation, productCard);
    }
}
