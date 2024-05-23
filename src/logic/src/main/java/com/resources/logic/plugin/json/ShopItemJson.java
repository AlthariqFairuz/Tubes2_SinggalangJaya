package com.resources.logic.plugin.json;

import com.google.gson.annotations.SerializedName;

public class ShopItemJson {
    // Attributes
    @SerializedName("item")
    private String item;

    @SerializedName("harga")
    private int harga;

    @SerializedName("jumlah")
    private int jumlah;

    // Constructor
    public ShopItemJson(String item, int harga, int jumlah) {
        this.item = item;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    // Getters and setters (optional)
    public String getItem() {
        return item;
    }

    public int getHarga() {
        return harga;
    }

    public int getJumlah() {
        return jumlah;
    }
}
