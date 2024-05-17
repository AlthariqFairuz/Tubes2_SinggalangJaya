package src.logic.card.animal;

import Animal.AnimalCard;

public class CarnivoreAnimalCard extends AnimalCard {

    public CarnivoreAnimalCard(String name, String imageLocation) {
        super(name, imageLocation, AnimalType.CARNIVORE);
    }
}
