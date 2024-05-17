package src.logic;

import card.animal.*;

public class Main {
    public static void main(String[] args) {
        // Hello world!
        OmnivoreAnimalCard card = new OmnivoreAnimalCard("test", "/usr/etc/test");
        card.printCard();
        System.out.println("Hello world!");
    }
}
