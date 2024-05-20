package com.resources.logic.plant;

import com.resources.logic.Card;

public class PlantCard extends Card {
    private int totalTurns;
    private int harvestTurns;

    public PlantCard(String name, String imageLocation, int totalTurns, int harvestTurns) {
        super(name, imageLocation);
        this.totalTurns = totalTurns;
        this.harvestTurns = harvestTurns;
    }
    
}