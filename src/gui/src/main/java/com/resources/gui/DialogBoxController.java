package com.resources.gui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DialogBoxController {
    private Stage dialog;

    public void setDialog(Stage dialog) {
        this.dialog = dialog;
    }

    @FXML
    public void onClickBackButtonDialogBox(MouseEvent event) {
        if (dialog != null) {
            dialog.close();
        }
    }
}
