module com.resources.logic {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;

    exports com.resources.logic;
    exports com.resources.logic.product;
    exports com.resources.logic.plugin;
    exports com.resources.logic.plugin.json;

}
