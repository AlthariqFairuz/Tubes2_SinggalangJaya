package com.resources.logic;

import com.resources.logic.animal.CarnivoreAnimalCard;
import com.resources.logic.animal.HerbivoreAnimalCard;
import com.resources.logic.animal.OmnivoreAnimalCard;
import com.resources.logic.item.Accelerate;
import com.resources.logic.item.Delay;
import com.resources.logic.item.Destroy;
import com.resources.logic.item.InstantHarvest;
import com.resources.logic.item.Protect;
import com.resources.logic.item.Trap;
import com.resources.logic.plant.PlantCard;
import com.resources.logic.product.ProductCard;


public class CardAssets {
    private static final String[] cardNames = new String[]{
            "LABU",
            "JAGUNG",
            "STROBERI",
            "SUSU",
            "TELUR",
            "SIRIP_HIU",
            "DAGING_KUDA",
            "DAGING_DOMBA",
            "DAGING_BERUANG",

            "HIU_DARAT",
            "SIRIP_HIU",
            "SAPI",
            "DOMBA",
            "KUDA",
            "AYAM",
            "BERUANG",

            "BIJI_LABU",
            "BIJI_JAGUNG",
            "BIJI_STROBERI",

            "ACCELERATE",
            "DELAY",
            "INSTANT_HARVEST",
            "DESTROY",
            "PROTECT",
            "TRAP"
    };

    public static Card getRandomCard() {
        // pick a random string from cardNames
        String name = cardNames[(int) (Math.random() * cardNames.length)];

        return toCard(name);
    }

    public static Card toCard(String name) {
        // Produk
        if (name.equals("LABU")) {
            return new ProductCard(name, "Produk/pumpkin.png", 500, 10);
        } else if (name.equals("JAGUNG")) {
            return new ProductCard(name, "Produk/corn.png", 150, 3);
        } else if (name.equals("STROBERI")) {
            return new ProductCard(name, "Produk/strawberry.png", 350, 5);
        } else if (name.equals("SUSU")) {
            return new ProductCard(name, "Produk/susu.png", 100, 4);
        } else if (name.equals("TELUR")) {
            return new ProductCard(name, "Produk/telur.png", 50, 2);
        } else if (name.equals("SIRIP_HIU")) {
            return new ProductCard(name, "Produk/shark-fin.png", 500, 12);
        } else if (name.equals("DAGING_KUDA")) {
            return new ProductCard(name, "Produk/Daging Kuda.png", 150, 8);
        } else if (name.equals("DAGING_DOMBA")) {
            return new ProductCard(name, "Produk/Daging Domba.png", 120, 6);
        } else if (name.equals("DAGING_BERUANG")) {
            return new ProductCard(name, "Produk/Daging Beruang.png", 500, 12);
        }

        // Hewan

        else if (name.equals("HIU_DARAT")) {
            return new CarnivoreAnimalCard(name, "Hewan/hiu darat.png", 0, 20,
                    (ProductCard) toCard("SIRIP_HIU"));
        } else if (name.equals("SAPI")) {
            return new HerbivoreAnimalCard(name, "Hewan/cow.png", 0, 10, (ProductCard) toCard("SUSU"));
        } else if (name.equals("DOMBA")) {
            return new HerbivoreAnimalCard(name, "Hewan/sheep.png", 0, 12,
                    (ProductCard) toCard("DAGING_DOMBA"));
        } else if (name.equals("KUDA")) {
            return new HerbivoreAnimalCard(name, "Hewan/horse.png", 0, 14,
                    (ProductCard) toCard("DAGING_KUDA"));
        } else if (name.equals("AYAM")) {
            return new OmnivoreAnimalCard(name, "Hewan/chicken.png", 0, 5, (ProductCard) toCard("TELUR"));
        } else if (name.equals("BERUANG")) {
            return new OmnivoreAnimalCard(name, "Hewan/bear.png", 0, 25,
                    (ProductCard) toCard("DAGING_BERUANG"));
        }

        // Tanaman

        else if (name.equals("BIJI_LABU")) {
            return new PlantCard(name, "Tanaman/pumpkin seeds.png", 0, 5, (ProductCard) toCard("LABU"));
        } else if (name.equals("BIJI_JAGUNG")) {
            return new PlantCard(name, "Tanaman/corn seeds.png", 0, 3, (ProductCard) toCard("JAGUNG"));
        } else if (name.equals("BIJI_STROBERI")) {
            return new PlantCard(name, "Tanaman/strawberry seeds.png", 0, 4,
                    (ProductCard) toCard("STROBERI"));
        }

        // Item

        else if (name.equals("ACCELERATE")) {
            return new Accelerate();
        } else if (name.equals("DELAY")) {
            return new Delay();
        } else if (name.equals("INSTANT_HARVEST")) {
            return new InstantHarvest();
        } else if (name.equals("DESTROY")) {
            return new Destroy();
        } else if (name.equals("PROTECT")) {
            return new Protect();
        } else if (name.equals("TRAP")) {
            return new Trap();
        }

        // Error handling

        else {
            System.out.println("Can't find card: " + name);
            return null;
        }
    }

    public static Card getRandomCard2() {
        // Generates a random card
        // 30% Animal 0
        // 30% Plant 1
        // 20% Product 2
        // 20% Item 3
        double random = Math.random();

        String[] animalCards = { "HIU_DARAT", "SAPI", "DOMBA", "KUDA", "AYAM", "BERUANG" };

        String[] plantCards = { "BIJI_LABU", "BIJI_JAGUNG", "BIJI_STROBERI" };

        String[] productCards = { "LABU", "JAGUNG", "STROBERI", "SUSU", "TELUR", "SIRIP_HIU", "DAGING_KUDA",
                "DAGING_DOMBA", "DAGING_BERUANG" };

        String[] itemCards = { "ACCELERATE", "DELAY", "INSTANT_HARVEST", "DESTROY", "PROTECT", "TRAP" };

        if (random < 0.3) {
            // Random animal
            int randomIdx = (int) (Math.random() * animalCards.length);
            return toCard(animalCards[randomIdx]);
        } else if (random < 0.6) {
            // Random plant
            int randomIdx = (int) (Math.random() * plantCards.length);
            return toCard(plantCards[randomIdx]);
        } else if (random < 0.8) {
            // Random product
            int randomIdx = (int) (Math.random() * productCards.length);
            return toCard(productCards[randomIdx]);
        } else {
            // Random item
            int randomIdx = (int) (Math.random() * itemCards.length);
            return toCard(itemCards[randomIdx]);
        }

    }
}
