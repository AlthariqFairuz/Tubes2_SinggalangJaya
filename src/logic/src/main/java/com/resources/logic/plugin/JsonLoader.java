package com.resources.logic.plugin;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jcp.xml.dsig.internal.dom.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.resources.logic.Card;
import com.resources.logic.CardSlot;
import com.resources.logic.Deck;
import com.resources.logic.Game;
import com.resources.logic.Land;
import com.resources.logic.Player;
import com.resources.logic.Shop;
import com.resources.logic.ShopItem;
import com.resources.logic.lib.Coordinate;
import com.resources.logic.plugin.json.DeckItemJson;
import com.resources.logic.plugin.json.LadangKartuJson;
import com.resources.logic.plugin.json.ShopItemJson;
import com.resources.logic.plugin.json.StateJson;
import com.resources.logic.product.ProductCard;

public class JsonLoader implements Plugin {
    @Override
    public void onLoad() {
        System.out.println("Loading JSON plugin");
    }

    @Override
    public void saveState(Game state, String filePath) {
        System.out.println("Saving game state to " + filePath);

        // Save game state to file

    }

    @Override
    public void loadState(String filePath) {
        // Load game state from file
        System.out.println("Loading game state from " + filePath);

        // Read filepath json as a string
        String jsonString = "";
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // System.out.println(jsonString);

        // Parse to json
        StateJson state;
        try {
            Gson gson = new GsonBuilder().create();
            state = gson.fromJson(jsonString, StateJson.class);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // System.out.println(state);

        // Current Turn
        boolean turnState = state.getCurrentTurn() == 1;
        Game.getInstance().setPlayer1Turn(turnState);

        // Shop state
        ArrayList<ShopItem> shopItemsState = new ArrayList<>();
        for (int i = 0; i < state.getShopItemCount(); i++) {
            ShopItemJson shopItemJson = state.getShopItems().get(i);
            ProductCard proudctCardJson = new ProductCard(shopItemJson.getItem(), null, shopItemJson.getHarga());
            ShopItem shopItem = new ShopItem(
                    proudctCardJson,
                    shopItemJson.getJumlah(),
                    shopItemJson.getHarga());
            shopItemsState.add(shopItem);
        }
        Shop.getInstance().setShopItems(shopItemsState);

        // Player 1 State
        // Gold
        int goldPlayer1State = state.getGuldenPlayer1();

        // Deck
        int deckCountPlayer1State = state.getJumlahDeckPlayer1();
        int activeDeckCountPlayer1State = state.getJumlahDeckAktifPlayer1();
        Deck deckPlayer1State = new Deck(deckCountPlayer1State, activeDeckCountPlayer1State);

        for (int i = 0; i < activeDeckCountPlayer1State; i++) {
            // Get item
            DeckItemJson deckItemJson = state.getDeckAktifPlayer1().get(i);

            // Get coordinate
            Coordinate co = Coordinate.CodeToCoordinate(jsonString);

            // Get card name
            String cardName = deckItemJson.getKartu();

            // Deck
            // TODO: Ini kan udh hardcoded ya, shrsnya gk perlu input dong
            ProductCard newCardProduct = new ProductCard(cardName, "", 0);
            deckPlayer1State.addCardToActiveDeck(newCardProduct);
        }

        // Land state
        CardSlot[][] cardSlotsPlayer1State = new CardSlot[4][5];
        int ladangPlayer1State = state.getJumlahKartuLadangPlayer1();
        for (int i = 0; i < state.getJumlahKartuLadangPlayer1(); i++) {
            // Get item
            LadangKartuJson ladangKartuJson = state.getKartuLadangPlayer1().get(i);

            // Get coordinate
            Coordinate co = Coordinate.CodeToCoordinate(ladangKartuJson.getLokasi());

            // Get card name
            String cardName = ladangKartuJson.getKartu();

            // Get age
            int age = ladangKartuJson.getUmur();

            // Get active count
            int activeCount = ladangKartuJson.getJumlahItemAktif();

            // Get active

            // TODO: TAMBAHIN PENYIMPANAN STATE SLOT LADANG??
        }
        Land landPlayer1State = new Land(4, 5, cardSlotsPlayer1State);

        Player player1 = new Player(goldPlayer1State, landPlayer1State, deckPlayer1State);

        Game.getInstance().setPlayer1(player1);

        // Player 2 state
        // Gold
        int goldPlayer2State = state.getGuldenPlayer2();

        // Deck
        int deckCountPlayer2State = state.getJumlahDeckPlayer2();
        int activeDeckCountPlayer2State = state.getJumlahDeckAktifPlayer2();
        Deck deckPlayer2State = new Deck(deckCountPlayer2State, activeDeckCountPlayer2State);

        for (int i = 0; i < activeDeckCountPlayer2State; i++) {
            // Get item
            DeckItemJson deckItemJson = state.getDeckAktifPlayer2().get(i);

            // Get coordinate
            Coordinate co = Coordinate.CodeToCoordinate(jsonString);

            // Get card name
            String cardName = deckItemJson.getKartu();

            // Deck
            ProductCard newCardProduct = new ProductCard(cardName, "", 0);
            deckPlayer2State.addCardToActiveDeck(newCardProduct);
        }

        // Land state
        CardSlot[][] cardSlotsPlayer2State = new CardSlot[4][5];
        int ladangPlayer2State = state.getJumlahKartuLadangPlayer2();
        for (int i = 0; i < state.getJumlahKartuLadangPlayer2(); i++) {
            // Get item
            LadangKartuJson ladangKartuJson = state.getKartuLadangPlayer2().get(i);

            // Get coordinate
            Coordinate co = Coordinate.CodeToCoordinate(ladangKartuJson.getLokasi());

            // Get card name
            String cardName = ladangKartuJson.getKartu();

            // Get age
            int age = ladangKartuJson.getUmur();

            // Get active count
            int activeCount = ladangKartuJson.getJumlahItemAktif();

            // Get active

            // TODO: TAMBAHIN PENYIMPANAN STATE SLOT LADANG??
        }

        Land landPlayer2State = new Land(4, 5, cardSlotsPlayer2State);

        Player player2 = new Player(goldPlayer2State, landPlayer2State, deckPlayer2State);

        Game.getInstance().setPlayer2(player2);
    }
}