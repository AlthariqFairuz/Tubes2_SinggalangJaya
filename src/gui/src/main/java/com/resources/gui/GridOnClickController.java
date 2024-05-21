package com.resources.gui;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import com.resources.logic.Game;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GridOnClickController implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeScores(); // Call the method to initialize the scores
        startScoreIncrementing(); // Start the timelines to increment scores every second
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
}
