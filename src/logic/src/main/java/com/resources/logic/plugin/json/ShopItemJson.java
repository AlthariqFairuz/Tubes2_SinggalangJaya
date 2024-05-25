package com.resources.logic.plugin.json;

import com.google.gson.annotations.SerializedName;

public class ShopItemJson {
    // Attributes
    @SerializedName("item")
    public String item;

    @SerializedName("jumlah")
    public int jumlah;

    // Constructor
    public ShopItemJson(String item, int jumlah) {
        this.item = item;
        this.jumlah = jumlah;
    }

    // Getters and setters (optional)
    public String getItem() {
        return item;
    }

    public int getJumlah() {
        return jumlah;
    }
}
