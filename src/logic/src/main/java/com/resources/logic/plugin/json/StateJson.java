package com.resources.logic.plugin.json;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class StateJson {
    // Attributes
    @SerializedName("currentTurn")
    private int currentTurn;

    @SerializedName("shopItemCount")
    private int shopItemCount;

    @SerializedName("shopItems")
    private List<ShopItemJson> shopItems;

    @SerializedName("guldenPlayer1")
    private int guldenPlayer1;

    @SerializedName("jumlahDeckPlayer1")
    private int jumlahDeckPlayer1;

    @SerializedName("jumlahDeckAktifPlayer1")
    private int jumlahDeckAktifPlayer1;

    @SerializedName("deckAktifPlayer1")
    private List<DeckItemJson> deckAktifPlayer1;

    @SerializedName("jumlahKartuLadangPlayer1")
    private int jumlahKartuLadangPlayer1;

    @SerializedName("kartuLadangPlayer1")
    private List<LadangKartuJson> kartuLadangPlayer1;

    @SerializedName("guldenPlayer2")
    private int guldenPlayer2;

    @SerializedName("jumlahDeckPlayer2")
    private int jumlahDeckPlayer2;

    @SerializedName("jumlahDeckAktifPlayer2")
    private int jumlahDeckAktifPlayer2;

    @SerializedName("deckAktifPlayer2")
    private List<DeckItemJson> deckAktifPlayer2;

    @SerializedName("jumlahKartuLadangPlayer2")
    private int jumlahKartuLadangPlayer2;

    @SerializedName("kartuLadangPlayer2")
    private List<LadangKartuJson> kartuLadangPlayer2;

    // Getters
    public int getCurrentTurn() {
        return currentTurn;
    }

    public int getShopItemCount() {
        return shopItemCount;
    }

    public List<ShopItemJson> getShopItems() {
        return shopItems;
    }

    public int getGuldenPlayer1() {
        return guldenPlayer1;
    }

    public int getJumlahDeckPlayer1() {
        return jumlahDeckPlayer1;
    }

    public int getJumlahDeckAktifPlayer1() {
        return jumlahDeckAktifPlayer1;
    }

    public List<DeckItemJson> getDeckAktifPlayer1() {
        return deckAktifPlayer1;
    }

    public int getJumlahKartuLadangPlayer1() {
        return jumlahKartuLadangPlayer1;
    }

    public List<LadangKartuJson> getKartuLadangPlayer1() {
        return kartuLadangPlayer1;
    }

    public int getGuldenPlayer2() {
        return guldenPlayer2;
    }

    public int getJumlahDeckPlayer2() {
        return jumlahDeckPlayer2;
    }

    public int getJumlahDeckAktifPlayer2() {
        return jumlahDeckAktifPlayer2;
    }

    public List<DeckItemJson> getDeckAktifPlayer2() {
        return deckAktifPlayer2;
    }

    public int getJumlahKartuLadangPlayer2() {
        return jumlahKartuLadangPlayer2;
    }

    public List<LadangKartuJson> getKartuLadangPlayer2() {
        return kartuLadangPlayer2;
    }

    // Setters
    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public void setShopItemCount(int shopItemCount) {
        this.shopItemCount = shopItemCount;
    }

    public void setShopItems(List<ShopItemJson> shopItems) {
        this.shopItems = shopItems;
    }

    public void setGuldenPlayer1(int guldenPlayer1) {
        this.guldenPlayer1 = guldenPlayer1;
    }

    public void setJumlahDeckPlayer1(int jumlahDeckPlayer1) {
        this.jumlahDeckPlayer1 = jumlahDeckPlayer1;
    }

    public void setJumlahDeckAktifPlayer1(int jumlahDeckAktifPlayer1) {
        this.jumlahDeckAktifPlayer1 = jumlahDeckAktifPlayer1;
    }

    public void setDeckAktifPlayer1(List<DeckItemJson> deckAktifPlayer1) {
        this.deckAktifPlayer1 = deckAktifPlayer1;
    }

    public void setJumlahKartuLadangPlayer1(int jumlahKartuLadangPlayer1) {
        this.jumlahKartuLadangPlayer1 = jumlahKartuLadangPlayer1;
    }

    public void setKartuLadangPlayer1(List<LadangKartuJson> kartuLadangPlayer1) {
        this.kartuLadangPlayer1 = kartuLadangPlayer1;
    }

    public void setGuldenPlayer2(int guldenPlayer2) {
        this.guldenPlayer2 = guldenPlayer2;
    }

    public void setJumlahDeckPlayer2(int jumlahDeckPlayer2) {
        this.jumlahDeckPlayer2 = jumlahDeckPlayer2;
    }

    public void setJumlahDeckAktifPlayer2(int jumlahDeckAktifPlayer2) {
        this.jumlahDeckAktifPlayer2 = jumlahDeckAktifPlayer2;
    }

    public void setDeckAktifPlayer2(List<DeckItemJson> deckAktifPlayer2) {
        this.deckAktifPlayer2 = deckAktifPlayer2;
    }

    public void setJumlahKartuLadangPlayer2(int jumlahKartuLadangPlayer2) {
        this.jumlahKartuLadangPlayer2 = jumlahKartuLadangPlayer2;
    }

    public void setKartuLadangPlayer2(List<LadangKartuJson> kartuLadangPlayer2) {
        this.kartuLadangPlayer2 = kartuLadangPlayer2;
    }
}