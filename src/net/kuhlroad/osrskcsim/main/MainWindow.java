package net.kuhlroad.osrskcsim.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A class for the main GUI window for the OSRS Kill Count Simulator. Contains an toolbar at the top and a main content
 *
 */
public class MainWindow extends Application {

    /**
     * Executes when the Application starts
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("OSRS KC Simulator");
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));

        Scene scene = new Scene(home);
        stage.setScene(scene);
        stage.show();
    }
}
