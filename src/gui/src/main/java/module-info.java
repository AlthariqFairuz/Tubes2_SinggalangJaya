module com.resources.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.resources.logic;
    requires javafx.graphics;


    opens com.resources.gui to javafx.fxml;
    exports com.resources.gui;
}