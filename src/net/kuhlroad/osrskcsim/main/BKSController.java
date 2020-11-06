package net.kuhlroad.osrskcsim.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BKSController implements Initializable {

    public Button buttonBKS;
    public Button buttonGIS;
    public Button buttonICC;
    public Button buttonHome;

    public ChoiceBox bossChoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bossChoiceBox.getItems().addAll("Vorkath");
        bossChoiceBox.getSelectionModel().select("Vorkath");
    }

    public void buttonBKSClicked(ActionEvent event) throws Exception{
        Parent parentBKS = FXMLLoader.load(getClass().getResource("../gui/BKS.fxml"));
        Scene sceneBKS = new Scene(parentBKS);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(sceneBKS);
        window.show();
    }

    public void buttonGISClicked(ActionEvent event) throws Exception{
        Parent parentGIS = FXMLLoader.load(getClass().getResource("../gui/GIS.fxml"));
        Scene sceneGIS = new Scene(parentGIS);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(sceneGIS);
        window.show();
    }

    public void buttonICCClicked(ActionEvent event) throws Exception{
        Parent parentICC = FXMLLoader.load(getClass().getResource("../gui/ICC.fxml"));
        Scene sceneICC = new Scene(parentICC);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(sceneICC);
        window.show();
    }

    public void buttonHomeClicked(ActionEvent event) throws Exception{
        Parent parentHome = FXMLLoader.load(getClass().getResource("../gui/Home.fxml"));
        Scene sceneHome = new Scene(parentHome);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(sceneHome);
        window.show();
    }
}
