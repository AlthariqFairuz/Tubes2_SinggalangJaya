package com.resources.gui;

import com.resources.logic.*;
import com.resources.logic.animal.AnimalCard;
import com.resources.logic.item.ItemCard;
import com.resources.logic.plant.PlantCard;
import com.resources.logic.product.ProductCard;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public final static String imageDirectory = "file:/home/azzmi/Desktop/cobarun/Tubes2_SinggalangJaya/src/gui/src/main/resources/com/resources/gui/Cards/";
    public final static int TotalGameTurns = 20;

    public static boolean ladangku;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label scoreLabelPlayer1;
    @FXML
    private Label scoreLabelPlayer2;

    @FXML
    private Timeline timelineGame;

    // Cards
    @FXML
    private GridPane cardLandGrid;
    @FXML
    private GridPane deck;

    // Buttons
    @FXML
    private Button ladangkuButton;
    @FXML
    private Button ladangLawanButton;
    @FXML
    private Button nextButton;

    // Labels
    @FXML
    private Label totalInactiveCardsLabel;
    @FXML
    private Label totalTurnsLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Running initialize...");

        ladangkuButton.setDisable(true);
        ladangLawanButton.setDisable(false);
        ladangku = true;

        update_periodically();

        implementDragAndDrop();

    }

    public Player getCurrentLadangShownPlayer() {
        if (ladangku) {
            return Game.getInstance().getCurrentPlayer();
        } else {
            return Game.getInstance().getOtherPlayer();
        }
    }


    private void initializeScores() {
        if (scoreLabelPlayer1 != null) {
            int goldPlayer1 = Game.getInstance().getPlayer1().getGold();
            scoreLabelPlayer1.setText(String.valueOf(goldPlayer1));
        }
        if (scoreLabelPlayer2 != null) {
            int goldPlayer2 = Game.getInstance().getPlayer2().getGold();
            scoreLabelPlayer2.setText(String.valueOf(goldPlayer2));
        }
    }

    public void implementDragAndDrop() {
        for (int col = 0; col < 6; col++) {
            ImageView newImageView = (ImageView) getNodeFromGridPane2(deck, col);
            int finalCol = col;
            newImageView.setOnDragDetected(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    /* drag was detected, start drag-and-drop gesture*/
//                    System.out.println("onDragDetected");
                    Deck currentDeck = Game.getInstance().getCurrentPlayer().getDeck();
                    if (currentDeck.getActiveCards()[finalCol].hasCard()) {
//                        System.out.println("Non empty");
                        Card currentCard = currentDeck.getActiveCards()[finalCol].getCard();
//                        /* allow any transfer mode */
                        Dragboard db = newImageView.startDragAndDrop(TransferMode.ANY);
//
                        ClipboardContent content = new ClipboardContent();
                        String contentString = new DragDropContent(currentCard.getName(), -1, finalCol).toString();
////                        System.out.println(contentString);
                        content.putString(contentString);
                        db.setContent(content);
                    } else {
//                        System.out.println("Empty");
                    }

                    event.consume();
                }
            });
        }
        for (int col = 0; col < 5; ++col) {
            for (int row = 0; row < 4; ++row) {
                ImageView newImageView = (ImageView) getNodeFromGridPane(cardLandGrid, col, row);

                int finalRow = row;
                int finalCol = col;
                newImageView.setOnDragDetected(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        /* drag was detected, start drag-and-drop gesture*/
//                    System.out.println("onDragDetected");
                        if (!ladangku) {
                            event.consume();
                        }
                        Land currentLand = getCurrentLadangShownPlayer().getLand();
                        if (currentLand.getCardSlots()[finalRow][finalCol].hasCard()) {
//                            System.out.println(finalRow + " and " + finalCol + " works");
//                        System.out.println("Non empty");
                            Card currentCard = currentLand.getCardSlots()[finalRow][finalCol].getCard();
                            /* allow any transfer mode */
                            Dragboard db = newImageView.startDragAndDrop(TransferMode.ANY);

                            ClipboardContent content = new ClipboardContent();
                            String contentString = new DragDropContent(currentCard.getName(), finalRow, finalCol).toString();
//                        System.out.println(contentString);
                            content.putString(contentString);
                            db.setContent(content);
                        } else {
//                        System.out.println("Empty");
                        }

                        event.consume();
                    }
                });

                newImageView.setOnDragOver(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        /* data is dragged over the target */
//                        System.out.println("onDragOver");
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
                    }
                });

                newImageView.setOnDragDropped(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
//                        System.out.println("onDragDropped");
                        ImageView sourceImageView = (ImageView) event.getGestureSource();
                        CardSlot sourceSlot;
                        boolean fromDeck = false;
                        if (isOnDeck(sourceImageView)) {
                            fromDeck = true;
//                            System.out.println("On deck");
                            Pair sourceDeck = getLocFromGridPane2(deck, (Node) event.getGestureSource());
//                            System.out.println("Found at " + sourceDeck.getFirst() + " and " + sourceDeck.getSecond());
                            sourceSlot = Game.getInstance().getCurrentPlayer().getDeck().getActiveCards()[sourceDeck.getSecond()];
                        } else {
//                            System.out.println("On land");
                            Pair sourceLand = getLocFromGridPane(cardLandGrid, (Node) event.getGestureSource());
//                            System.out.println("Found at " + sourceLand.getFirst() + " and " + sourceLand.getSecond());
                            sourceSlot = getCurrentLadangShownPlayer().getLand().getCardSlots()[sourceLand.getFirst()][sourceLand.getSecond()];
//                            System.out.println("This one... " + Boolean.valueOf(sourceSlot.hasCard()));
                        }

                        Pair destinationLand = getLocFromGridPane(cardLandGrid, newImageView);
                        CardSlot landSlot = getCurrentLadangShownPlayer().getLand().getCardSlots()[destinationLand.getFirst()][destinationLand.getSecond()];
//                        System.out.println("Destionation " + destinationLand.getFirst() + "  " + destinationLand.getSecond());
                        Card sourceCard = sourceSlot.getCard();


                        if (ladangku) {
                            if (fromDeck) {
                                if (!landSlot.hasCard()) {
                                    if (sourceSlot.getCard() instanceof ItemCard) {
                                        System.out.println("Kartu efek item tidak dapat ditaruh di ladang");
                                    } else {
                                        landSlot.setCard(sourceSlot.popCard());
                                    }
                                } else {
                                    Card destinationCard = landSlot.getCard();

                                    if (sourceCard instanceof ProductCard) {
                                        if (destinationCard.canEat((ProductCard) sourceCard)) {
                                            destinationCard.eat((ProductCard) sourceCard);
                                            sourceSlot.popCard();
                                        } else {
                                            System.out.println("Can't eat that product");
                                        }
                                    } else if (sourceCard.getName().equals("INSTANT_HARVEST")) {
                                        if (destinationCard.canHarvestInstantly()) {
                                            sourceSlot.popCard();
                                            sourceSlot.setCard(destinationCard.getHarvestProduct());
                                            landSlot.popCard();
                                        } else {
                                            System.out.println("Kartu tidak dapat dipanen");
                                        }
                                    } else if (sourceCard.getName().equals("DESTROY")) {
                                        System.out.println("Tidak bisa destroy lahan sendiri");
                                    } else if (sourceCard instanceof ItemCard) {
                                        if (destinationCard.canReceiveItem((ItemCard) sourceCard)) {
                                            if (destinationCard.receiveItem((ItemCard) sourceCard)) {
                                                sourceSlot.popCard();
                                            } else {
                                                System.out.println("Kartu efek tidak bisa di apply karena sudah dilakukan atau alasan lain");
                                            }
                                        } else {
                                            System.out.println("Can't receive that item");
                                        }
                                    }
                                }
                            } else {
                                if (!landSlot.hasCard()) {
                                    landSlot.setCard(sourceSlot.popCard());
                                }
                            }
                        } else {
                            if (fromDeck) {
                                if (!landSlot.hasCard()) {
                                    System.out.println("Tidak bisa menaruh kartu di ladang lawan");
                                } else {
                                    if (sourceCard.getName().equals("DESTROY")) {
                                        if ((landSlot.getCard() instanceof AnimalCard) || (landSlot.getCard() instanceof PlantCard)) {
                                            if (landSlot.getCard().isProtectedFromBear()) {
                                                System.out.println("Kartu terprotected sehingga tidak bisa di destroy");
                                            } else {
                                                sourceSlot.popCard();
                                                landSlot.popCard();
                                            }
                                        } else {
                                            System.out.println("Hanya dapat mengdestroy hewan atan tanaman musuh");

                                        }
                                    } else {
                                        System.out.println("Anda hanya bisa DESTROY ladang musuh");
                                    }
                                }
                            } else {
                                System.out.println("Tidak bisa memindahkan kartu lawan");
                            }
                        }
                        event.setDropCompleted(true);
                        event.consume();
                    }
                });
            }
        }

    }

    private void update_periodically() {
        timelineGame = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            for (int col = 0; col < 5; ++col) {
                for (int row = 0; row < 4; ++row) {
                    Player selectedPlayer = getCurrentLadangShownPlayer();

                    CardSlot slot = selectedPlayer.getLand().getCardSlots()[row][col];
                    ImageView newImageView = (ImageView) getNodeFromGridPane(cardLandGrid, col, row);
                    if (newImageView == null) {
                        continue;
                    }
                    if (slot.hasCard()) {
                        newImageView.setImage(new Image(imageDirectory + slot.getCard().getImageLocation()));
                    } else {
                        newImageView.setImage(new Image(imageDirectory + "Other/Empty.png"));
                    }
                }
            }
            for (int col = 0; col < 6; col++) {
                ImageView newImageView = (ImageView) getNodeFromGridPane2(deck, col);
                CardSlot slot = Game.getInstance().getCurrentPlayer().getDeck().getActiveCards()[col];
                if (slot.hasCard()) {
                    newImageView.setImage(new Image(imageDirectory + slot.getCard().getImageLocation()));
                } else {
                    newImageView.setImage(new Image(imageDirectory + "Other/Empty.png"));
                }
            }
            totalInactiveCardsLabel.setText("Deck:\n" + Integer.toString(Game.getInstance().getCurrentPlayer().getDeck().countCardsInNonActiveDeck()) + "/" + Game.getInstance().getCurrentPlayer().getDeck().getTotalDeckCapacity());
            totalTurnsLabel.setText("Turns: " + Integer.toString(Game.getInstance().getTotalTurns()));
        }));

        timelineGame.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        timelineGame.play();


//        timelinePlayer2 = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
////            if (Game.getInstance())
//            for (int col = 0; col < 5; ++col) {
//                for (int row = 0; row < 4; ++row) {
//                    CardSlot slot = Game.getInstance().getPlayer2().getLand().getCardSlots()[row][col];
//                    ImageView newImageView = (ImageView) getNodeFromGridPane(cardLandGrid, col, row);
//                    if (newImageView == null) {
//                        continue;
//                    }
//                    if (slot.hasCard()) {
//                        newImageView.setImage(new Image(imageDirectory + slot.getCard().getImageLocation()));
//                    } else {
//                        newImageView.setImage(new Image(imageDirectory + "Other/Empty.png"));
//                    }
//                }
//            }
//            for (int col = 0; col < 6; col++) {
//                ImageView newImageView = (ImageView) getNodeFromGridPane2(deck, col);
//                CardSlot slot = Game.getInstance().getPlayer2().getDeck().getActiveCards()[col];
//                if (slot.hasCard()) {
//                    newImageView.setImage(new Image(imageDirectory + slot.getCard().getImageLocation()));
//                } else {
//                    newImageView.setImage(new Image(imageDirectory + "Other/Empty.png"));
//                }
//            }
//        }));
//
//        timelinePlayer2.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
//        timelinePlayer2.play(); // Start the timeline for player 2
    }

    private void deleteImageView(ImageView imageView) {
        imageView.setImage(new Image(imageDirectory + "Other/Empty.png"));
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null &&
                    GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col &&
                    node instanceof ImageView) {
                return (ImageView) node;
            }
        }
        return null;
    }

    private Pair getLocFromGridPane(GridPane gridPane, Node n) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && node == n) {
                return new Pair(GridPane.getRowIndex(node), GridPane.getColumnIndex(node));
            }
        }

        return new Pair(-1, -1);
    }


    private Node getNodeFromGridPane2(GridPane gridPane, int col) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == col &&
                    node instanceof ImageView) {
                return (ImageView) node;
            }
        }
        return null;
    }

    private Pair getLocFromGridPane2(GridPane gridPane, Node n) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) != null && node == n) {
                return new Pair(0, GridPane.getColumnIndex(node));
            }
        }
        return new Pair(-1, -1);
    }

    private boolean isOnDeck(ImageView imageView) {
        for (Node node : deck.getChildren()) {
            if (node instanceof ImageView && (ImageView) node == imageView) {
                return true;
            }
        }
        return false;
    }

    private boolean isOnLand(ImageView imageView) {
        for (Node node : cardLandGrid.getChildren()) {
            if (node instanceof ImageView && (ImageView) node == imageView) {
                return true;
            }
        }
        return false;
    }


    @FXML
    public void nextButtonHandler(MouseEvent event) {
        Game.getInstance().next();

        if (Game.getInstance().getTotalTurns() == HomeController.TotalGameTurns) {
            showWinner();
        }
        getShuffledCards();
    }

    public void showWinner() {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("winner.fxml"));
            Parent root = loader.load();

            // Create the dialog stage
            Stage winnerStage = new Stage();
            winnerStage.initModality(Modality.APPLICATION_MODAL);
            winnerStage.initStyle(StageStyle.TRANSPARENT);

            // Set title and icon
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Logo.jpg")));
            winnerStage.getIcons().add(icon);
            winnerStage.setTitle("Game Result");

            // Set the scene to the stage
            winnerStage.setScene(new Scene(root));

            // Get the controller and call the setWinner method
            WinnerController winnerController = loader.getController();
            winnerController.setWinner();

            // Show the dialog and wait until the user closes it
            winnerStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getShuffledCards() {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("shuffle.fxml"));

            // Create the dialog stage
            Stage shuffleStage = new Stage();

            // Set title and icon
            Image icon = new Image(Objects.requireNonNull(HomeController.class.getResourceAsStream("Logo.jpg")));
            shuffleStage.getIcons().add(icon);
            shuffleStage.setTitle("Save");

            shuffleStage.initModality(Modality.WINDOW_MODAL);
            shuffleStage.initStyle(StageStyle.TRANSPARENT);
            shuffleStage.setScene(new Scene(loader.load()));

            // Get the controller and set the dialog reference
            ShuffleController controller = loader.getController();
            controller.setShuffle(shuffleStage);

            // Show the dialog and wait until the user closes it
            shuffleStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getCardDescription(CardSlot c) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("dialogbox.fxml"));

            // Create the dialog stage
            Stage dialogStage = new Stage();

            // Set title and icon
            Image icon = new Image(Objects.requireNonNull(HomeController.class.getResourceAsStream("Logo.jpg")));
            dialogStage.getIcons().add(icon);
            dialogStage.setTitle("Deskripsi");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initStyle(StageStyle.TRANSPARENT);
            dialogStage.setScene(new Scene(loader.load()));

            // Get the controller and set the dialog reference
            DialogBoxController controller = loader.getController();
            controller.setDialog(dialogStage);
            controller.setDialogBox(c);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void keLadangku(MouseEvent e) {
        ladangku = true;
        ladangkuButton.setDisable(true);
        ladangLawanButton.setDisable(false);
        nextButton.setDisable(false);
    }

    @FXML
    public void keLadangLawan(MouseEvent e) {
        ladangku = false;
        ladangkuButton.setDisable(false);
        ladangLawanButton.setDisable(true);
        nextButton.setDisable(true);
    }


    //    private void updateLabel(Label label, Integer newScore) {
//        label.setText(String.valueOf(newScore));
//    }
//
    @FXML
    public void onGridOnClick(MouseEvent event) {

        ImageView imageView = (ImageView) event.getSource();
        int row = GridPane.getRowIndex(imageView);
        int col = GridPane.getColumnIndex(imageView);

        CardSlot cardSlot = getCurrentLadangShownPlayer().getLand().getCardSlots()[row][col];
//        System.out.println("Clicked on Row: " + row + ", Column: " + col);
        if (cardSlot.hasCard()) {
            Card c = cardSlot.getCard();
            if ((c instanceof AnimalCard) || (c instanceof PlantCard)) {
                getCardDescription(cardSlot);
            } else {
                System.out.println("Not an animal or a plant");
            }
        }

    }


    public void shopClicked(MouseEvent event) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("shop.fxml"));

            // Create the dialog stage
            Stage shopStage = new Stage();

            // Set title and icon
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Logo.jpg")));
            shopStage.getIcons().add(icon);
            shopStage.setTitle("Shop");

            shopStage.initModality(Modality.WINDOW_MODAL);
            shopStage.initStyle(StageStyle.TRANSPARENT);
            shopStage.setScene(new Scene(loader.load()));

            // Get the controller and set the dialog reference
            ShopController controller = loader.getController();
            controller.setShop(shopStage);

            // Show the dialog and wait until the user closes it
            shopStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadClicked(MouseEvent event) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("load.fxml"));

            // Create the dialog stage
            Stage loadStage = new Stage();

            // Set title and icon
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Logo.jpg")));
            loadStage.getIcons().add(icon);
            loadStage.setTitle("Load");

            loadStage.initModality(Modality.WINDOW_MODAL);
            loadStage.initStyle(StageStyle.TRANSPARENT);
            loadStage.setScene(new Scene(loader.load()));

            // Get the controller and set the dialog reference
            LoadController controller = loader.getController();
            controller.setLoad(loadStage);

            // Show the dialog and wait until the user closes it
            loadStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveClicked(MouseEvent event) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("save.fxml"));

            // Create the dialog stage
            Stage saveStage = new Stage();

            // Set title and icon
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Logo.jpg")));
            saveStage.getIcons().add(icon);
            saveStage.setTitle("Save");

            saveStage.initModality(Modality.WINDOW_MODAL);
            saveStage.initStyle(StageStyle.TRANSPARENT);
            saveStage.setScene(new Scene(loader.load()));

            // Get the controller and set the dialog reference
            SaveController controller = loader.getController();
            controller.setSave(saveStage);

            // Show the dialog and wait until the user closes it
            saveStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pluginClicked(MouseEvent event) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("plugin.fxml"));

            // Create the dialog stage
            Stage pluginStage = new Stage();

            // Set title and icon
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Logo.jpg")));
            pluginStage.getIcons().add(icon);
            pluginStage.setTitle("Plugins");

            pluginStage.initModality(Modality.WINDOW_MODAL);
            pluginStage.initStyle(StageStyle.TRANSPARENT);
            pluginStage.setScene(new Scene(loader.load()));

            // Get the controller and set the dialog reference
            PluginController controller = loader.getController();
            controller.setPlugin(pluginStage);

            // Show the dialog and wait until the user closes it
            pluginStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // start old

//     private void updateActiveCards() {
//         Deck deck = Game.getInstance().getPlayer1().getDeck(); // Adjust to get the correct player deck
//         CardSlot[] activeCards = deck.getActiveCards();
//         Label[] cardActiveLabels = {cardActiveLabel0, cardActiveLabel1, cardActiveLabel2, cardActiveLabel3, cardActiveLabel4, cardActiveLabel5};
//         ImageView[] imgActiveCards = {imgActiveCard0, imgActiveCard1, imgActiveCard2, imgActiveCard3, imgActiveCard4, imgActiveCard5};

//         for (int i = 0; i < activeCards.length && i < cardActiveLabels.length; i++) {
//             if (activeCards[i] != null) {
//                 Card card = activeCards[i].getCard();
//                 cardActiveLabels[i].setText(card.getName());
//             } else {
//                 cardActiveLabels[i].setText("Empty");
//                 imgActiveCards[i].setImage(null);
// //                imgActiveCards[i].setImage(new Image("/faq.png"));
//             }
//         }
//     }

//     private void updateLandCard() {
//         Land land = Game.getInstance().getPlayer1().getLand();
//         CardSlot[][] cardSlots = land.getCardSlots();

//         for (int row = 0; row < 4; row++) {
//             for (int col = 0; col < 5; col++) {
//                 Pane pane = (Pane) cardLandGrid.getChildren().get(row * 5 + col);
//                 Label label = (Label) pane.getChildren().get(1);
//                 ImageView imageView = (ImageView) pane.getChildren().get(0);

//                 // Initialize the card slot if necessary
//                 if (cardSlots[row][col] == null) {
// //                    cardSlots[row][col] = new CardSlot();  // Assuming a constructor for CardSlot
//                     label.setText("Empty");
//                 }

//                 // Set label and image based on the card slot (this is just a placeholder)
//                 label.setText("Slot " + (row * 5 + col + 1));
//                 imageView.setImage(null);  // Set the image as per your logic
//             }
//         }
//     }

}
