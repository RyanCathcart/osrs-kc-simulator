import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class KCSimulator extends Application {

    /**
     * Executes when the Application starts
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Boney's Kill Count Simulator");
        stage.setWidth(400);
        stage.setHeight(500);

        VBox root = new VBox();

        ImageView imageView = new ImageView("https://oldschool.runescape.wiki/images/thumb/9/9a/Vorkath.png/280px-Vorkath.png?1ce3f");
        root.getChildren().addAll(imageView);


        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }
}
