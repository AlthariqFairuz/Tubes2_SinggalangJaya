package com.resources.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import java.io.IOException;
import java.util.Objects;

public class MainGUI extends Application {

    @FXML
    private TextField player1;
    private TextField player2;
    @Override
    public void start(Stage stage) throws IOException {

        // Set FXMLLoader as its root
        FXMLLoader fxmlLoader = new FXMLLoader(MainGUI.class.getResource("player1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        // Set title and icon
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Logo.jpg")));
        stage.getIcons().add(icon);
        stage.setTitle("Singgalang Jaya");

        // Import CSS
        String css = Objects.requireNonNull(this.getClass().getResource("style.css")).toExternalForm();
        scene.getStylesheets().add(css);

        // Set player name


        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}