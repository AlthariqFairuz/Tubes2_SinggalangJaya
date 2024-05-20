package com.resources.logic;

public abstract class Card {
    private String name;
    private String imageLocation;
    protected int totalAccelerate;
    protected int totalDelay;


    public Card(String name, String imageLocation) {
        this.name = name;
        this.imageLocation = imageLocation;
        this.totalAccelerate = 0;
        this.totalDelay = 0;
    }

    public int getTotalAccelerate() {
        return totalAccelerate;
    }

    public int getTotalDelay() {
        return totalDelay;
    }

    public int getNumber() {
        return 0;
    }

    public void accelerate() {
        return;
    }

    public void delay() {
        return;
    }

    

}
