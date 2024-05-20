package com.resources.logic.animal;


import com.resources.logic.product.ProductCard;

public class HerbivoreAnimalCard extends AnimalCard {

    public HerbivoreAnimalCard(String name, String imageLocation, int currentWeight, int harvestWeight, ProductCard harvestProduct) {
        super(name, imageLocation, AnimalType.HERBIVORE, currentWeight, harvestWeight, harvestProduct);
    }
}
