package com.resources.gui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoadController {
    private Stage load;

    public void setLoad(Stage loadStage) {
        this.load = loadStage;
    }

    @FXML
    public void onClickBackButtonLoadBox(MouseEvent event) {
        if (load != null) {
            load.close();
        }
    }
}