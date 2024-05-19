package com.resources.animal;

import java.util.ArrayList;

import com.resources.product.ProductCard;

public class HerbivoreAnimalCard extends AnimalCard {

    public HerbivoreAnimalCard(String name, String imageLocation, int currentWeight, int harvestWeight, ArrayList<ProductCard> harvestProducts) {
        super(name, imageLocation, AnimalType.HERBIVORE, currentWeight, harvestWeight, harvestProducts);
    }
}
