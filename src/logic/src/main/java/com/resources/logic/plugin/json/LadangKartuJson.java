package com.resources.logic.plugin.json;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class LadangKartuJson {
    @SerializedName("lokasi")
    private String lokasi;

    @SerializedName("kartu")
    private String kartu;

    @SerializedName("umur")
    private int umur;

    @SerializedName("jumlahItemAktif")
    private int jumlahItemAktif;

    @SerializedName("itemAktif")
    private List<String> itemAktif;

    // Getters and setters (optional)
    public String getLokasi() {
        return lokasi;
    }

    public String getKartu() {
        return kartu;
    }

    public int getUmur() {
        return umur;
    }

    public int getJumlahItemAktif() {
        return jumlahItemAktif;
    }

    public List<String> getItemAktif() {
        return itemAktif;
    }
}
