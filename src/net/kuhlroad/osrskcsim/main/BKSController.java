package net.kuhlroad.osrskcsim.main;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import net.kuhlroad.osrskcsim.assets.Boss;
import net.kuhlroad.osrskcsim.assets.BossListManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BKSController implements Initializable {

    private BossListManager bossListManager;

    public GridPane inputGridPane;
    public ChoiceBox bossChoiceBox;
    public TextField numKillsTextField;
    public Label killsFieldErrorLabel;
    public Button buttonSim;

    public FlowPane dropsFlowPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bossListManager = new BossListManager();
        ArrayList<String> bossNames = new ArrayList<String>();
        for (String key : bossListManager.getBossList().keySet()) {
            bossNames.add(bossListManager.getBossList().get(key).getName());
        }
        bossChoiceBox.getItems().addAll(bossNames);
        bossChoiceBox.getSelectionModel().select("Abyssal Sire");
    }

    public void ButtonSimClicked(ActionEvent actionEvent) {
        // Clear the previous result
        dropsFlowPane.getChildren().clear();
        //Store boss name
        String bossName = (String) bossChoiceBox.getValue();
        Boss boss = bossListManager.getBossList().get(bossName);

        try {
            //Store number of kills
            int numKills = Integer.parseInt(numKillsTextField.getText());

            Label message = new Label("Killing " + bossName + " " + numKills + " times: ");
            message.setPadding(new Insets(4, 4, 4, 4));
            dropsFlowPane.getChildren().add(message);

            for (int i=0;i<numKills;i++) {

            }
        } catch (NumberFormatException e) {
            killsFieldErrorLabel.setText("Kills amount must be numeric.");
        }
    }
}
