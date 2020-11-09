package net.kuhlroad.osrskcsim.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public ScrollPane contentPane;
    public Button buttonBKS;
    public Button buttonGIS;
    public Button buttonICC;
    public Button buttonHome;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPage("Home.fxml");

        buttonHome.setDisable(true);
        buttonBKS.setDisable(false);
        buttonGIS.setDisable(false);
        buttonICC.setDisable(false);
    }

    public void buttonHomeClicked(ActionEvent event) throws Exception {
        setPage("Home.fxml");
        buttonHome.setDisable(true);
        buttonBKS.setDisable(false);
        buttonGIS.setDisable(false);
        buttonICC.setDisable(false);
    }

    public void buttonBKSClicked(ActionEvent event) throws Exception {
        setPage("BKS.fxml");
        buttonHome.setDisable(false);
        buttonBKS.setDisable(true);
        buttonGIS.setDisable(false);
        buttonICC.setDisable(false);
    }

    public void buttonGISClicked(ActionEvent event) throws Exception {
        setPage("GIS.fxml");
        buttonHome.setDisable(false);
        buttonBKS.setDisable(false);
        buttonGIS.setDisable(true);
        buttonICC.setDisable(false);
    }

    public void buttonICCClicked(ActionEvent event) throws Exception {
        setPage("ICC.fxml");
        buttonHome.setDisable(false);
        buttonBKS.setDisable(false);
        buttonGIS.setDisable(false);
        buttonICC.setDisable(true);
    }

    /**
     * Set the current pane to be displayed in the main content pane of the application. Searches the assets folder
     * for the correct filename. Example input: "file.fxml"
     * @param filename The name of the .fxml file to be used as the content source
     */
    public void setPage(String filename) {
        try {
            URL fileURL = MainWindowController.class.getResource("/net/kuhlroad/osrskcsim/gui/" + filename);

            if (fileURL == null)
                throw new java.io.FileNotFoundException("FXML file can't be found");

            contentPane.setContent(new FXMLLoader().load(fileURL));
        } catch (Exception e) {
            System.out.println("Page " + filename + " not found.");
        }
    }
}
