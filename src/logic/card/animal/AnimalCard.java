package src.logic.card.animal;

import src.logic.card.Card;

public abstract class AnimalCard extends Card {
    enum AnimalType {CARNIVORE, OMNIVORE, HERBIVORE};
    AnimalType animalType;

    public AnimalCard(String name, String imageLocation, AnimalType animalType) {
        super(name, imageLocation);
        this.animalType = animalType;
    }

}
