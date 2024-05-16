module com.resources.gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.resources.gui to javafx.fxml;
    exports com.resources.gui;
}