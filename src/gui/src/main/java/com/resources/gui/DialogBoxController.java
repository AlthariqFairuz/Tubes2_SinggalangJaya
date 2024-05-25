package com.resources.gui;

import com.resources.logic.Card;
import com.resources.logic.CardSlot;
import com.resources.logic.Game;
import com.resources.logic.animal.AnimalCard;
import com.resources.logic.plant.PlantCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.resources.gui.HomeController.imageDirectory;
import static com.resources.gui.HomeController.ladangku;
import static com.resources.logic.CardAssets.toCard;

public class DialogBoxController {
    private Stage dialog;

    private CardSlot currentCardSlot;

    private Card currentCard;

    @FXML
    private Label assetNameLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label itemAktifLabel;

    @FXML
    private ImageView assetImageView;

    @FXML
    private Button panenButton;

    public void setDialog(Stage dialog) {
        this.dialog = dialog;
    }

    public void setDialogBox(CardSlot c) {
        this.currentCardSlot = c;
        this.currentCard = c.getCard();
        if (!currentCard.canHarvest() || !ladangku || !Game.getInstance().getCurrentPlayer().getDeck().isActiveDeckAvailable()) {
            panenButton.setDisable(true);
        } else {
            panenButton.setDisable(false);
        }

        String properName = currentCard.getName();
        properName = properName.toLowerCase().replace("_", " ");
        assetNameLabel.setText(properName);
        assetImageView.setImage(new Image(imageDirectory + currentCard.getImageLocation()));
        if (currentCard instanceof AnimalCard) {
            ageLabel.setText("Berat: " + String.valueOf(((AnimalCard)currentCard).getCurrentWeight()) + " (Asli: " + String.valueOf(((AnimalCard)currentCard).getNumber()) + ")");
        } else if (currentCard instanceof PlantCard) {
            ageLabel.setText("Turns: " + String.valueOf(((PlantCard)currentCard).getCurrentTotalTurns()) + " (Asli: " + String.valueOf(((PlantCard)currentCard).getNumber()) + ")");
        } else {
            ageLabel.setText("");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Item aktif:\n");
        sb.append("Accelerate: " + currentCard.getTotalAccelerate() + "\n");
        sb.append("Delay: " + currentCard.getTotalDelay() + "\n");

        sb.append("Trap: ");
        if (currentCard.isTrapSet()) {
            sb.append("Terpasang\n");
        } else {
            sb.append("Tidak terpasang\n");
        }

        sb.append("Proteksi: ");
        if (currentCard.isProtectedFromBear()) {
            sb.append("Terpasang\n");
        } else {
            sb.append("Tidak terpasang\n");
        }

        itemAktifLabel.setText(sb.toString());
    }

    @FXML
    public void panenKartu(MouseEvent event) {
        if (currentCard.canHarvest() && ladangku && Game.getInstance().getCurrentPlayer().getDeck().isActiveDeckAvailable()) {
            Game.getInstance().getCurrentPlayer().getDeck().addCardToActiveDeck(currentCard.getHarvestProduct());
            currentCardSlot.popCard();
            System.out.println("Berhasil panen");
            onClickBackButtonDialogBox(null);
        } else {
            System.out.println("Harusnya ga bisa di harvest");
        }
    }

    @FXML
    public void onClickBackButtonDialogBox(MouseEvent event) {
        if (dialog != null) {
            dialog.close();
        }
    }


}
