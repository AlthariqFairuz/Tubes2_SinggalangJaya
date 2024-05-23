package com.resources.gui;

import com.resources.logic.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label scoreLabelPlayer1; // Add the Label with @FXML annotation

    @FXML
    private Label scoreLabelPlayer2; // Add the Label with @FXML annotation
    private Timeline timelinePlayer1;
    private Timeline timelinePlayer2;

    @FXML
    private ImageView imgActiveCard0, imgActiveCard1, imgActiveCard2, imgActiveCard3, imgActiveCard4, imgActiveCard5; 
    
    @FXML
    private Label cardActiveLabel0, cardActiveLabel1, cardActiveLabel2, cardActiveLabel3, cardActiveLabel4, cardActiveLabel5;

    @FXML
    private GridPane cardLandGrid;

    @FXML
    private Deck deck;

    @FXML
    private ImageView img_hewan_bear, img_hewan_ayam, img_hewan_cow, img_hewan_hiu, img_hewan_horse, img_hewan_sheep, img_produk_corn, img_produk_daging_beruang, img_produk_daging_domba, img_daging_kuda, img_produk_pumpkin, img_produk_sirip, img_produk_stroberi, img_produk_susu, img_produk_telur, img_tanaman_corn_seed, img_tanaman_pumpkin_seed, img_tanaman_strawberry_seed, img_item_accelerate, img_item_trap, img_item_delay, img_item_destroy, img_item_instant_harvest, img_item_protect;



    // @Override
    // public void initialize(URL location, ResourceBundle resources) {
    //     deck = Game.getInstance().getPlayer1().getDeck();
    //     initializeScores(); // Call the method to initialize the scores
    //     // startScoreIncrementing(); // Start the timelines to increment scores every second
    //     updateActiveCards();
    //     updateLandCard();
    // }

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
    

    private void startScoreIncrementing() {
        // Create a Timeline that increments the score for player 1 every second
        timelinePlayer1 = new Timeline(new KeyFrame(Duration.seconds(1), event -> incrementScorePlayer1()));
        timelinePlayer1.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        timelinePlayer1.play(); // Start the timeline for player 1

        // Create a Timeline that increments the score for player 2 every second
        timelinePlayer2 = new Timeline(new KeyFrame(Duration.seconds(1), event -> incrementScorePlayer2()));
        timelinePlayer2.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        timelinePlayer2.play(); // Start the timeline for player 2
    }

    public void onGridOnClick(MouseEvent event) {
        ImageView clickedImageView = (ImageView) event.getSource();
        String cardName = "";
        String imageURL = "";

        if (clickedImageView == img_hewan_bear) {
            cardName = "BERUANG";
            imageURL = "@hewan_bear.png";
        } else if (clickedImageView == img_hewan_cow) {
            cardName = "SAPI";
            imageURL = "@hewan_cow.png";
        } else if (clickedImageView == img_hewan_horse) {
            cardName = "KUDA";
            imageURL = "@hewan_horse.png";
        } else if (clickedImageView == img_hewan_sheep) {
            cardName = "DOMBA";
            imageURL = "@hewan_sheep.png";
        } else if (clickedImageView == img_produk_corn) {
            cardName = "JAGUNG";
            imageURL = "@produk_corn.png";
        } else if (clickedImageView == img_produk_stroberi) {
            cardName = "STROBERI";
            imageURL = "@produk_stroberi.png";
        }

        Card card = CardAssets.toCard(cardName);

        showDialog(card.getName(), card.getNumber(), card.getImageLocation());

    }

    private void showDialog(String cardName, int ageCard, String imageURL) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogbox.fxml"));

            // Create the dialog stage
            Stage dialogStage = new Stage();

            // Set title and icon
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Logo.jpg")));
            dialogStage.getIcons().add(icon);
            dialogStage.setTitle("Item");

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initStyle(StageStyle.TRANSPARENT);
            dialogStage.setScene(new Scene(loader.load()));

            // Get the controller and set the dialog reference
            DialogBoxController controller = loader.getController();
            controller.setAssetName(cardName);
//            controller.setAssetImage(imageURL);
            controller.setAgeLabel(ageCard);
            controller.setDialog(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void incrementScorePlayer1() {
        if (scoreLabelPlayer1 != null) {
            // Get the current score from the label
            String currentScoreText = scoreLabelPlayer1.getText();
            try {
                // Parse the current score to an integer
                int currentScore = Integer.parseInt(currentScoreText);
                // Increment the score
                currentScore++;
                // Set the updated score back to the label
                updateLabel(scoreLabelPlayer1, currentScore);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void incrementScorePlayer2() {
        if (scoreLabelPlayer2 != null) {
            // Get the current score from the label
            String currentScoreText = scoreLabelPlayer2.getText();
            try {
                // Parse the current score to an integer
                int currentScore = Integer.parseInt(currentScoreText);
                // Increment the score
                currentScore++;
                // Set the updated score back to the label
                updateLabel(scoreLabelPlayer2, currentScore);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateLabel(Label label, Integer newScore) {
        label.setText(String.valueOf(newScore));
    }

    public void switchToPlayer1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("player1.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPlayer2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("player2.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    public void ladangLawanPressed(ActionEvent event) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("player2.fxml"));

            // Load the scene from the FXML
            Scene newScene = new Scene(loader.load());

            // Get the current stage (window)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Optionally, set title and icon for the stage
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Logo.jpg")));
            currentStage.getIcons().add(icon);
            currentStage.setTitle("Enemy's Land");

            // Set the new scene to the current stage
            currentStage.setScene(newScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
