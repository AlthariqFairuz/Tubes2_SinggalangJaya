package com.resources.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.Timer;
import java.util.TimerTask;

public class Player1GoldController {

    @FXML
    private TextArea textArea;

    private Timer timer;

    @FXML
    public void initialize() {
        // Initialize the text area with some initial text
        textArea.setText("0");
    }

    @FXML
    public void updateAutomatically(String newGold) {
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

    @FXML
    public void updateManually(String newGold) {
        // Stop automatic updates if running
        stopUpdating();

        // Manually update the text area
        updateTextArea(newGold);
    }

    private void stopUpdating() {
        // Stop the timer if running
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    private void updateTextArea(String newGold) {
        // Update the text area with the new value
        textArea.setText(newGold);
    }
}
