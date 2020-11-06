package net.kuhlroad.osrskcsim.main;

import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BKSController implements Initializable {

    public ChoiceBox bossChoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bossChoiceBox.getItems().addAll("Vorkath");
        bossChoiceBox.getSelectionModel().select("Vorkath");
    }
}
