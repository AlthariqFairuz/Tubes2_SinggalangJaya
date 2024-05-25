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
    public boolean canHarvestInstantly() {
        return true;
    }

    @Override
    public boolean canHarvest() {
        return totalTurns >= harvestTurns;
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
    public boolean installATrap() {
        if (isTrapSet()) {
            return false;
        } else {
            this.trapSet = true;
            return true;
        }
    }

    @Override
    public boolean canReceiveItem(ItemCard card) {
        if (card.getName().equals("ACCELERATE")) {
//            return accelerate();
            return true;
        } else if (card.getName().equals("DELAY")) {
//            return delay();
            return true;
        } else if (card.getName().equals("PROTECT")) {
//            return protectFromBear();
            return true;
        } else if (card.getName().equals("TRAP")) {
//            return installATrap();
            return true;
        }


        System.out.println("Can't find the specified item card: " + card.getName());
        return false;
    }

    @Override
    public boolean canEat(ProductCard food) {
        return false;
    }


    @Override
    public boolean receiveItem(ItemCard card) {
        if (card.getName().equals("ACCELERATE")) {
            return accelerate();
//            return true;
        } else if (card.getName().equals("DELAY")) {
            return delay();
//            return true;
        } else if (card.getName().equals("PROTECT")) {
            return protectFromBear();
//            return true;
        } else if (card.getName().equals("TRAP")) {
            return installATrap();
//            return true;
        }


        System.out.println("Can't find the specified item card: " + card.getName());
        return false;
    }
}
