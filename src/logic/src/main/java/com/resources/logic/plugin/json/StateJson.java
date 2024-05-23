package com.resources.logic.plugin.json;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class StateJson {
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

    // Getters and setters (optional)
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
}