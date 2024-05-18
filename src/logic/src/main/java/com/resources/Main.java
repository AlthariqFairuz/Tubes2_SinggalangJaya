package com.resources;

import com.resources.animal.OmnivoreAnimalCard;

public class Main {
    public static void main(String[] args) {
        // Hello world!
        OmnivoreAnimalCard card = new OmnivoreAnimalCard("test", "/usr/etc/test");
        card.printCard();
        System.out.println("Hello world!");
    }
}
