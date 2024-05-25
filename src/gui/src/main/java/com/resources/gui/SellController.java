package com.resources.gui;

import java.util.ArrayList;
import java.util.List;

import com.resources.logic.Card;
import com.resources.logic.CardSlot;
import com.resources.logic.Game;
import com.resources.logic.Shop;
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

public class SellController {

    private Stage sell;
    @FXML
    private VBox listSellItem;
    @FXML
    private List<ProductCard> sellItems = new ArrayList<ProductCard>() {
        
    };

    public void setSell(Stage sellStage) {
        this.sell = sellStage;
        loadSellItems();
        
    }

    public void loadSellItems() {

        Shop instance = Shop.getInstance();
        CardSlot[] itemCanBeSold =  Game.getInstance().getCurrentPlayer().getDeck().getActiveCards();
        for (CardSlot cardSlot : itemCanBeSold) {
            if (cardSlot.getCard() != null && cardSlot.getCard() instanceof ProductCard) {
                Card card = cardSlot.getCard();
                ProductCard productCard = (ProductCard) card;
                sellItems.add(productCard);
            }
        }
        if (this.sellItems == null || this.sellItems.isEmpty()) {
            Label noItems = new Label("No items available");
            noItems.setStyle("-fx-font-size: 20px; -fx-allignment: center; -fx-font-weight: bold;");
            listSellItem.getChildren().add(noItems);
            return;
        }
        listSellItem.getChildren().clear();
        
        for (ProductCard item : sellItems) {
            // Create a container for each shop item (e.g., HBox)
            HBox itemBox = new HBox(10); // HBox with spacing 10
            
            // Create an ImageView for the item's image
            Image itemImage = new Image(HomeController.imageDirectory + item.getImageLocation());
            ImageView itemImageView = new ImageView(itemImage);
            if (itemImageView != null){
                itemImageView.setFitHeight(50); // Set the desired height
                itemImageView.setFitWidth(50);  // Set the desired width
                itemImageView.setPreserveRatio(true); // Preserve the aspect ratio
            }
            Button sellButton = new Button("Sell");
            sellButton.setStyle("-fx-background-color: #9336B4; -fx-text-fill: #000000;");

            // Create Labels for the item's name and price
            Label itemName = new Label(item.getName());
            itemName.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
            Label itemPrice = new Label("Price: " + item.getPrice());
            itemPrice.setStyle("-fx-font-size: 15px;");

            // Add the ImageView, Labels, and Button to the HBox
            itemBox.getChildren().addAll(itemImageView, itemName, itemPrice, sellButton);
            listSellItem.getChildren().add(itemBox);
            
            // Set the action for the sell button
            sellButton.setOnMouseClicked(e -> {
                instance.sellShopItem(item);
                loadSellItems();
            });
        }
    }

    @FXML
    public void onClickBackButtonSellBox(MouseEvent event) {
        if (sell != null) {
            sell.close();
        }
    }
    
}
