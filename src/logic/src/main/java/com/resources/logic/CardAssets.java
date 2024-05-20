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

    public static Card toCard(String name) {
        // Produk
        if (name.equals("LABU")) {
            return new ProductCard(name, "resources/Produk/pumpkin.png", 500);
        } else if (name.equals("JAGUNG")) {
            return new ProductCard(name, "resources/Produk/corn.png", 150);
        } else if (name.equals("STROBERI")) {
            return new ProductCard(name, "resources/Produk/strawberry.png", 350);
        } else if (name.equals("SUSU")) {
            return new ProductCard(name, "resources/Produk/susu.png", 100);
        } else if (name.equals("TELUR")) {
            return new ProductCard(name, "resources/Produk/telur.png", 50);
        } else if (name.equals("SIRIP_HIU")) {
            return new ProductCard(name, "resources/Produk/shark-fin.png", 500);
        } else if (name.equals("DAGING_KUDA")) {
            return new ProductCard(name, "resources/Produk/Daging Kuda.png", 150);
        } else if (name.equals("DAGING_DOMBA")) {
            return new ProductCard(name, "resources/Produk/Daging Domba.png", 120);
        } else if (name.equals("DAGING_BERUANG")) {
            return new ProductCard(name, "resources/Produk/Daging Beruang.png", 500);
        }

        // Hewan

        else if (name.equals("HIU_DARAT")) {
            return new CarnivoreAnimalCard(name, "resources/Hewan/hiu darat.png", 0, 20,
                    (ProductCard) toCard("SIRIP_HIU"));
        } else if (name.equals("SAPI")) {
            return new HerbivoreAnimalCard(name, "resources/Hewan/cow.png", 0, 10, (ProductCard) toCard("SUSU"));
        } else if (name.equals("DOMBA")) {
            return new HerbivoreAnimalCard(name, "resources/Hewan/sheep.png", 0, 12,
                    (ProductCard) toCard("DAGING_DOMBA"));
        } else if (name.equals("KUDA")) {
            return new HerbivoreAnimalCard(name, "resources/Hewan/horse.png", 0, 14,
                    (ProductCard) toCard("DAGING_KUDA"));
        } else if (name.equals("AYAM")) {
            return new OmnivoreAnimalCard(name, "resources/Hewan/chicken.png", 0, 5, (ProductCard) toCard("telur"));
        } else if (name.equals("BERUANG")) {
            return new OmnivoreAnimalCard(name, "resources/Hewan/bear.png", 0, 25,
                    (ProductCard) toCard("DAGING_BERUANG"));
        }

        // Tanaman

        else if (name.equals("BIJI_LABU")) {
            return new PlantCard(name, "resources/Tanaman/pumpkin seeds.png", 0, 5, (ProductCard) toCard("LABU"));
        } else if (name.equals("BIJI_JAGUNG")) {
            return new PlantCard(name, "resources/Tanaman/corn seeds.png", 0, 3, (ProductCard) toCard("JAGUNG"));
        } else if (name.equals("BIJI_STROBERI")) {
            return new PlantCard(name, "resources/Tanaman/strawberry seeds.png", 0, 4,
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
}
