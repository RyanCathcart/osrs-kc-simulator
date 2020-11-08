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
import java.util.*;

public class BKSController implements Initializable {

    private BossListManager bossListManager;

    public GridPane inputGridPane;
    public ChoiceBox bossChoiceBox;
    public TextField numKillsTextField;
    public Label killsFieldErrorLabel;
    public Button buttonSim;
    public Label simulationPreTextLabel;

    public FlowPane dropsFlowPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bossListManager = new BossListManager();
        ArrayList<String> bossNames = new ArrayList<String>();
        for (String key : bossListManager.getBossList().keySet()) {
            bossNames.add(bossListManager.getBossList().get(key).getName());
        }
        bossChoiceBox.getItems().addAll(bossNames);
        bossChoiceBox.getSelectionModel().select("Vorkath");
    }

    public void ButtonSimClicked(ActionEvent actionEvent) {
        // Clear the previous result
        dropsFlowPane.getChildren().clear();
        // Store boss name
        String bossName = (String) bossChoiceBox.getValue();
        Boss boss = bossListManager.getBossList().get(bossName);

        try {
            // Store number of kills
            int numKills = Integer.parseInt(numKillsTextField.getText());

            // Create label stating what is about to be done
            simulationPreTextLabel.setText("Killing " + bossName + " " + numKills + " times: ");

            // Simulate the kills
            simulateKills(boss, numKills);
        } catch (NumberFormatException e) {
            killsFieldErrorLabel.setText(numKillsTextField.getText() +  " is not numeric.");
        }
    }

    /**
     * Simulate a specified number of kills on a specified Boss
     * @param boss      The Boss to 'kill'
     * @param numKills  The number of kills
     */
    public void simulateKills(Boss boss, int numKills) {
        HashMap<String, Integer> items =  boss.getItems();

        /*
        Generate int[] named rates, containing the drop rates of each item. This is to create an collection to
        easily find the greatest common denominator or least common multiple of the drop rates.
         */
        int[] rates = new int[items.values().size()];
        int i=0;
        for (int rate : items.values()) {
            rates[i++] = rate;
        }

        /*
        Generate HashMap named dropTable, containing item names mapped to an Integer[] with
            dropTable[0] being the low bound
            dropTable[1] being the high bound
         */
        int lowBound = 0, highBound = 0;
        int commonDenominator = lcm(rates);
        HashMap<String, Integer[]> dropTable = new HashMap<String, Integer[]>();
        for (String item : items.keySet()) {
            lowBound += highBound;
            highBound = lowBound + (commonDenominator / items.get(item));

            dropTable.put(item, new Integer[]{lowBound, highBound});
        }

        /*
            Simulate the kills. For each kill generate a random int and check if it's in each item's bounds.
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
    static int gcd2(int arr[]) {
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
