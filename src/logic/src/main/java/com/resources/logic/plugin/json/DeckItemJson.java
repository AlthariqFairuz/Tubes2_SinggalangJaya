package com.resources.logic.plugin.json;

import com.google.gson.annotations.SerializedName;

public class DeckItemJson {
    @SerializedName("lokasi")
    private String lokasi;

    @SerializedName("kartu")
    private String kartu;

    // Getters and setters (optional)
    public String getLokasi() {
        return lokasi;
    }

    public String getKartu() {
        return kartu;
    }
}
