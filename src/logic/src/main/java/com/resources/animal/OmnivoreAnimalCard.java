package com.resources.animal;

import java.util.ArrayList;

import com.resources.product.ProductCard;

public class OmnivoreAnimalCard extends AnimalCard {

    public OmnivoreAnimalCard(String name, String imageLocation, int currentWeight, int harvestWeight, ArrayList<ProductCard> harvestProducts) {
        super(name, imageLocation, AnimalType.OMNIVORE, currentWeight, harvestWeight, harvestProducts);
    }
}
