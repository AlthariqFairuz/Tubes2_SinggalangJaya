package com.resources.logic.animal;

import com.resources.logic.Card;
import com.resources.logic.product.ProductCard;

public abstract class AnimalCard extends Card {
    enum AnimalType {CARNIVORE, OMNIVORE, HERBIVORE};
    
    private AnimalType animalType;

    private int currentWeight; // sebelum dikenakan effects accelerate dan delay
    private int harvestWeight;



    public AnimalCard(String name, String imageLocation, AnimalType animalType, int currentWeight, int harvestWeight, ProductCard harvestProduct) {
        super(name, imageLocation, harvestProduct, 0);
        this.animalType = animalType;
        this.currentWeight = currentWeight;
        this.harvestWeight = harvestWeight;
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

    @Override
    public boolean canHarvest() {
        return getCurrentWeight() >= harvestWeight;
    }

    public int getCurrentWeight() {
        return Math.max(0, currentWeight + 8 * totalAccelerate - 5 * totalDelay);
    }

}
