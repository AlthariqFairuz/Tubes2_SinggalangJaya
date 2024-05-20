module com.resources.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.resources.logic;


    opens com.resources.gui to javafx.fxml;
    exports com.resources.gui;
}