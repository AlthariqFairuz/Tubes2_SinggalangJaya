module com.resources.logic {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    requires com.google.gson;

    exports com.resources.logic;
    exports com.resources.logic.product;
    exports com.resources.logic.plugin;
    exports com.resources.logic.plugin.json;
    exports com.resources.logic.state;
    exports com.resources.logic.item;
    exports com.resources.logic.animal;
    exports com.resources.logic.plant;

}