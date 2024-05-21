package com.resources.gui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ShopController {
    private Stage shop;

    public void setShop(Stage shop) {
        this.shop = shop;
    }

    @FXML
    public void onClickBackButtonShopBox(MouseEvent event) {
        if (shop != null) {
            shop.close();
        }
    }
    
}
