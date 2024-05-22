package com.resources.gui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PluginController {
      private Stage plugin;

    public void setPlugin(Stage plugin) {
        this.plugin = plugin;

    }

    @FXML
    public void onClickBackButtonPluginBox(MouseEvent event) {
        if (plugin != null) {
            plugin.close();
        }
    }
}
