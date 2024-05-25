package com.resources.logic.animal;


import com.resources.logic.product.ProductCard;

public class CarnivoreAnimalCard extends AnimalCard {

    public CarnivoreAnimalCard(String name, String imageLocation, int currentWeight, int harvestWeight, ProductCard harvestProduct) {
        super(name, imageLocation, AnimalType.CARNIVORE, currentWeight, harvestWeight, harvestProduct);
    }

    @Override
    public boolean canEat(ProductCard food) {
        if (food.getName().equals("SIRIP_HIU") ||
                food.getName().equals("SUSU") ||
                food.getName().equals("TELUR") ||
                food.getName().equals("DAGING_KUDA") ||
                food.getName().equals("DAGING_DOMBA") ||
                food.getName().equals("DAGING_BERUANG")) {

            return true;
        }
        return false;
    }

}
