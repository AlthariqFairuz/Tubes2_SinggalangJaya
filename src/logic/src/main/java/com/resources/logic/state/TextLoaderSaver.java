package com.resources.logic.state;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.resources.logic.Card;
import com.resources.logic.CardAssets;
import com.resources.logic.Deck;
import com.resources.logic.Game;
import com.resources.logic.Land;
import com.resources.logic.Player;
import com.resources.logic.Shop;
import com.resources.logic.ShopItem;
import com.resources.logic.lib.Coordinate;
import com.resources.logic.product.ProductCard;

public class TextLoaderSaver implements LoaderSaver {
    @Override
    public void onLoad() {
        System.out.println("Loading text...");
    }

    @Override
    public void onSave() {
        System.out.println("Saving text...");
    }

    @Override
    public void saveState(String filePath) {
        // Save the text to a file

    }

    @Override
    public void loadState(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            // Get Current turn
            line = reader.readLine();
            int currentTurn = Integer.parseInt(line);
            // Update game instance
            Game.getInstance().setTotalTurns(currentTurn);
            // DEBUG
            System.out.println("Current turn: " + currentTurn);

            // Get jumlah item in shop
            line = reader.readLine();
            int jumlahShopItem = Integer.parseInt(line);
            // DEBUG
            System.out.println("Jumlah shop item: " + jumlahShopItem);

            // Get shop items
            ArrayList<ShopItem> shopItemList = new ArrayList<>();
            for (int i = 0; i < jumlahShopItem; i++) {
                // Get shop item
                line = reader.readLine();
                String[] shopItem = line.split(" ");
                String itemName = shopItem[0];
                Card card = CardAssets.toCard(itemName);
                int frequency = Integer.parseInt(shopItem[1]);

                // Add shop item to list
                ShopItem si = new ShopItem((ProductCard) card, frequency);
                shopItemList.add(si);

                // DEBUG
                System.out.println(si.getItem().getName() + " " + si.getFrequency());
            }
            // Update shop instance
            Shop.getInstance().setShopItems(shopItemList);

            // Parse player 1
            // Gulden player 1
            line = reader.readLine();
            int guldenPlayer1 = Integer.parseInt(line);
            // DEBUG
            System.out.println("Gulden player 1: " + guldenPlayer1);

            // Get jumlah deck player 1
            line = reader.readLine();
            int jumlahDeckPlayer1 = Integer.parseInt(line);
            // DEBUG
            System.out.println("Jumlah deck player 1: " + jumlahDeckPlayer1);

            // Get jumlah deck aktif player 1
            line = reader.readLine();
            int jumlahDeckAktifPlayer1 = Integer.parseInt(line);
            // DEBUG
            System.out.println("Jumlah deck aktif player 1: " + jumlahDeckAktifPlayer1);

            // Initialize deck
            Deck deckPlayer1 = new Deck(jumlahDeckPlayer1, jumlahDeckAktifPlayer1);

            // Get deck player 1
            for (int i = 0; i < jumlahDeckPlayer1; i++) {
                // Get deck
                line = reader.readLine();
                String[] deck = line.split(" ");
                String lokasi = deck[0];
                String cardName = deck[1];

                // Coordinate
                Coordinate coordinate = Coordinate.CodeToCoordinate(lokasi);
                int col = coordinate.getCol();

                // Add deck to list
                Card card = CardAssets.toCard(cardName);
                deckPlayer1.addCardToActiveDeck(card, col);

                // DEBUG
                System.out.println(card.getName() + " " + lokasi);
            }

            // Land state
            // Jumlah kartu ladang player 1
            line = reader.readLine();
            int jumlahKartuLadangPlayer1 = Integer.parseInt(line);
            // DEBUG
            System.out.println("Jumlah kartu ladang player 1: " + jumlahKartuLadangPlayer1);

            // Get kartu ladang player 1
            Land landPlayer1 = new Land(4, 5);
            for (int i = 0; i < jumlahKartuLadangPlayer1; i++) {
                // Get kartu ladang
                line = reader.readLine();
                String[] kartuLadang = line.split(" ");

                // Lokasi
                String lokasi = kartuLadang[0];
                Coordinate co = Coordinate.CodeToCoordinate(lokasi);

                // Card
                String cardName = kartuLadang[1];
                Card card = CardAssets.toCard(cardName);

                // Umur/berat
                int umur = Integer.parseInt(kartuLadang[2]);

                // Jumlah item aktif
                int jumlahItemAktif = Integer.parseInt(kartuLadang[3]);

                // List item aktif
                ArrayList<ProductCard> listItemAktif = new ArrayList<>();
                for (int j = 0; j < jumlahItemAktif; j++) {
                    String itemName = kartuLadang[4 + j];
                    ProductCard item = (ProductCard) CardAssets.toCard(itemName);
                    listItemAktif.add(item);
                }

                // TODO: PENYIMPANAN EFEK ITEM LADANG
                landPlayer1.setLandSlot(jumlahDeckAktifPlayer1, jumlahKartuLadangPlayer1, card);

                // DEBUG
                System.out.println(
                        card.getName() + " " + lokasi + " " + umur + " " + jumlahItemAktif + " " + listItemAktif);
            }
            // Connect to game instance
            Player player1 = new Player(guldenPlayer1, landPlayer1, deckPlayer1);
            Game.getInstance().setPlayer1(player1);

            // Parse player 2
            // Gulden player 2
            line = reader.readLine();
            int guldenPlayer2 = Integer.parseInt(line);
            // DEBUG
            System.out.println("Gulden player 2: " + guldenPlayer2);

            // Jumlah deck player 2
            line = reader.readLine();
            int jumlahDeckPlayer2 = Integer.parseInt(line);
            // DEBUG
            System.out.println("Jumlah deck player 2: " + jumlahDeckPlayer2);

            // Jumlah deck aktif player 2
            line = reader.readLine();
            int jumlahDeckAktifPlayer2 = Integer.parseInt(line);
            // DEBUG
            System.out.println("Jumlah deck aktif player 2: " + jumlahDeckAktifPlayer2);

            // Initialize deck
            Deck deckPlayer2 = new Deck(jumlahDeckPlayer2, jumlahDeckAktifPlayer2);

            // Get deck player 2
            for (int i = 0; i < jumlahDeckPlayer2; i++) {
                // Get deck
                line = reader.readLine();
                String[] deck = line.split(" ");
                String lokasi = deck[0];
                String cardName = deck[1];

                // Coordinate
                Coordinate coordinate = Coordinate.CodeToCoordinate(lokasi);
                int col = coordinate.getCol();

                // Add deck to list
                Card card = CardAssets.toCard(cardName);
                deckPlayer2.addCardToActiveDeck(card, col);

                // DEBUG
                System.out.println(card.getName() + " " + lokasi);
            }

            // Land state
            // Jumlah kartu ladang player 2
            line = reader.readLine();
            int jumlahKartuLadangPlayer2 = Integer.parseInt(line);
            // DEBUG
            System.out.println("Jumlah kartu ladang player 2: " + jumlahKartuLadangPlayer2);

            // Get kartu ladang player 2
            Land landPlayer2 = new Land(4, 5);
            for (int i = 0; i < jumlahKartuLadangPlayer2; i++) {
                // Get kartu ladang
                line = reader.readLine();
                String[] kartuLadang = line.split(" ");

                // Lokasi
                String lokasi = kartuLadang[0];
                Coordinate co = Coordinate.CodeToCoordinate(lokasi);

                // Card
                String cardName = kartuLadang[1];
                Card card = CardAssets.toCard(cardName);

                // Umur/berat
                int umur = Integer.parseInt(kartuLadang[2]);

                // Jumlah item aktif
                int jumlahItemAktif = Integer.parseInt(kartuLadang[3]);

                // List item aktif
                ArrayList<ProductCard> listItemAktif = new ArrayList<>();
                for (int j = 0; j < jumlahItemAktif; j++) {
                    String itemName = kartuLadang[4 + j];
                    ProductCard item = (ProductCard) CardAssets.toCard(itemName);
                    listItemAktif.add(item);
                }

                // TODO: PENYIMPANAN EFEK ITEM LADANG
                landPlayer2.setLandSlot(jumlahDeckAktifPlayer1, jumlahKartuLadangPlayer1, card);

                // DEBUG
                System.out.println(
                        card.getName() + " " + lokasi + " " + umur + " " + jumlahItemAktif + " " + listItemAktif);
            }
            // Connect to game instance
            Player player2 = new Player(guldenPlayer2, landPlayer2, deckPlayer2);
            Game.getInstance().setPlayer2(player2);

            // Close reader
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
