    package com.resources.gui;

    import java.io.File;
    import java.util.ArrayList;
    import java.util.List;
    import javafx.fxml.FXML;
    import javafx.scene.control.Label;
    import javafx.scene.input.MouseEvent;
    import javafx.stage.FileChooser;
    import javafx.stage.Stage;
    import com.resources.logic.plugin.*;

    public class PluginController {
        private Stage plugin;
        private File selectedFile; 
        @FXML
        private Label fileName;
        private String path;
        private List<Plugin> plugins = new ArrayList<Plugin>();

        public void setPlugin(Stage plugin) {
            this.plugin = plugin;

        }

        @FXML
        public void onClickBackButtonPluginBox(MouseEvent event) {
            if (plugin != null) {
                plugin.close();
            }
        }

        @FXML
        public void onPluginButtonClicked(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select JAR File");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("JAR Files", "*.jar")
        );

        // Show the file chooser dialog using the plugin stage
        selectedFile = fileChooser.showOpenDialog(plugin);

        if (selectedFile != null) {
            // Print the name of the selected JAR file
            fileName.setText(selectedFile.getAbsolutePath());
            path = selectedFile.getAbsolutePath();

            // Here you can perform any operations with the selected JAR file
        } else {
            // Set text to indicate no file selected
            fileName.setText("No file selected.");
        }
    }

    // Method to get the selected JAR file
    public File getSelectedFile() {
        return selectedFile;
    }

    // Load the selected JAR file
    public void onUploadButtonClicked(MouseEvent event) {
        if (selectedFile != null) {
            // Load the selected JAR file using the PluginLoader class
            PluginLoader pluginLoader = new PluginLoader();
            plugins.add(pluginLoader.loadPlugin(path));
            
        }
        else {
            // Display an error message if no file is selected
            fileName.setText("No file selected.");
        }
    }
}
