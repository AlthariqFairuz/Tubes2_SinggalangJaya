    package com.resources.gui;

    import java.io.File;

    import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
    import javafx.stage.DirectoryChooser;
    import javafx.stage.Stage;

    public class PluginController {
        private Stage plugin;
        private File selectedDirectory; 
        private Label fileName;

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
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Folder");
    
            // Show the folder chooser dialog using the plugin stage
            selectedDirectory = directoryChooser.showDialog(plugin);
    
            if (selectedDirectory != null) {
                // Print the path of the selected folder
                fileName.setText(selectedDirectory.getAbsolutePath());
                
                // Here you can perform any operations with the selected folder
            } else {
                fileName.setText("No folder selected.");
            }
        }
    
        // Method to get the selected directory
        public File getSelectedDirectory() {
            return selectedDirectory;
        }
    }
