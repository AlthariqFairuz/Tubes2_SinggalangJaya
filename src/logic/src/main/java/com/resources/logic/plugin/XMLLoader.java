package com.resources.logic.plugin;

import java.lang.System;
import java.util.ArrayList;

import org.w3c.dom.NodeList;

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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLLoader implements Plugin {
    public void onLoad() {
        System.out.println("XMLParser loaded");
    }

    public void saveState(String filePath) {
        System.out.println("XMLParser saveState");
    }

    public void loadState(String filePath) {
        try {
            // Load the XML file
            File inputFile = new File(filePath);

            // Create a DocumentBuilder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // // Print root element
            // System.out.println("Root element: " +
            // doc.getDocumentElement().getNodeName());

            // Parsing currentTurn
            Node currentTurnNode = doc.getElementsByTagName("currentTurn").item(0);
            boolean isPlayer1Turn = currentTurnNode.getTextContent().equals("1");
            Game.getInstance().setPlayer1Turn(isPlayer1Turn);
            // System.out.println("Current Turn: " +
            // currentTurnNode.item(0).getTextContent());

            // Parsing shop item count
            Node shopItemCountNode = doc.getElementsByTagName("shopItemCount").item(0);
            int shopItemCount = Integer.parseInt(shopItemCountNode.getTextContent());

            // Parsing shop items
            ArrayList<ShopItem> shopItemsState = new ArrayList<>();
            Node shopItemsNode = doc.getElementsByTagName("shopItems").item(0);
            NodeList shopItemsList = shopItemsNode.getChildNodes();
            for (int i = 0; i < shopItemCount; i++) {
                Node node = shopItemsList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Get name
                    String name = element.getElementsByTagName("name").item(0).getTextContent();

                    // Get card
                    Card card = CardAssets.toCard(name);

                    // Get price
                    int price = Integer.parseInt(element.getElementsByTagName("harga").item(0).getTextContent());

                    // Get frequency
                    int frequency = Integer.parseInt(element.getElementsByTagName("jumlah").item(0).getTextContent());

                    // Create obj
                    ShopItem shopItem = new ShopItem(card.getHarvestProduct(), frequency, price);

                    // Add to shop
                    shopItemsState.add(shopItem);
                    // System.out.println("Item Name: " +
                    // element.getElementsByTagName("name").item(0).getTextContent());
                    // System.out.println("Harga: " +
                    // element.getElementsByTagName("harga").item(0).getTextContent());
                    // System.out.println("Jumlah: " +
                    // element.getElementsByTagName("jumlah").item(0).getTextContent());
                }
            }
            Shop.getInstance().setShopItems(shopItemsState);

            // Parsing player1 details
            Node player1Node = doc.getElementsByTagName("player1").item(0);
            if (player1Node.getNodeType() == Node.ELEMENT_NODE) {
                Element player1Element = (Element) player1Node;
                // System.out.println(
                // "\nPlayer 1 Gulden: " +
                // player1Element.getElementsByTagName("gulden").item(0).getTextContent());
                // System.out.println("Player 1 Jumlah Deck: "
                // +
                // player1Element.getElementsByTagName("jumlahDeck").item(0).getTextContent());

                // Parsing gulden
                int guldenPlayer1 = Integer
                        .parseInt(player1Element.getElementsByTagName("gulden").item(0).getTextContent());

                // Parse jumlah dek total
                int jumlahDeckPlayer1 = Integer
                        .parseInt(player1Element.getElementsByTagName("jumlahDeck").item(0).getTextContent());

                // Parse jumlah dek aktif
                int jumlahDeckAktifPlayer1 = Integer
                        .parseInt(player1Element.getElementsByTagName("jumlahDeckAktif").item(0).getTextContent());

                // Parse deck aktif
                Node deckAktifPlayer1Node = player1Element.getElementsByTagName("deckAktif").item(0);
                NodeList deckAktifList = deckAktifPlayer1Node.getChildNodes();
                // Parsing deckAktif for player1
                // System.out.println("\nPlayer 1 Deck Aktif:");
                Deck deckPlayer1State = new Deck(jumlahDeckPlayer1, jumlahDeckAktifPlayer1);
                for (int i = 0; i < deckAktifList.getLength(); i++) {
                    Node node = deckAktifList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;

                        // Lokasi
                        Node lokasiNode = element.getElementsByTagName("lokasi").item(0);
                        String lokasi = lokasiNode.getTextContent();
                        Coordinate co = Coordinate.CodeToCoordinate(lokasi);
                        int col = co.getCol();

                        // Kartu
                        Node kartuNode = element.getElementsByTagName("kartu").item(0);
                        String kartu = kartuNode.getTextContent();

                        // Get card
                        Card card = CardAssets.toCard(kartu);

                        // Deck
                        deckPlayer1State.addCardToActiveDeck(card, col);

                        // System.out
                        // .println("Lokasi: " +
                        // element.getElementsByTagName("lokasi").item(0).getTextContent());
                        // System.out.println("Kartu: " +
                        // element.getElementsByTagName("kartu").item(0).getTextContent());
                    }
                }

                // Parsing kartuLadang for player1
                // Jumlah kartu ladang
                CardSlot[][] cardSlotsPlayer1State = new CardSlot[4][5];
                Node jumlahKartuLadangPlayer1Node = player1Element.getElementsByTagName("jumlahKartuLadang").item(0);
                int jumlahKartuLadangPlayer1 = Integer.parseInt(jumlahKartuLadangPlayer1Node.getTextContent());

                // Parsing kartu ladang
                NodeList kartuLadangList = player1Element.getElementsByTagName("kartuLadang").item(0).getChildNodes();
                for (int i = 0; i < jumlahKartuLadangPlayer1; i++) {
                    Node node = kartuLadangList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        // System.out
                        // .println("Lokasi: " +
                        // element.getElementsByTagName("lokasi").item(0).getTextContent());
                        // System.out.println("Kartu: " +
                        // element.getElementsByTagName("kartu").item(0).getTextContent());
                        // System.out.println("Umur: " +
                        // element.getElementsByTagName("umur").item(0).getTextContent());
                        // System.out.println("Jumlah Item Aktif: "
                        // + element.getElementsByTagName("jumlahItemAktif").item(0).getTextContent());

                        // Lokasi
                        Node lokasiNode = element.getElementsByTagName("lokasi").item(0);
                        String lokasi = lokasiNode.getTextContent();
                        Coordinate co = Coordinate.CodeToCoordinate(lokasi);
                        int col = co.getCol();
                        int row = co.getRow();

                        // Kartu
                        Node kartuNode = element.getElementsByTagName("kartu").item(0);
                        String kartu = kartuNode.getTextContent();
                        Card card = CardAssets.toCard(kartu);

                        // Umur
                        Node umurNode = element.getElementsByTagName("umur").item(0);
                        int umur = Integer.parseInt(umurNode.getTextContent());

                        // Jumlah item aktif
                        Node jumlahItemAktifNode = element.getElementsByTagName("jumlahItemAktif").item(0);
                        int jumlahItemAktif = Integer.parseInt(jumlahItemAktifNode.getTextContent());

                        // Item aktif
                        Node itemAktifNode = element.getElementsByTagName("itemAktif").item(0);
                        NodeList itemAktifList = itemAktifNode.getChildNodes();
                        // TODO: TAMBAHIN TEMPAT PENYIMPANAN STATE ITEM DIMANA??
                        // System.out.print("Item Aktif: ");
                        for (int j = 0; j < itemAktifList.getLength(); j++) {
                            System.out.print(itemAktifList.item(j).getTextContent() + " ");
                        }
                        // System.out.println();

                        cardSlotsPlayer1State[row][col] = new CardSlot(card);
                    }
                }
                Land landPlayer1State = new Land(4, 5, cardSlotsPlayer1State);

                Player player1 = new Player(guldenPlayer1, landPlayer1State, deckPlayer1State);

                Game.getInstance().setPlayer1(player1);
            }

            // Player 2 states
            Node player2Node = doc.getElementsByTagName("player2").item(0);

            // REPEAT AS BEFORE LIKE PLAYER 1

            if (player2Node.getNodeType() == Node.ELEMENT_NODE) {
                Element player2Element = (Element) player2Node;
                // System.out.println(
                // "\nPlayer 2 Gulden: " +
                // player2Element.getElementsByTagName("gulden").item(0).getTextContent());
                // System.out.println("Player 2 Jumlah Deck: "
                // +
                // player2Element.getElementsByTagName("jumlahDeck").item(0).getTextContent());

                // Parsing gulden
                int guldenPlayer2 = Integer
                        .parseInt(player2Element.getElementsByTagName("gulden").item(0).getTextContent());

                // Parse jumlah dek total
                int jumlahDeckPlayer2 = Integer
                        .parseInt(player2Element.getElementsByTagName("jumlahDeck").item(0).getTextContent());

                // Parse jumlah dek aktif
                int jumlahDeckAktifPlayer2 = Integer
                        .parseInt(player2Element.getElementsByTagName("jumlahDeckAktif").item(0).getTextContent());

                // Parse deck aktif
                Node deckAktifPlayer2Node = player2Element.getElementsByTagName("deckAktif").item(0);
                NodeList deckAktifList = deckAktifPlayer2Node.getChildNodes();
                // Parsing deckAktif for player1
                // System.out.println("\nPlayer 2 Deck Aktif:");
                Deck deckPlayer2State = new Deck(jumlahDeckPlayer2, jumlahDeckAktifPlayer2);
                for (int i = 0; i < deckAktifList.getLength(); i++) {
                    Node node = deckAktifList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;

                        // Lokasi
                        Node lokasiNode = element.getElementsByTagName("lokasi").item(0);
                        String lokasi = lokasiNode.getTextContent();
                        Coordinate co = Coordinate.CodeToCoordinate(lokasi);
                        int col = co.getCol();

                        // Kartu
                        Node kartuNode = element.getElementsByTagName("kartu").item(0);
                        String kartu = kartuNode.getTextContent();

                        // Get card
                        Card card = CardAssets.toCard(kartu);

                        // Deck
                        deckPlayer2State.addCardToActiveDeck(card, col);

                        // System.out
                        // .println("Lokasi: " +
                        // element.getElementsByTagName("lokasi").item(0
                    }
                }

                // Parsing kartuLadang for player2

                // Jumlah kartu ladang
                CardSlot[][] cardSlotsPlayer2State = new CardSlot[4][5];
                Node jumlahKartuLadangPlayer2Node = player2Element.getElementsByTagName("jumlahKartuLadang").item(0);
                int jumlahKartuLadangPlayer2 = Integer.parseInt(jumlahKartuLadangPlayer2Node.getTextContent());

                // Parsing kartu ladang
                NodeList kartuLadangList = player2Element.getElementsByTagName("kartuLadang").item(0).getChildNodes();

                for (int i = 0; i < jumlahKartuLadangPlayer2; i++) {
                    Node node = kartuLadangList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        // System.out
                        // .println("Lokasi: " +
                        // element.getElementsByTagName("lokasi").item(0).getTextContent());
                        // System.out.println("Kartu: " +
                        // element.getElementsByTagName("kartu").item(0).getTextContent());
                        // System.out.println("Umur: " +
                        // element.getElementsByTagName("umur").item(0).getTextContent());
                        // System.out.println("Jumlah Item Aktif: "
                        // + element.getElementsByTagName("jumlahItemAktif").item(0).getTextContent());

                        // Lokasi
                        Node lokasiNode = element.getElementsByTagName("lokasi").item(0);
                        String lokasi = lokasiNode.getTextContent();
                        Coordinate co = Coordinate.CodeToCoordinate(lokasi);
                        int col = co.getCol();
                        int row = co.getRow();

                        // Kartu
                        Node kartuNode = element.getElementsByTagName("kartu").item(0);
                        String kartu = kartuNode.getTextContent();
                        Card card = CardAssets.toCard(kartu);

                        // Umur
                        Node umurNode = element.getElementsByTagName("umur").item(0);
                        int umur = Integer.parseInt(umurNode.getTextContent());

                        // Jumlah item aktif
                        Node jumlahItemAktifNode = element.getElementsByTagName("jumlahItemAktif").item(0);
                        int jumlahItemAktif = Integer.parseInt(jumlahItemAktifNode.getTextContent());

                        // Item aktif
                        Node itemAktifNode = element.getElementsByTagName("itemAktif").item(0);
                        NodeList itemAktifList = itemAktifNode.getChildNodes();

                        for (int j = 0; j < itemAktifList.getLength(); j++) {

                            System.out.print(itemAktifList.item(j).getTextContent() + " ");
                        }

                        // TODO: TAMBAHIN TEMPAT PENYIMPANAN STATE ITEM DIMANA??
                        cardSlotsPlayer2State[row][col] = new CardSlot(card);
                    }
                }

                Land landPlayer2State = new Land(4, 5, cardSlotsPlayer2State);

                Player player2 = new Player(guldenPlayer2, landPlayer2State, deckPlayer2State);

                Game.getInstance().setPlayer2(player2);
            }
            //

        } catch (

        Exception e) {
            e.printStackTrace();
        }
    }
}
