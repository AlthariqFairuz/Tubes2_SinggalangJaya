package com.resources.gui;

import java.util.ArrayList;
import java.util.List;

import com.resources.logic.plugin.PluginLoader;
import com.resources.logic.state.TextLoaderSaver;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SaveController {
    private Stage save;

    @FXML
    private TextField savTextField;
    @FXML
    private ComboBox saveMenuButton;


    public void setSave(Stage saveStage) {
        this.save = saveStage;
            List<String> options = new ArrayList<String>();
            options.add("txt");
            for (String plugin : PluginLoader.getInstance().getPlugins()) {
                options.add(plugin.toLowerCase());
            }
            saveMenuButton.getItems().addAll(options);

    }

    @FXML
    public void onClickBackButtonSaveBox(MouseEvent event) {
        if (save != null) {
            save.close();
        }
    }
    
    @FXML
    public void onSaveButtonClicked(MouseEvent event) {
         // Load the game
        if (savTextField.getText().isEmpty()) {
            return;
        }
        String path = "../state/" + savTextField.getText() + "." + saveMenuButton.getSelectionModel().getSelectedItem().toString().toLowerCase();
        String choice = saveMenuButton.getSelectionModel().getSelectedItem().toString().toLowerCase();
        if (choice.equals("txt")) {
            TextLoaderSaver instance = new TextLoaderSaver();
            instance.saveState(path);
        }
        else {
            PluginLoader.getInstance().getPlugin(choice).saveState(path);
        }
    }
}

