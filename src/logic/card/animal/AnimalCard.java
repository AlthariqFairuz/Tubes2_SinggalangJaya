package src.logic.card.animal;

public abstract class AnimalCard extends Card {
    enum AnimalType {CARNIVORE, OMNIVORE, HERBIVORE};
    AnimalType animalType;

    public AnimalCard(string name, string imageLocation, AnimalType animalType) {
        super(name, imageLocation);
        this.animalType = animalType;
    }

}
