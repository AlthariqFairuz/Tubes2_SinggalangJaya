package com.resources.gui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class DialogBoxController {
    private Stage dialog;

    @FXML
    private Label assetNameLabel, ageLabel;

    @FXML
    private ImageView assetImageView;

    public void setDialog(Stage dialog) {
        this.dialog = dialog;
    }

    public void setAssetName(String assetName) {
        assetNameLabel.setText(assetName);
    }

    public void setAssetImage(String imageURL) {
        Image image = new Image("@Empty.png");
        assetImageView.setImage(image);
    }

    public void setAgeLabel(int ageCard) {
        ageLabel.setText("Umur: " + String.valueOf(ageCard));
    }


    @FXML
    public void onClickBackButtonDialogBox(MouseEvent event) {
        if (dialog != null) {
            dialog.close();
        }
    }
}
