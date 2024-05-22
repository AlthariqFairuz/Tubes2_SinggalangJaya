package com.resources.gui;

import java.io.IOException;
import java.util.ArrayList;

import com.resources.logic.Shop;
import com.resources.logic.ShopItem;
import com.resources.logic.product.ProductCard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShopController {
    private VBox vbox; // Assuming you have a VBox in your FXML file to hold the labels
    private Stage shop;

    public void setShop(Stage shopStage) {
        this.shop = shopStage;
        Shop shop = Shop.getInstance();
        ProductCard productCard = new ProductCard("tes", null, 30);
        shop.sellShopItem(productCard);
        ArrayList<ShopItem> shopItems = shop.getShopItems();

        for (ShopItem item : shopItems) {
            try {
                HBox hbox = loadCustomLabel(item);
                vbox.getChildren().add(hbox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private HBox loadCustomLabel(ShopItem item) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("shopItem.fxml"));
        HBox hbox = loader.load();

        // Retrieve the label and image view from the loaded FXML
        Label itemLabel = (Label) hbox.lookup("#itemLabel");
        // ImageView itemImage = (ImageView) hbox.lookup("#itemImage");

        // Set the text of the label
        itemLabel.setText("Item: " + item.getItem().getName() + ", Frequency: " + item.getFrequency());

        // // Optionally set an image (replace "imagePath" with actual image path or URL)
        // String imagePath = item.getItem().getImagePath(); // Assuming your ProductCard has an image path
        // if (imagePath != null) {
        //     itemImage.setImage(new Image(imagePath));
        // }

        return hbox;
    }

    @FXML
    public void onClickBackButtonShopBox(MouseEvent event) {
        if (shop != null) {
            shop.close();
        }
    }
    
}
