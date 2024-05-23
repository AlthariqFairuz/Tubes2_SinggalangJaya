package com.resources.logic.plugin.xml;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.lang.String;

import java.util.List;

public class StateXML {
    @JacksonXmlProperty(localName = "CurrentTurn")
    private int currentTurn;
    @JacksonXmlProperty(localName = "ShopItemCount")
    private int shopItemCount;
    private List<ShopItemXML> shopItems;
    @JacksonXmlProperty(localName = "GuldenPlayer1")
    private int guldenPlayer1;
    @JacksonXmlProperty(localName = "JumlahDeckPlayer1")
    private int jumlahDeckPlayer1;
    @JacksonXmlProperty(localName = "JumlahDeckAktifPlayer1")
    private int jumlahDeckAktifPlayer1;
    private List<DeckXML> deckAktifPlayer1;
    @JacksonXmlProperty(localName = "JumlahKartuLadangPlayer")
    private int jumlahKartuLadangPlayer;
    private List<KartuXML> kartuLadangPlayer1;
    @JacksonXmlProperty(localName = "GuldenPlayer2")
    private int guldenPlayer2;
    @JacksonXmlProperty(localName = "JumlahDeckPlayer2")
    private int jumlahDeckPlayer2;
    @JacksonXmlProperty(localName = "JumlahDeckAktifPlayer2")
    private int jumlahDeckAktifPlayer2;
    private List<DeckXML> deckAktifPlayer2;
    private List<KartuXML> kartuLadangPlayer2;

    public int getCurrentTurn() {
        return currentTurn;
    }

    public int getShopItemCount() {
        return shopItemCount;
    }

    public List<ShopItemXML> getShopItems() {
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

    public List<DeckXML> getDeckAktifPlayer1() {
        return deckAktifPlayer1;
    }

    public int getJumlahKartuLadangPlayer() {
        return jumlahKartuLadangPlayer;
    }

    public List<KartuXML> getKartuLadangPlayer1() {
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

    public List<DeckXML> getDeckAktifPlayer2() {
        return deckAktifPlayer2;
    }

    public List<KartuXML> getKartuLadangPlayer2() {
        return kartuLadangPlayer2;
    }

    
}
