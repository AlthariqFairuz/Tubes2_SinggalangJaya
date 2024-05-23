package com.resources.logic;

import java.util.ArrayList;
import java.util.List;

import com.resources.logic.product.ProductCard;

public class Shop {
    private static Shop instance;
    private ArrayList<ShopItem> shopItems;

    private Shop() {
        shopItems = new ArrayList<>();
    }

    public static Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
        }
        return instance;
    }

    public void setShopItems(ArrayList<ShopItem> shopItems) {
        this.shopItems = shopItems;
    }

    public List<ShopItem> getShopItems() {
        return shopItems;
    }

    public boolean buyShopItem(String name) {
        for (ShopItem item : shopItems) {
            if (item.getItem().getName().equals(name)) {
                item.setFrequency(item.getFrequency() - 1);
                if (item.getFrequency() == 0) {
                    shopItems.remove(item);
                }
                return true;
            }
        }
        return false;
    }

    public void sellShopItem(ProductCard card) {
        for (ShopItem item : shopItems) {
            if (item.getItem().getName().equals(card.getName())) {
                item.setFrequency(item.getFrequency() + 1);
                return;
            }
        }
        shopItems.add(new ShopItem(card, 1, card.getPrice()));
    }
}