package com.resources.gui;

import com.resources.logic.Card;
import com.resources.logic.Deck;
import com.resources.logic.Game;
import com.resources.logic.Player;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ShuffleController {

    @FXML
    private ImageView slot1;
    @FXML
    private ImageView slot2;
    @FXML
    private ImageView slot3;
    @FXML
    private ImageView slot4;

    private Stage shuffleStage;
    private List<Card> cardsTaken;

    public void setShuffle(Stage shuffleStage) {
        this.shuffleStage = shuffleStage;
        onShuffleButtonClicked(null);
    }

    @FXML
    public void onClickBackButtonShuffleBox(MouseEvent event) {
        if (shuffleStage != null) {
            shuffleStage.close();
            for (Card card : cardsTaken) {
                Deck deck = Game.getInstance().getCurrentPlayer().getDeck();
                deck.addCardToActiveDeck(card);
                Game.getInstance().getCurrentPlayer().getDeck().deleteCardFromInactiveDeck(card);
            }
        }
    }

    @FXML
    public void onShuffleButtonClicked(MouseEvent event) {
        Deck deck = Game.getInstance().getCurrentPlayer().getDeck();
        int NoCardsTaken = Math.min(4, Math.min(deck.countEmptySlotsInActiveDeck(), deck.countCardsInNonActiveDeck()));
        cardsTaken = deck.getNrandomCardsFromInactiveDeck(NoCardsTaken);
        if (cardsTaken.size() > 0) {
            slot1.setImage(new Image(HomeController.imageDirectory + cardsTaken.get(0).getImageLocation()));
        }
        if (cardsTaken.size() > 1) {
            slot2.setImage(new Image(HomeController.imageDirectory + cardsTaken.get(1).getImageLocation()));
        }
        if (cardsTaken.size() > 2) {
            slot3.setImage(new Image(HomeController.imageDirectory + cardsTaken.get(2).getImageLocation()));
        }
        if (cardsTaken.size() > 3) {
            slot4.setImage(new Image(HomeController.imageDirectory + cardsTaken.get(3).getImageLocation()));
        }
    }
}
