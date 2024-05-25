package com.resources.gui;

import com.resources.logic.plugin.PluginLoader;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SaveController {
    private Stage save;

    @FXML
    private TextField savTextField;
    @FXML
    private MenuButton saveMenuButton;


    public void setSave(Stage saveStage) {
        this.save = saveStage;
        if (PluginLoader.getInstance().getPlugins().isEmpty()) {
            saveMenuButton.setDisable(true);
        }
        else {
            MenuItem json = new MenuItem("JSON");
            MenuItem xml = new MenuItem("XML");
            saveMenuButton.getItems().addAll(json, xml);
        }

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
