package net.kuhlroad.osrskcsim.main;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import net.kuhlroad.osrskcsim.assets.Boss;
import net.kuhlroad.osrskcsim.assets.BossListManager;
import net.kuhlroad.osrskcsim.assets.Item;

import java.net.URL;
import java.util.*;

/**
 * Controller class for the Boss killcount simulator (BKS) pane
 * @date 2020.11.08
 */
public class BKSController implements Initializable {

    private BossListManager bossListManager;

    // User inputs
    public GridPane inputGridPane;
    public ChoiceBox<String> bossChoiceBox;
    public TextField numKillsTextField;
    public Label killsFieldErrorLabel;
    public Button buttonSim;
    public Label simulationPreTextLabel;

    // Item table
    public TableView itemTableView;
    public TableColumn<Boss, String> itemColumn;
    public TableColumn<Boss, Integer> rateColumn;
    public TableColumn<Boss, Boolean> includeColumn;

    public FlowPane dropsFlowPane;

    /**
     * Executes when this pane is invoked
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bossListManager = new BossListManager();

        // Add boss names to ChoiceBox
        ArrayList<String> bossNames = new ArrayList<String>();
        for (String key : bossListManager.getBossList().keySet()) {
            bossNames.add(bossListManager.getBossList().get(key).getName());
        }
        bossChoiceBox.getItems().addAll(bossNames);

        // Associate table columns with Item's instance fields
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("dropRate"));
        includeColumn.setCellFactory(CheckBoxTableCell.forTableColumn(includeColumn)); // Custom checkbox column, updates associated object's field accordingly.
        includeColumn.setCellValueFactory(new PropertyValueFactory<>("include"));

        // Set up itemTableView to listen to bossChoiceBox's selection, and set the default selection to "Vorkath"
        bossChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (v, oldVal, newVal) -> {
                    //killsFieldErrorLabel.setText("");
                    itemTableView.getItems().clear();
                    Boss newBoss = bossListManager.getBossList().get(newVal);
                    updateItemTableView(newBoss);
                });
        bossChoiceBox.getSelectionModel().select("Vorkath");

        // Make button disable when numKills TextField is empty
        BooleanBinding textIsEmpty = Bindings.createBooleanBinding(() -> numKillsTextField.getText().trim().isEmpty(), numKillsTextField.textProperty());
        buttonSim.disableProperty().bind(textIsEmpty);
    }

    public void updateItemTableView(Boss boss) {
        for(Item item : boss.getItems()) {
            itemTableView.getItems().add(item);
        }
    }

    /**
     * Executes when a key is pressed when the numKills text field is focused
     * @param keyEvent  The key event
     */
    public void KeyPressedInNumKillsTextField(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            simulateKills();
        }
    }

    /**
     * Executes when the simulate button is clicked in the GUI
     * @param actionEvent The button event
     */
    public void ButtonSimClicked(ActionEvent actionEvent) {
        simulateKills();
    }

    /**
     * Simulate the kills/item drops
     */
    public void simulateKills() {
        // Clear the previous result
        dropsFlowPane.getChildren().clear();
        killsFieldErrorLabel.setText("");

        // Store the selected boss name, the Boss associated with that name, and that Boss' ArrayList of Items
        String bossName = (String) bossChoiceBox.getValue();
        Boss boss = bossListManager.getBossList().get(bossName);
        ArrayList<Item> items = new ArrayList<Item>();
        for (Item item : boss.getItems()) {
            if (item.isIncluded())
                items.add(item);
        }

        // Store the number of kills entered, if the entry is numeric
        int numKills = 0;
        try {
            numKills = Integer.parseInt(numKillsTextField.getText());
        } catch (NumberFormatException e) {
            killsFieldErrorLabel.setText(numKillsTextField.getText() +  " is not numeric.");
        }

        // State what is about to be done, under the inputs and above the output
        if (numKills > 0) {
            simulationPreTextLabel.setText("Killing " + bossName + " " + numKills + " times: ");

            /*
        Generate int[] named rates, containing the drop rates of each item. This is to create an collection to
        easily find the greatest common denominator or least common multiple of the drop rates.
         */
            int[] rates = new int[items.size()];
            int i = 0;
            for (Item item : items) {
                rates[i++] = item.getDropRate();
            }

        /*
        Generate HashMap named dropTable, containing item names mapped to an Integer[] with
            dropTable[0] being the low bound
            dropTable[1] being the high bound
         */
            int lowBound = 0, highBound = 0;
            int commonDenominator = lcm(rates);
            HashMap<String, Integer[]> dropTable = new HashMap<String, Integer[]>();
            for (Item item : items) {
                lowBound += highBound;
                highBound = lowBound + (commonDenominator / item.getDropRate());

                dropTable.put(item.getName(), new Integer[]{lowBound, highBound});
            }

        /*
        Simulate the kills. For each kill, generate a random int and check if it's in each item's bounds.
         */
            Random r = new Random();
            for (int killNum=1;killNum<=numKills;killNum++) {
                for (String item : dropTable.keySet()) {
                    int roll = r.nextInt(commonDenominator);
                    if (roll >= dropTable.get(item)[0] &&  roll < dropTable.get(item)[1]) {
                        Label message = new Label("Kill " + killNum + ": " + item);
                        message.setPadding(new Insets(4, 4, 4, 4));
                        dropsFlowPane.getChildren().add(message);
                    }
                }
            }
        }
    }

    /**
     * Function to find the greatest common denominator of two integers
     * @param a The first integer
     * @param b The second integer
     * @return The GCD of the two integers
     */
    static int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    /**
     * Function to find the greatest common denominator of an array of integers
     * @param arr The array of integers
     * @return The GDC of the integers
     */
    static int gcd2(int[] arr) {
        int result = 0;
        for (int element : arr) {
            result = gcd(result, element);
            if (result == 1)
                return 1;
        }
        return result;
    }

    /**
     * Function to find the least common multiple of an array of integers
     * @param numbers the array of integers
     * @return The LCM of the integers
     */
    public static int lcm(int... numbers) {
        return Arrays.stream(numbers).reduce(1, (x, y) -> x * (y / gcd2(new int[] {x, y})));
    }
}
