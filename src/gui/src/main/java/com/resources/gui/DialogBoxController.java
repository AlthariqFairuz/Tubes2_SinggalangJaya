package com.resources.gui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;;

public class DialogBoxController {
    private Stage dialog;

    @FXML
    private Label assetNameLabel;

    public void setDialog(Stage dialog) {
        this.dialog = dialog;
    }

    public void setAssetName(String assetName) {
        assetNameLabel.setText(assetName);
    }

    @FXML
    public void onClickBackButtonDialogBox(MouseEvent event) {
        if (dialog != null) {
            dialog.close();
        }
    }
}
