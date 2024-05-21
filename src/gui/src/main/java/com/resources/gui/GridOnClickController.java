package com.resources.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GridOnClickController {
    @FXML
    private ImageView myImageView;
    private static TextField textArea;
    private static Timer timer;

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
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialize(ActionEvent event, String gold) {

        // Initialize the text area with some initial text
        GridOnClickController.updateAutomatically(gold);
    }

    public static void updateAutomatically(String newGold) {
        // Start updating the text area every 2 seconds
        stopUpdating();
        if (newGold != null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    // Update the text area every 2 seconds
                    updateTextArea(newGold);
                }
            }, 0, 2000);
        }
    }

    private static void stopUpdating() {
        // Stop the timer if running
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    private static void updateTextArea(String newGold) {
        // Update the text area with the new value
        textArea.setText(newGold);
    }
}
