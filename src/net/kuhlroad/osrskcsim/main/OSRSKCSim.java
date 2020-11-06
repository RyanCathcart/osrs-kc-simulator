package net.kuhlroad.osrskcsim.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * A class for the main GUI window for the OSRS Kill Count Simulator. Has a toolbar at the bottom and a main content
 * pane at the top.
 * @author Ryan Cathcart
 * @version 2020.11.05
 */
public class OSRSKCSim extends Application {

    /**
     * Executes when the Application starts
     * @param window The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("OSRS KC Simulator");
        window.setResizable(false);
        StackPane contentStack = new StackPane();
        Parent home = FXMLLoader.load(getClass().getResource("../gui/Home.fxml"));

        Scene sceneHome = new Scene(home);
        window.setScene(sceneHome);
        window.show();
    }

    /**
     * Main method. Launches application.
     * @param args Main arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
