package com.resources.logic.animal;

import java.util.ArrayList;

import com.resources.logic.Card;
import com.resources.logic.product.ProductCard;

public abstract class AnimalCard extends Card {
    enum AnimalType {CARNIVORE, OMNIVORE, HERBIVORE};
    
    private AnimalType animalType;

    private int currentWeight; // sebelum dikenakan effects accelerate dan delay
    private int harvestWeight;

    private ArrayList<ProductCard> harvestProducts;


    public AnimalCard(String name, String imageLocation, AnimalType animalType, int currentWeight, int harvestWeight, ArrayList<ProductCard> harvestProducts) {
        super(name, imageLocation);
        this.animalType = animalType;
        this.currentWeight = currentWeight;
        this.harvestWeight = harvestWeight;
        this.harvestProducts = harvestProducts;
    }

    @Override
    public int getNumber() {
        return getCurrentWeight();
    }

    @Override
    public void accelerate() {
        this.totalAccelerate++;
    }

    @Override
    public void delay() {
        this.totalDelay++;
    }


    public int getCurrentWeight() {
        return Math.max(0, currentWeight + 8 * totalAccelerate - 5 * totalDelay);
    }

}
