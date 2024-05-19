package com.resources.animal;

import java.util.ArrayList;

import com.resources.Card;
import com.resources.product.ProductCard;

public abstract class AnimalCard extends Card {
    enum AnimalType {CARNIVORE, OMNIVORE, HERBIVORE};
    
    private AnimalType animalType;

    private int currentWeight;
    private int harvestWeight;

    private ArrayList<ProductCard> harvestProducts;


    public AnimalCard(String name, String imageLocation, AnimalType animalType, int currentWeight, int harvestWeight, ArrayList<ProductCard> harvestProducts) {
        super(name, imageLocation);
        this.animalType = animalType;
        this.currentWeight = currentWeight;
        this.harvestWeight = harvestWeight;
        this.harvestProducts = harvestProducts;
    }
}
