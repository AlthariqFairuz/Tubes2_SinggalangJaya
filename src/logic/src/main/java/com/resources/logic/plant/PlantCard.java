package com.resources.logic.plant;

import com.resources.logic.Card;
import com.resources.logic.item.ItemCard;
import com.resources.logic.product.ProductCard;

public class PlantCard extends Card {
    private int totalTurns;
    private int harvestTurns;


    public PlantCard(String name, String imageLocation, int totalTurns, int harvestTurns, ProductCard harvestProduct) {
        super(name, imageLocation, harvestProduct, 0);
        this.totalTurns = totalTurns;
        this.harvestTurns = harvestTurns;
    }

    @Override
    public int getNumber() {
        return totalTurns;
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

    public int getCurrentTotalTurns() {
        return Math.max(0, totalTurns + 2 * (totalAccelerate - totalDelay));
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
    public boolean canEat(ProductCard food) {
        return false;
    }
}
