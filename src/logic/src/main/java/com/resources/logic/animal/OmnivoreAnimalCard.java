package com.resources.logic.animal;


import com.resources.logic.product.ProductCard;

public class OmnivoreAnimalCard extends AnimalCard {

    public OmnivoreAnimalCard(String name, String imageLocation, int currentWeight, int harvestWeight, ProductCard harvestProduct) {
        super(name, imageLocation, AnimalType.OMNIVORE, currentWeight, harvestWeight, harvestProduct);
    }
}
