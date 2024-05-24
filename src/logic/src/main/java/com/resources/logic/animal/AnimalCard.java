package com.resources.logic.animal;

import com.resources.logic.Card;
import com.resources.logic.item.ItemCard;
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
        return currentWeight;
    }

    @Override
    public boolean accelerate() {
        this.totalAccelerate++;
        return true;
    }

    @Override
    public boolean delay() {
        this.totalDelay++;
        return true;
    }

    @Override
    public boolean canHarvest() {
        return getCurrentWeight() >= harvestWeight;
    }

    public int getCurrentWeight() {
        return Math.max(0, currentWeight + 8 * totalAccelerate - 5 * totalDelay);
    }

    @Override
    public boolean protectFromBear() {
        if (protectedFromBear) {
            return false;
        } else {
            this.protectedFromBear = true;
            return true;
        }
    }

    @Override
    public boolean canReceiveItem(ItemCard card) {
        return true;
    }

    @Override
    public void eat(ProductCard food) {
//        System.out.print("Before " + getCurrentWeight());
        currentWeight += food.getAddedWeight();
//        System.out.println(", After " + getCurrentWeight());
    }

    @Override
    public void receiveItem(ItemCard card) {
        if (card.getName().equals("ACCELERATE")) {
            accelerate();
        } else if (card.getName().equals("DELAY")) {
            delay();
        } else if (card.getName().equals("PROTECT")) {
            protectFromBear();
        } else if (card.getName().equals("HERBIVORE")) {

        }
    }
}
