package com.resources.logic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class dragNDrop2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        int rows = 2;
        int columns = 2;

        // Create a 2x2 grid of rectangles
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Rectangle rect = createDraggableRectangle("Rect" + row + col);
                gridPane.add(rect, col, row);
            }
        }

        Scene scene = new Scene(gridPane, 300, 300);
        primaryStage.setTitle("Drag and Drop Minimal Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Rectangle createDraggableRectangle(String name) {
        Rectangle rect = new Rectangle(90, 90, Color.LIGHTBLUE);
        rect.setStroke(Color.BLACK);
        rect.setUserData(name); // Store the name in the rectangle's user data

        rect.setOnDragDetected(event -> {
            Dragboard db = rect.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString((String) rect.getUserData());
            db.setContent(content);

            event.consume();
        });

        rect.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                String name2 = db.getString();
                System.out.println("Dropped: " + name2);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        return rect;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
