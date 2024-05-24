package com.resources.logic.plugin.json;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class LadangKartuJson {
    // Attributes
    @SerializedName("lokasi")
    public String lokasi;

    @SerializedName("kartu")
    public String kartu;

    @SerializedName("umur")
    public int umur;

    @SerializedName("jumlahItemAktif")
    public int jumlahItemAktif;

    @SerializedName("itemAktif")
    public List<String> itemAktif;

    // Constructor
    public LadangKartuJson(String lokasi, String kartu, int umur, int jumlahItemAktif, List<String> itemAktif) {
        this.lokasi = lokasi;
        this.kartu = kartu;
        this.umur = umur;
        this.jumlahItemAktif = jumlahItemAktif;
        this.itemAktif = itemAktif;
    }

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
