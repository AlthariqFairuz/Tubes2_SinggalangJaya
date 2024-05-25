package com.resources.gui;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.resources.logic.Shop;
import com.resources.logic.ShopItem;
import com.resources.logic.product.ProductCard;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShopController { // Assuming you have a VBox in your FXML file to hold the labels
    private Stage shop;
    @FXML
    private VBox listShopItem;
    @FXML
    private List<ShopItem> shopItems;

    public void setShop(Stage shopStage) {
        this.shop = shopStage;
        loadShopItems();

    }

    private void loadShopItems () {
        Shop instance = Shop.getInstance();
        this.shopItems = instance.getShopItems();
        if (this.shopItems == null || this.shopItems.isEmpty()) {
            Label noItems = new Label("No items available");
            noItems.setStyle("-fx-font-size: 20px; -fx-allignment: center; -fx-font-weight: bold;");
            listShopItem.getChildren().add(noItems);
            return;
        }
        listShopItem.getChildren().clear();
        
        for (ShopItem item : shopItems) {
            // Create a container for each shop item (e.g., HBox)
            HBox itemBox = new HBox(10); // HBox with spacing 10
            
            // Create an ImageView for the item's image
            Image itemImage = new Image(item.getItem().getImageLocation());
            ImageView itemImageView = new ImageView(itemImage);
            itemImageView.setFitHeight(50); // Set the desired height
            itemImageView.setFitWidth(50);  // Set the desired width
            itemImageView.setPreserveRatio(true); // Preserve the aspect ratio
            Button buyButton = new Button("Buy");
            buyButton.setStyle("-fx-background-color: #00ff00; -fx-text-fill: #000000;");

            // Create Labels for the item's name and price
            Label priceLabel = new Label(item.getPrice() + " gold");
            priceLabel.setStyle("-fx-font-weight: bold;");
            Label quantityLabel = new Label("Quantity: " + item.getFrequency());
            quantityLabel.setStyle("-fx-font-style: italic;");
            buyButton.setOnAction(e -> {
                if (instance.buyShopItem(item.getItem().getName())) {
                    loadShopItems();
                }
            });


            // Add the ImageView and Labels to the HBox
            itemBox.setStyle("-fx-padding: 10px; -fx-border-color: black; -fx-border-width: 1px; -fx-margin: 10px;");
            itemBox.getChildren().addAll(itemImageView, priceLabel, quantityLabel, buyButton);

            // Add the HBox to the VBox
            listShopItem.getChildren().add(itemBox);
        }
    }

    @FXML
    public void onClickBackButtonShopBox(MouseEvent event) {
        if (shop != null) {
            shop.close();
        }
    } 
}
