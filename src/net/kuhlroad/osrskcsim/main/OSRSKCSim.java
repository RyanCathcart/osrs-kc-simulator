package net.kuhlroad.osrskcsim.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        window.getIcons().add(new Image(getClass().getResourceAsStream("../assets/osrs-slayer-wiki-icon.png")));
        window.setTitle("OSRS Killcount Simulator");

        Parent home = FXMLLoader.load(getClass().getResource("../gui/MainWindow.fxml"));

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
