package com.resources.gui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ShopController { // Assuming you have a VBox in your FXML file to hold the labels
    private Stage shop;

    public void setShop(Stage shopStage) {
        this.shop = shopStage;

    }

    @FXML
    public void onClickBackButtonShopBox(MouseEvent event) {
        if (shop != null) {
            shop.close();
        }
    } 
}
