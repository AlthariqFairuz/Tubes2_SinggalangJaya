package com.resources.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import com.resources.logic.plugin.PluginLoader;
import com.resources.logic.state.TextLoaderSaver;

public class LoadController { 
    private Stage load;

    @FXML
    private TextField loadTextField;
    @FXML
    private ComboBox loadMenuButton;

    public void setLoad(Stage loadStage) {
        this.load = loadStage;
    
            List<String> options = new ArrayList<String>();
            options.add("txt");
            for (String plugin : PluginLoader.getInstance().getPlugins()) {
                options.add(plugin.toLowerCase());
            }

            loadMenuButton.getItems().addAll(options);
    }

    @FXML
    public void onClickBackButtonLoadBox(MouseEvent event) {
        if (load != null) {
            load.close();
        }
        loadMenuButton.setPromptText("Select a file type");
    }

    @FXML
    public void onClickLoadButton(MouseEvent event) {
        // Load the game
        if (loadTextField.getText().isEmpty()) {
            return;
        }
        String path = "../state/" + loadTextField.getText() + "." + loadMenuButton.getSelectionModel().getSelectedItem().toString().toLowerCase();
        System.out.println(path);
        String choice = loadMenuButton.getSelectionModel().getSelectedItem().toString().toLowerCase();
        System.out.println(choice);
        if (choice.equals("txt")) {
            TextLoaderSaver instance = new TextLoaderSaver();
            instance.loadState(path);
        }
        else {
            PluginLoader.getInstance().getPlugin(choice).loadState(path);
        }
    }
}