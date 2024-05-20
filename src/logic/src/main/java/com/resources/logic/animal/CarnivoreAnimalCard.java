package com.resources.logic.animal;


import com.resources.logic.product.ProductCard;

public class CarnivoreAnimalCard extends AnimalCard {

    public CarnivoreAnimalCard(String name, String imageLocation, int currentWeight, int harvestWeight, ProductCard harvestProduct) {
        super(name, imageLocation, AnimalType.CARNIVORE, currentWeight, harvestWeight, harvestProduct);
    }
}
