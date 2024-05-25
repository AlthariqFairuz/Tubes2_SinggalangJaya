package com.resources.logic.plugin;

import java.lang.System;
import java.util.ArrayList;
import java.util.List;

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
import com.resources.logic.product.ProductCard;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLLoader implements Plugin {
    public String getPluginType() {
        return "XML";
    }

    public void onLoad() {
        System.out.println("XMLParser loading");
    }

    public void onSave() {
        System.out.println("XMLParser saving");
    }

    public void saveState(String filePath) {
        System.out.println("XMLParser saveState");
        try {
            // Initialize XML Builder
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // Root element
            Element root = document.createElement("gameState");
            document.appendChild(root);

            // Current Turn
            Element currentTurnNode = document.createElement("currentTurn");
            String currentTurn = Integer.toString(Game.getInstance().getTotalTurns());
            currentTurnNode.appendChild(document.createTextNode(currentTurn));
            root.appendChild(currentTurnNode);

            // Shop Item Count
            Element shopItemCountNode = document.createElement("shopItemCount");
            String count = Integer.toString(Shop.getInstance().getShopItems().size());
            shopItemCountNode.appendChild(document.createTextNode(count));
            root.appendChild(shopItemCountNode);

            // Shop Items
            Element shopItemsNodeList = document.createElement("shopItems");
            for (ShopItem shopItem : Shop.getInstance().getShopItems()) {
                // Create item node
                Element itemNode = document.createElement("item");

                // Create name node
                Element nameNode = document.createElement("name");
                String name = shopItem.getItem().getName();

                // Append name to item
                nameNode.appendChild(document.createTextNode(name));
                itemNode.appendChild(nameNode);

                // Create jumlah node
                Element jumlah = document.createElement("jumlah");
                String jumlahStr = Integer.toString(shopItem.getFrequency());
                jumlah.appendChild(document.createTextNode(jumlahStr));
                itemNode.appendChild(jumlah);

                // Append item to shopItems
                shopItemsNodeList.appendChild(itemNode);
            }
            // Append shopItems to root
            root.appendChild(shopItemsNodeList);

            // Player 1
            Element player1Node = document.createElement("player1");
            Player player1 = Game.getInstance().getPlayer1();

            // Gulden player1
            Element guldenPlayer1Node = document.createElement("gulden");
            String guldenPlayer1 = Integer.toString(player1.getGold());
            guldenPlayer1Node.appendChild(document.createTextNode(guldenPlayer1));
            player1Node.appendChild(guldenPlayer1Node);

            int activeCardsPlayer1Count = player1.getDeck().getActiveCardsCount();
            int nonActiveCardsPlayer1Count = player1.getDeck().getNonActiveCardsCount();

            // Jumlah deck player1
            Element jumlahDeckPlayer1Node = document.createElement("jumlahDeck");
            String jumlahDeckPlayer1 = Integer.toString(activeCardsPlayer1Count + nonActiveCardsPlayer1Count);
            jumlahDeckPlayer1Node.appendChild(document.createTextNode(jumlahDeckPlayer1));
            player1Node.appendChild(jumlahDeckPlayer1Node);

            // Jumlah deck aktif player1
            Element jumlahDeckAktifPlayer1Node = document.createElement("jumlahDeckAktif");
            String jumlahDeckAktifPlayer1 = Integer.toString(activeCardsPlayer1Count);
            jumlahDeckAktifPlayer1Node.appendChild(document.createTextNode(jumlahDeckAktifPlayer1));
            player1Node.appendChild(jumlahDeckAktifPlayer1Node);

            // Deck aktif player1
            Element deckAktifPlayer1Node = document.createElement("deckAktif");

            // iterate player 1 deck
            int index = 0;
            for (CardSlot cardSlot : player1.getDeck().getActiveCards()) {
                if (cardSlot.hasCard()) {
                    Element cardNode = document.createElement("card");

                    // Lokasi
                    Element lokasiNode = document.createElement("lokasi");
                    Coordinate co = new Coordinate(0, index);
                    String lokasi = Coordinate.CoordinateToCode(co);
                    lokasiNode.appendChild(document.createTextNode(lokasi));
                    cardNode.appendChild(lokasiNode);

                    // Kartu
                    Element kartuNode = document.createElement("kartu");
                    String kartu = cardSlot.getCard().getName();
                    kartuNode.appendChild(document.createTextNode(kartu));
                    cardNode.appendChild(kartuNode);

                    // Append card to deckAktif
                    deckAktifPlayer1Node.appendChild(cardNode);
                }
                index++;
            }
            // Append deckAktif to player1
            player1Node.appendChild(deckAktifPlayer1Node);

            // Kartu ladang player1
            Element kartuLadangPlayer1Node = document.createElement("kartuLadang");

            // Jumlah kartu ladang player1
            Element jumlahKartuLadangPlayer1Node = document.createElement("jumlahKartuLadang");
            int jumlahKartuLadangPlayer1 = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 5; j++) {
                    CardSlot cardSlot = player1.getLand().getCardSlots()[i][j];
                    if (cardSlot.hasCard()) {
                        // Update count
                        jumlahKartuLadangPlayer1++;

                        // Create card node
                        Element cardNode = document.createElement("card");

                        // Lokasi
                        Element lokasiNode = document.createElement("lokasi");
                        Coordinate co = new Coordinate(i, j);
                        String lokasi = Coordinate.CoordinateToCode(co);
                        lokasiNode.appendChild(document.createTextNode(lokasi));
                        cardNode.appendChild(lokasiNode);

                        // Kartu
                        Element kartuNode = document.createElement("kartu");
                        String kartu = cardSlot.getCard().getName();
                        kartuNode.appendChild(document.createTextNode(kartu));
                        cardNode.appendChild(kartuNode);

                        // Umur
                        Element umurNode = document.createElement("umur");
                        String age = Integer.toString(cardSlot.getCard().getHarvestProduct().getAddedWeight());
                        umurNode.appendChild(document.createTextNode(age));
                        cardNode.appendChild(umurNode);

                        // Jumlah item aktif
                        Element jumlahItemAktifNode = document.createElement("jumlahItemAktif");
                        int jumlahItemAktif = cardSlot.getCard().getHarvestProduct().getTotalActiveItem();
                        jumlahItemAktifNode.appendChild(document.createTextNode(Integer.toString(jumlahItemAktif)));
                        cardNode.appendChild(jumlahItemAktifNode);

                        // Item aktif
                        Element itemAktifNode = document.createElement("itemAktif");
                        List<String> listItemAktif = cardSlot.getCard().getHarvestProduct().getActiveItems();
                        for (String item : listItemAktif) {
                            Element itemNode = document.createElement("item");
                            itemNode.appendChild(document.createTextNode(item));
                            itemAktifNode.appendChild(itemNode);
                        }
                        // Append item aktif to card
                        cardNode.appendChild(itemAktifNode);

                        // Append card to kartuLadang
                        kartuLadangPlayer1Node.appendChild(cardNode);
                    }
                }
            }
            // Append jumlahKartuLadang to player1
            jumlahKartuLadangPlayer1Node
                    .appendChild(document.createTextNode(Integer.toString(jumlahKartuLadangPlayer1)));
            player1Node.appendChild(jumlahKartuLadangPlayer1Node);
            // Append kartuLadang to player1
            player1Node.appendChild(kartuLadangPlayer1Node);
            // Append player1 to root
            root.appendChild(player1Node);

            // Player 2
            // Create player2 node
            Element player2Node = document.createElement("player2");
            Player player2 = Game.getInstance().getPlayer2();

            // Gulden player2
            Element guldenPlayer2Node = document.createElement("gulden");
            String guldenPlayer2 = Integer.toString(player2.getGold());
            guldenPlayer2Node.appendChild(document.createTextNode(guldenPlayer2));
            player2Node.appendChild(guldenPlayer2Node);

            int activeCardsPlayer2Count = player2.getDeck().getActiveCardsCount();
            int nonActiveCardsPlayer2Count = player2.getDeck().getNonActiveCardsCount();

            // Jumlah deck player2
            Element jumlahDeckPlayer2Node = document.createElement("jumlahDeck");
            String jumlahDeckPlayer2 = Integer.toString(activeCardsPlayer2Count + nonActiveCardsPlayer2Count);
            jumlahDeckPlayer2Node.appendChild(document.createTextNode(jumlahDeckPlayer2));
            player2Node.appendChild(jumlahDeckPlayer2Node);

            // Jumlah deck aktif player2
            Element jumlahDeckAktifPlayer2Node = document.createElement("jumlahDeckAktif");
            String jumlahDeckAktifPlayer2 = Integer.toString(activeCardsPlayer2Count);
            jumlahDeckAktifPlayer2Node.appendChild(document.createTextNode(jumlahDeckAktifPlayer2));
            player2Node.appendChild(jumlahDeckAktifPlayer2Node);

            // Deck aktif player2
            Element deckAktifPlayer2Node = document.createElement("deckAktif");

            // iterate player 2 deck
            index = 0;
            for (CardSlot cardSlot : player2.getDeck().getActiveCards()) {
                if (cardSlot.hasCard()) {
                    Element cardNode = document.createElement("card");

                    // Lokasi
                    Element lokasiNode = document.createElement("lokasi");
                    Coordinate co = new Coordinate(0, index);
                    String lokasi = Coordinate.CoordinateToCode(co);
                    lokasiNode.appendChild(document.createTextNode(lokasi));
                    cardNode.appendChild(lokasiNode);

                    // Kartu
                    Element kartuNode = document.createElement("kartu");
                    String kartu = cardSlot.getCard().getName();
                    kartuNode.appendChild(document.createTextNode(kartu));
                    cardNode.appendChild(kartuNode);

                    // Append card to deckAktif
                    deckAktifPlayer2Node.appendChild(cardNode);
                }
                index++;
            }
            // Append deckAktif to player2
            player2Node.appendChild(deckAktifPlayer2Node);

            // Kartu ladang player2
            Element kartuLadangPlayer2Node = document.createElement("kartuLadang");

            // Jumlah kartu ladang player2
            Element jumlahKartuLadangPlayer2Node = document.createElement("jumlahKartuLadang");

            int jumlahKartuLadangPlayer2 = 0;

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 5; j++) {
                    CardSlot cardSlot = player2.getLand().getCardSlots()[i][j];
                    if (cardSlot.hasCard()) {
                        // Update count
                        jumlahKartuLadangPlayer2++;

                        // Create card node
                        Element cardNode = document.createElement("card");

                        // Lokasi
                        Element lokasiNode = document.createElement("lokasi");
                        Coordinate co = new Coordinate(i, j);
                        String lokasi = Coordinate.CoordinateToCode(co);
                        lokasiNode.appendChild(document.createTextNode(lokasi));
                        cardNode.appendChild(lokasiNode);

                        // Kartu
                        Element kartuNode = document.createElement("kartu");
                        String kartu = cardSlot.getCard().getName();
                        kartuNode.appendChild(document.createTextNode(kartu));
                        cardNode.appendChild(kartuNode);

                        // Umur
                        Element umurNode = document.createElement("umur");
                        String age = Integer.toString(cardSlot.getCard().getHarvestProduct().getAddedWeight());
                        umurNode.appendChild(document.createTextNode(age));
                        cardNode.appendChild(umurNode);

                        // Jumlah item aktif
                        Element jumlahItemAktifNode = document.createElement("jumlahItemAktif");
                        int jumlahItemAktif = cardSlot.getCard().getHarvestProduct().getTotalActiveItem();
                        jumlahItemAktifNode.appendChild(document.createTextNode(Integer.toString(jumlahItemAktif)));
                        cardNode.appendChild(jumlahItemAktifNode);

                        // Item aktif
                        Element itemAktifNode = document.createElement("itemAktif");
                        List<String> listItemAktif = cardSlot.getCard().getHarvestProduct().getActiveItems();
                        for (String item : listItemAktif) {
                            Element itemNode = document.createElement("item");
                            itemNode.appendChild(document.createTextNode(item));
                            itemAktifNode.appendChild(itemNode);
                        }

                        // Append item aktif to card
                        cardNode.appendChild(itemAktifNode);

                        // Append card to kartuLadang
                        kartuLadangPlayer2Node.appendChild(cardNode);
                    }
                }
            }
            // Append jumlahKartuLadang to player2
            jumlahKartuLadangPlayer2Node
                    .appendChild(document.createTextNode(Integer.toString(jumlahKartuLadangPlayer2)));
            player2Node.appendChild(jumlahKartuLadangPlayer2Node);
            // Append kartuLadang to player2
            player2Node.appendChild(kartuLadangPlayer2Node);
            // Append player2 to root
            root.appendChild(player2Node);

            // Write the content into XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filePath));
            transformer.transform(domSource, streamResult);

            // Output to console for testing
            System.out.println("Done creating XML File");

        } catch (Exception e) {
            e.printStackTrace();
        }
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

            // Print root element
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            // Parsing currentTurn
            Node currentTurnNode = doc.getElementsByTagName("currentTurn").item(0);
            int currentTurn = Integer.parseInt(currentTurnNode.getTextContent());
            Game.getInstance().setTotalTurns(currentTurn);
            // DEBUG
            System.out.println("Current Turn: " + currentTurnNode.getTextContent());

            // Parsing shop item count
            Node shopItemCountNode = doc.getElementsByTagName("shopItemCount").item(0);
            int shopItemCount = Integer.parseInt(shopItemCountNode.getTextContent());
            // DEBUG
            System.out.println("Shop Item Count: " + shopItemCount);

            // Parsing shop items
            ArrayList<ShopItem> shopItemsState = new ArrayList<>();
            Node shopItemsNode = doc.getElementsByTagName("shopItems").item(0);
            NodeList shopItemsList = shopItemsNode.getChildNodes();
            for (int i = 0; i < shopItemsList.getLength(); i++) {
                Node node = shopItemsList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Get name
                    String name = element.getElementsByTagName("name").item(0).getTextContent();

                    // Get card
                    Card card = CardAssets.toCard(name);

                    // Get frequency
                    int frequency = Integer.parseInt(element.getElementsByTagName("jumlah").item(0).getTextContent());

                    // Create obj
                    ShopItem shopItem = new ShopItem((ProductCard) card, frequency);

                    // Add to shop
                    shopItemsState.add(shopItem);

                    // DEBUG
                    System.out.print("Item Name: " +
                            element.getElementsByTagName("name").item(0).getTextContent() + " ");
                    System.out.print("Jumlah: " +
                            element.getElementsByTagName("jumlah").item(0).getTextContent() + " ");
                    System.out.println();
                }
            }
            Shop.getInstance().setShopItems(shopItemsState);

            // Parsing player1 details
            Node player1Node = doc.getElementsByTagName("player1").item(0);
            if (player1Node.getNodeType() == Node.ELEMENT_NODE) {
                Element player1Element = (Element) player1Node;
                // Parsing gulden
                int guldenPlayer1 = Integer
                        .parseInt(player1Element.getElementsByTagName("gulden").item(0).getTextContent());
                // DEBUG
                System.out.println("Player 1 Gulden: " + guldenPlayer1);

                // Parse jumlah dek total
                int jumlahDeckPlayer1 = Integer
                        .parseInt(player1Element.getElementsByTagName("jumlahDeck").item(0).getTextContent());
                // DEBUG
                System.out.println("Player 1 Jumlah Deck: " + jumlahDeckPlayer1);

                // Parse jumlah dek aktif
                int jumlahDeckAktifPlayer1 = Integer
                        .parseInt(player1Element.getElementsByTagName("jumlahDeckAktif").item(0).getTextContent());
                // DEBUG
                System.out.println("Player 1 Jumlah Deck Aktif: " + jumlahDeckAktifPlayer1);

                // Parse deck aktif
                Node deckAktifPlayer1Node = player1Element.getElementsByTagName("deckAktif").item(0);
                NodeList deckAktifList = deckAktifPlayer1Node.getChildNodes();
                // Parsing deckAktif for player1
                Deck deckPlayer1State = new Deck(40, 6);
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
                        deckPlayer1State.setCardToActiveDeck(card, col);

                        // DEBUG
                        System.out.println(card.getName() + " " + kartu + " " + lokasi);
                    }
                }

                // Generate non-active cards
                for (int i = 0; i < jumlahDeckPlayer1 - jumlahDeckAktifPlayer1; i++) {
                    Card randCard = CardAssets.getRandomCard();
                    deckPlayer1State.addCardToNonActiveDeck(randCard);
                }

                // Parsing kartuLadang for player1
                // Jumlah kartu ladang
                Land landPlayer1State = new Land();
                Node jumlahKartuLadangPlayer1Node = player1Element.getElementsByTagName("jumlahKartuLadang").item(0);
                int jumlahKartuLadangPlayer1 = Integer.parseInt(jumlahKartuLadangPlayer1Node.getTextContent());
                // DEBUG
                System.out.println("Player 1 Jumlah Kartu Ladang: " + jumlahKartuLadangPlayer1);

                // Parsing kartu ladang
                NodeList kartuLadangList = player1Element.getElementsByTagName("kartuLadang").item(0).getChildNodes();
                for (int i = 0; i < kartuLadangList.getLength(); i++) {
                    Node node = kartuLadangList.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
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
                        // Update added weight
                        card.getHarvestProduct().setAddedWeight(umur);

                        // Jumlah item aktif
                        Node jumlahItemAktifNode = element.getElementsByTagName("jumlahItemAktif").item(0);
                        int jumlahItemAktif = Integer.parseInt(jumlahItemAktifNode.getTextContent());

                        // Item aktif
                        Node itemAktifNode = element.getElementsByTagName("itemAktif").item(0);
                        NodeList itemAktifList = itemAktifNode.getChildNodes();
                        List<String> itemAktif = new ArrayList<>();
                        for (int j = 0; j < itemAktifList.getLength(); j++) {
                            Node itemNode = itemAktifList.item(j);

                            // If node
                            if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element itemElement = (Element) itemNode;

                                // Add item to list
                                itemAktif.add(itemElement.getTextContent());
                            }
                        }
                        // Set item aktif
                        card.getHarvestProduct().setActiveItems(itemAktif);

                        // Set card to land
                        landPlayer1State.setLandSlot(row, col, card);

                        // DEBUG
                        System.out.println(kartu + " " + lokasi + " " + umur + " " + jumlahItemAktif + " " + itemAktif);
                    }
                }
                // Create land & set i
                Player player1 = new Player(guldenPlayer1, landPlayer1State, deckPlayer1State);
                Game.getInstance().setPlayer1(player1);
            }

            // Player 2 states
            Node player2Node = doc.getElementsByTagName("player2").item(0);

            // REPEAT AS BEFORE LIKE PLAYER 1

            if (player2Node.getNodeType() == Node.ELEMENT_NODE) {
                Element player2Element = (Element) player2Node;

                // Parsing gulden
                int guldenPlayer2 = Integer
                        .parseInt(player2Element.getElementsByTagName("gulden").item(0).getTextContent());
                // DEBUG
                System.out.println("Player 2 Gulden: " + guldenPlayer2);

                // Parse jumlah dek total
                int jumlahDeckPlayer2 = Integer
                        .parseInt(player2Element.getElementsByTagName("jumlahDeck").item(0).getTextContent());
                // DEBUG
                System.out.println("Player 2 Jumlah Deck: " + jumlahDeckPlayer2);

                // Parse jumlah dek aktif
                int jumlahDeckAktifPlayer2 = Integer
                        .parseInt(player2Element.getElementsByTagName("jumlahDeckAktif").item(0).getTextContent());
                // DEBUG
                System.out.println("Player 2 Jumlah Deck Aktif: " + jumlahDeckAktifPlayer2);

                // Parse deck aktif
                Node deckAktifPlayer2Node = player2Element.getElementsByTagName("deckAktif").item(0);
                NodeList deckAktifList = deckAktifPlayer2Node.getChildNodes();
                // Parsing deckAktif for player1
                Deck deckPlayer2State = new Deck(40, 6);
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
                        deckPlayer2State.setCardToActiveDeck(card, col);

                        // DEBUG
                        System.out.println(card.getName() + " " + kartu + " " + lokasi);
                    }
                }

                // Generate non-active cards
                for (int i = 0; i < jumlahDeckPlayer2 - jumlahDeckAktifPlayer2; i++) {
                    Card randCard = CardAssets.getRandomCard();
                    deckPlayer2State.addCardToNonActiveDeck(randCard);
                }

                // Parsing kartuLadang for player2
                // Jumlah kartu ladang
                Land landPlayer2State = new Land();
                Node jumlahKartuLadangPlayer2Node = player2Element.getElementsByTagName("jumlahKartuLadang").item(0);
                int jumlahKartuLadangPlayer2 = Integer.parseInt(jumlahKartuLadangPlayer2Node.getTextContent());
                // DEBUG
                System.out.println("Player 2 Jumlah Kartu Ladang: " + jumlahKartuLadangPlayer2);

                // Parsing kartu ladang
                NodeList kartuLadangList = player2Element.getElementsByTagName("kartuLadang").item(0).getChildNodes();

                for (int i = 0; i < kartuLadangList.getLength(); i++) {
                    Node node = kartuLadangList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;

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
                        // Update added weight
                        card.getHarvestProduct().setAddedWeight(umur);

                        // Jumlah item aktif
                        Node jumlahItemAktifNode = element.getElementsByTagName("jumlahItemAktif").item(0);
                        int jumlahItemAktif = Integer.parseInt(jumlahItemAktifNode.getTextContent());

                        // Item aktif
                        Node itemAktifNode = element.getElementsByTagName("itemAktif").item(0);
                        NodeList itemAktifList = itemAktifNode.getChildNodes();
                        List<String> itemAktif = new ArrayList<>();
                        for (int j = 0; j < itemAktifList.getLength(); j++) {
                            Node itemNode = itemAktifList.item(j);

                            // If node
                            if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element itemElement = (Element) itemNode;

                                // Add item to list
                                itemAktif.add(itemElement.getTextContent());

                            }
                        }
                        // Set item aktif
                        card.getHarvestProduct().setActiveItems(itemAktif);

                        // Set card to land
                        landPlayer2State.setLandSlot(row, col, card);

                        // DEBUG
                        System.out.println(card.getName() + " " + lokasi + " " + umur + " " + jumlahItemAktif + " "
                                + itemAktif);
                    }
                }
                // Create player and set it
                Player player2 = new Player(guldenPlayer2, landPlayer2State, deckPlayer2State);
                Game.getInstance().setPlayer2(player2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
