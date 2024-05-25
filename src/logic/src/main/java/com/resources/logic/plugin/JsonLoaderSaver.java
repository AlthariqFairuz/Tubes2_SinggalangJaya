package com.resources.logic.plugin;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.resources.logic.Card;
import com.resources.logic.CardAssets;
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

public class JsonLoaderSaver implements Plugin {
    @Override
    public String getPluginType() {
        return "JSON";
    }

    @Override
    public void onLoad() {
        System.out.println("Loading JSON plugin");
    }

    @Override
    public void onSave() {
        System.out.println("Saving JSON plugin");
    }

    @Override
    public void saveState(String filePath) {
        System.out.println("Saving game state to " + filePath);

        // Initialize write state
        StateJson state = new StateJson();

        // Get current turn
        int currentTurn = Game.getInstance().getTotalTurns();
        // DEBUG
        System.out.println(currentTurn);
        state.setCurrentTurn(currentTurn);

        // Get shop items
        List<ShopItem> shopItems = Shop.getInstance().getShopItems();

        // Shop item count
        int shopItemCount = shopItems.size();
        state.setShopItemCount(shopItemCount);

        // Shop items
        List<ShopItemJson> shopItemsJson = new ArrayList<>();
        for (ShopItem shopItem : shopItems) {
            ShopItemJson shopItemJson = new ShopItemJson(
                    shopItem.getItem().getName(),
                    shopItem.getFrequency());
            shopItemsJson.add(shopItemJson);
        }
        state.setShopItems(shopItemsJson);

        // Get player 1 state
        Player player1 = Game.getInstance().getPlayer1();

        // Gold player 1
        int guldenPlayer1 = player1.getGold();
        state.setGuldenPlayer1(guldenPlayer1);

        // Deck count player 1 (total active + inactive)
        int jumlahDeckPlayer1 = player1.getDeck().getNonActiveCardsCount()
                + player1.getDeck().getActiveCardsCount();
        state.setJumlahDeckPlayer1(jumlahDeckPlayer1);

        // Active deck count player 1
        int jumlahDeckAktifPlayer1 = player1.getDeck().getActiveCardsCount();
        state.setJumlahDeckAktifPlayer1(jumlahDeckAktifPlayer1);

        // Deck aktif player 1
        List<DeckItemJson> deckAktifPlayer1 = new ArrayList<>();
        int index = 0;
        for (CardSlot cardSlot : player1.getDeck().getActiveCards()) {
            // If slot exists
            if (cardSlot.hasCard()) {
                // Get coordinate
                Coordinate co = new Coordinate(0, index);
                String code = Coordinate.CoordinateToCode(co);

                // Get card name
                String cardName = cardSlot.getCard().getName();

                // Deck item
                DeckItemJson deckItemJson = new DeckItemJson(code, cardName);

                // Add to deck
                deckAktifPlayer1.add(deckItemJson);
            }
            index++;
        }
        state.setDeckAktifPlayer1(deckAktifPlayer1);
        // DEBUG
        System.out.println("Jumlah kartu aktif player 1: " + deckAktifPlayer1.size());

        // Land state player 1
        int jumlahKartuLadangPlayer1 = 0;
        // Kartu ladang player 1
        List<LadangKartuJson> kartuLadangPlayer1 = new ArrayList<>();
        // Iterate through all card slots
        int landRow = 4;
        int landCol = 5;
        for (int i = 0; i < landRow; i++) {
            for (int j = 0; j < landCol; j++) {
                // Get card slot
                CardSlot cardSlot = player1.getLand().getCardSlots()[i][j];

                // If card exists
                if (cardSlot.hasCard()) {
                    // Increment jumlah kartu ladang player 1
                    jumlahKartuLadangPlayer1++;

                    // Get coordinate
                    Coordinate co = new Coordinate(i, j);
                    String code = Coordinate.CoordinateToCode(co);

                    // Get card name
                    String cardName = cardSlot.getCard().getName();

                    // Get age
                    int age = cardSlot.getCard().getHarvestProduct().getAddedWeight();

                    // Get active state count
                    int activeItemCount = cardSlot.getCard().getHarvestProduct().getTotalActiveItem();

                    // Get active
                    List<String> activeItems = cardSlot.getCard().getHarvestProduct().getActiveItems();

                    // Kartu ladang
                    LadangKartuJson ladangKartuJson = new LadangKartuJson(code, cardName, age, activeItemCount,
                            activeItems);

                    // Add to kartu ladang player 1
                    kartuLadangPlayer1.add(ladangKartuJson);

                    // DEBUG
                    System.out.println(
                            cardSlot.getCard().getName() + " " + code + " " + age + " " + activeItemCount + " "
                                    + activeItems);
                }
            }
        }
        // Set jumlah kartu ladang player 1
        state.setJumlahKartuLadangPlayer1(jumlahKartuLadangPlayer1);
        // Set kartu ladang player 1
        state.setKartuLadangPlayer1(kartuLadangPlayer1);

        // Player 2 state
        Player player2 = Game.getInstance().getPlayer2();

        // Gold player 2
        int guldenPlayer2 = player2.getGold();
        // Set gold player 2
        state.setGuldenPlayer2(guldenPlayer2);

        // Deck count player 2 (total active + inactive)
        int jumlahDeckPlayer2 = player2.getDeck().getNonActiveCardsCount()
                + player2.getDeck().getActiveCardsCount();
        // Set deck count player 2
        state.setJumlahDeckPlayer2(jumlahDeckPlayer2);

        // Active deck count player 2
        int jumlahDeckAktifPlayer2 = player2.getDeck().getActiveCardsCount();
        // Set active deck count player 2
        state.setJumlahDeckAktifPlayer2(jumlahDeckAktifPlayer2);

        // Deck aktif player 2
        List<DeckItemJson> deckAktifPlayer2 = new ArrayList<>();

        // Iterate through all active deck
        index = 0;
        for (CardSlot cardSlot : player2.getDeck().getActiveCards()) {
            // If slot exists
            if (cardSlot.hasCard()) {
                // Get coordinate
                Coordinate co = new Coordinate(0, index);
                String code = Coordinate.CoordinateToCode(co);

                // Get card name
                String cardName = cardSlot.getCard().getName();

                // Deck item
                DeckItemJson deckItemJson = new DeckItemJson(code, cardName);

                // Add to deck
                deckAktifPlayer2.add(deckItemJson);
            }
            index++;
        }
        // Set deck aktif player 2
        state.setDeckAktifPlayer2(deckAktifPlayer2);

        // Land state player 2
        int jumlahKartuLadangPlayer2 = 0;

        // Kartu ladang player 2
        List<LadangKartuJson> kartuLadangPlayer2 = new ArrayList<>();
        Land landPlayer2 = player2.getLand();
        // Iterate through all card slots
        for (int i = 0; i < landPlayer2.getRow(); i++) {
            for (int j = 0; j < landPlayer2.getCol(); j++) {
                // Get card slot
                CardSlot cardSlot = landPlayer2.getCardSlots()[i][j];

                // If card exists
                if (cardSlot.hasCard()) {
                    //
                    jumlahKartuLadangPlayer2++;

                    // Get coordinate
                    Coordinate co = new Coordinate(i, j);
                    String code = Coordinate.CoordinateToCode(co);

                    // Get card name
                    String cardName = cardSlot.getCard().getName();

                    // Get age
                    int age = cardSlot.getCard().getHarvestProduct().getAddedWeight();

                    // Get active item total count
                    int activeItemCount = cardSlot.getCard().getHarvestProduct().getTotalActiveItem();

                    // Get active items list
                    List<String> activeItems = new ArrayList<>();
                    for (String item : cardSlot.getCard().getHarvestProduct().getActiveItems()) {
                        activeItems.add(item);
                    }

                    // Kartu ladang
                    LadangKartuJson ladangKartuJson = new LadangKartuJson(code, cardName, age, activeItemCount,
                            activeItems);
                    kartuLadangPlayer2.add(ladangKartuJson);

                    // DEBUG
                    System.out.println(
                            cardSlot.getCard().getName() + " " + code + " " + age + " " + activeItemCount + " "
                                    + activeItems);
                }
            }
        }
        // Set kartu ladang player 2
        state.setKartuLadangPlayer2(kartuLadangPlayer2);
        // Set jumlah kartu ladang player 2
        state.setJumlahKartuLadangPlayer2(jumlahKartuLadangPlayer2);

        // Convert state to json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(state);

        // Write to file
        try {
            Files.write(Paths.get(filePath), jsonString.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        int turnState = state.getCurrentTurn();
        Game.getInstance().setTotalTurns(turnState);
        // DEBUG
        System.out.println(turnState);
        System.out.println(Game.getInstance().getTotalTurns());

        // Shop item count
        int shopItemCountState = state.getShopItemCount();
        // DEBUG
        System.out.println(shopItemCountState);

        // Shop state
        ArrayList<ShopItem> shopItemsState = new ArrayList<>();
        for (int i = 0; i < shopItemCountState; i++) {
            // Get item
            ShopItemJson shopItemJson = state.getShopItems().get(i);
            Card card = CardAssets.toCard(shopItemJson.getItem());

            ShopItem shopItem = new ShopItem(
                    (ProductCard) card,
                    shopItemJson.getJumlah());

            shopItemsState.add(shopItem);

            // DEBUG
            System.out
                    .println(shopItem.getItem().getName() + " " + shopItem.getFrequency());
        }
        Shop.getInstance().setShopItems(shopItemsState);

        // Player 1 State
        // Gold
        int goldPlayer1State = state.getGuldenPlayer1();
        // DEBUG
        System.out.println(goldPlayer1State);

        // Deck
        int deckCountPlayer1State = state.getJumlahDeckPlayer1();
        int activeDeckCountPlayer1State = state.getJumlahDeckAktifPlayer1();
        Deck deckPlayer1State = new Deck(40, 6);
        // DEBUG
        System.out.println(deckCountPlayer1State + " " + activeDeckCountPlayer1State);

        for (int i = 0; i < activeDeckCountPlayer1State; i++) {
            // Get item
            DeckItemJson deckItemJson = state.getDeckAktifPlayer1().get(i);

            // Get coordinate
            String lokasi = deckItemJson.getLokasi();
            Coordinate co = Coordinate.CodeToCoordinate(lokasi);
            int col = co.getCol();

            // Get card name
            String cardName = deckItemJson.getKartu();
            Card card = CardAssets.toCard(cardName);

            // Deck
            deckPlayer1State.setCardToActiveDeck(card, col);

            // DEBUG
            System.out.println(card.getName() + " " + lokasi);
        }
        // DEBUG
        System.out.println("Jumlah kartu aktif player 1: " + deckPlayer1State.getActiveCardsCount());

        // Generate nonactive cards in player 1 deck
        for (int i = 0; i < deckCountPlayer1State - activeDeckCountPlayer1State; i++) {
            Card randCard = CardAssets.getRandomCard();
            deckPlayer1State.addCardToNonActiveDeck(randCard);
        }
        // DEBUG
        System.out.println("Jumlah kartu nonaktif player 1: " + deckPlayer1State.getNonActiveCardsCount());

        // Land state
        int ladangPlayer1State = state.getJumlahKartuLadangPlayer1();
        Land landPlayer1State = new Land();
        for (int i = 0; i < ladangPlayer1State; i++) {
            // Get item
            LadangKartuJson ladangKartuJson = state.getKartuLadangPlayer1().get(i);

            // Get coordinate
            String lokasi = ladangKartuJson.getLokasi();
            Coordinate co = Coordinate.CodeToCoordinate(lokasi);
            int col = co.getCol();
            int row = co.getRow();

            // Get card name
            String cardName = ladangKartuJson.getKartu();
            Card card = CardAssets.toCard(cardName);

            // Get age
            int age = ladangKartuJson.getUmur();
            // Update umur
            card.getHarvestProduct().setAddedWeight(age);

            // Get active count
            // unused
            // int activeItemCount = ladangKartuJson.getJumlahItemAktif();

            // Get active items
            List<String> activeItems = ladangKartuJson.getItemAktif();
            // Set
            card.getHarvestProduct().setActiveItems(activeItems);

            landPlayer1State.setLandSlot(row, col, card);

            // DEBUG
            System.out.println(card.getName() + " " + lokasi + " " + card.getHarvestProduct().getAddedWeight() + " "
                    + card.getHarvestProduct().getTotalActiveItem() + " " + card.getHarvestProduct().getActiveItems());
        }
        // Set to instance
        Player player1 = new Player(goldPlayer1State, landPlayer1State, deckPlayer1State);
        Game.getInstance().setPlayer1(player1);

        // Player 2 state
        // Gold
        int goldPlayer2State = state.getGuldenPlayer2();
        // DEBUG
        System.out.println(goldPlayer2State);

        // Deck
        int deckCountPlayer2State = state.getJumlahDeckPlayer2();
        // DEBUG
        System.out.println(deckCountPlayer2State);

        int activeDeckCountPlayer2State = state.getJumlahDeckAktifPlayer2();
        // DEBUG
        System.out.println(activeDeckCountPlayer2State);

        Deck deckPlayer2State = new Deck(40, 6);

        // Fill active deck
        for (int i = 0; i < activeDeckCountPlayer2State; i++) {
            // Get item
            DeckItemJson deckItemJson = state.getDeckAktifPlayer2().get(i);

            // Get coordinate
            String lokasi = deckItemJson.getLokasi();
            Coordinate co = Coordinate.CodeToCoordinate(lokasi);
            int col = co.getCol();

            // Get card name
            String cardName = deckItemJson.getKartu();
            Card card = CardAssets.toCard(cardName);

            // Deck
            deckPlayer2State.setCardToActiveDeck(card, col);

            // DEBUG
            System.out.println(card.getName() + " " + lokasi);
        }

        // Generate nonactive cards in player 2 deck
        for (int i = 0; i < deckCountPlayer2State - activeDeckCountPlayer2State; i++) {
            Card randCard = CardAssets.getRandomCard();
            deckPlayer2State.addCardToNonActiveDeck(randCard);
        }
        // DEBUG
        System.out.println("Jumlah kartu nonaktif player 2: " + deckPlayer2State.getNonActiveCardsCount());

        // Land state
        int ladangPlayer2CountState = state.getJumlahKartuLadangPlayer2();
        Land landPlayer2State = new Land();
        for (int i = 0; i < ladangPlayer2CountState; i++) {
            // Get item
            LadangKartuJson ladangKartuJson = state.getKartuLadangPlayer2().get(i);

            // Get coordinate
            String lokasi = ladangKartuJson.getLokasi();
            Coordinate co = Coordinate.CodeToCoordinate(lokasi);
            int col = co.getCol();
            int row = co.getRow();

            // Get card name
            String cardName = ladangKartuJson.getKartu();
            Card card = CardAssets.toCard(cardName);

            // Get age
            int age = ladangKartuJson.getUmur();
            // Update age
            card.getHarvestProduct().setAddedWeight(age);

            // Get active count
            // unused
            // int activeCount = ladangKartuJson.getJumlahItemAktif();

            // Get active items
            List<String> activeItems = ladangKartuJson.getItemAktif();
            // Set
            card.getHarvestProduct().setActiveItems(activeItems);

            // Set to land
            landPlayer2State.setLandSlot(row, col, card);

            // DEBUG
            System.out.println(card.getName() + " " + lokasi + " "
                    + card.getHarvestProduct().getAddedWeight() + " "
                    + card.getHarvestProduct().getTotalActiveItem() + " " + card.getHarvestProduct().getActiveItems());
        }
        // Set to instance
        Player player2 = new Player(goldPlayer2State, landPlayer2State, deckPlayer2State);
        Game.getInstance().setPlayer2(player2);
    }
}