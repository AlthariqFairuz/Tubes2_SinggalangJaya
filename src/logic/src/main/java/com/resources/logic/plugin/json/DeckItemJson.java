package com.resources.logic.plugin.json;

import com.google.gson.annotations.SerializedName;

public class DeckItemJson {
    // Attributes
    @SerializedName("lokasi")
    public String lokasi;

    @SerializedName("kartu")
    public String kartu;

    // Constructor
    public DeckItemJson(String lokasi, String kartu) {
        this.lokasi = lokasi;
        this.kartu = kartu;
    }

    // Getters and setters (optional)
    public String getLokasi() {
        return lokasi;
    }

    public String getKartu() {
        return kartu;
    }
}
