package com.resources.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.resources.gui.DialogBoxController;

import java.io.IOException;
import java.util.Objects;

public class GridOnClickController {
    @FXML
    private ImageView myImageView;
    @FXML
    private Label myLabel;

    public void onGridOnClick(MouseEvent event) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogbox.fxml"));

            // Create the dialog stage
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initStyle(StageStyle.TRANSPARENT);
            dialogStage.setScene(new Scene(loader.load()));

            // Get the controller and set the dialog reference
            DialogBoxController controller = loader.getController();
            controller.setDialog(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            if(event.getClickCount() == 2) {
                dialogStage.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
