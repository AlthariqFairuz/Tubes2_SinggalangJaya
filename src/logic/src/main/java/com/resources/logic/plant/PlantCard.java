package com.resources.logic.plant;

import com.resources.logic.Card;
import com.resources.logic.product.ProductCard;

public class PlantCard extends Card {
    private int totalTurns;
    private int harvestTurns;

    public PlantCard(String name, String imageLocation, int totalTurns, int harvestTurns, ProductCard harvestProduct) {
        super(name, imageLocation, harvestProduct);
        this.totalTurns = totalTurns;
        this.harvestTurns = harvestTurns;
    }
    
}
