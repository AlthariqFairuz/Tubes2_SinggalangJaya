package com.resources.gui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SaveController {
    private Stage save;

    public void setSave(Stage saveStage) {
        this.save = saveStage;

    }

    @FXML
    public void onClickBackButtonSaveBox(MouseEvent event) {
        if (save != null) {
            save.close();
        }
    }
    
    @FXML
    public void onSaveButtonClicked(MouseEvent event) {
        // Save the game
    }
}
