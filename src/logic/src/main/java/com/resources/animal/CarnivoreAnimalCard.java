package com.resources.animal;

import java.util.ArrayList;

import com.resources.product.ProductCard;

public class CarnivoreAnimalCard extends AnimalCard {

    public CarnivoreAnimalCard(String name, String imageLocation, int currentWeight, int harvestWeight, ArrayList<ProductCard> harvestProducts) {
        super(name, imageLocation, AnimalType.CARNIVORE, currentWeight, harvestWeight, harvestProducts);
    }
}
