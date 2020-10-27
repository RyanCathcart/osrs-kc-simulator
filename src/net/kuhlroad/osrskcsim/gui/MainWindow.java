package net.kuhlroad.osrskcsim.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A class for the main GUI window for the OSRS Kill Count Simulator. Contains an toolbar at the top and a main content
 * s
 */
public class MainWindow extends Application {

    /**
     * Executes when the Application starts
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Boney's Kill Count Simulator");
        stage.setWidth(800);
        stage.setHeight(600);
        VBox root = new VBox();

        HBox toolbar = new HBox();
        int buttonHeight = 40;
        int btnCount = 4;
        toolbar.setAlignment(Pos.TOP_CENTER);
        toolbar.setMaxHeight(buttonHeight);

        Button buttonBKS = new Button("Boss Kill Sim");
        buttonBKS.setPrefHeight(buttonHeight);
        buttonBKS.prefWidthProperty().bind(toolbar.widthProperty().divide(btnCount));

        Button buttonGIS = new Button("Get Item Sim");
        buttonGIS.setPrefHeight(buttonHeight);
        buttonGIS.prefWidthProperty().bind(toolbar.widthProperty().divide(btnCount));

        Button buttonICC = new Button("Item Chance Calc");
        buttonICC.setPrefHeight(buttonHeight);
        buttonICC.prefWidthProperty().bind(toolbar.widthProperty().divide(btnCount));

        Button buttonAbout = new Button("About");
        buttonAbout.setPrefHeight(buttonHeight);
        buttonAbout.prefWidthProperty().bind(toolbar.widthProperty().divide(btnCount));

        toolbar.getChildren().addAll(buttonBKS, buttonGIS, buttonICC, buttonAbout);

        StackPane mainContentPane = new StackPane();

        ScrollPane paneBKS = new ScrollPane();
        Image vorkath = new Image("https://oldschool.runescape.wiki/images/thumb/9/9a/Vorkath.png/1200px-Vorkath.png?1ce3f");
        paneBKS.setContent(new ImageView(vorkath));
        mainContentPane.getChildren().add(paneBKS);

        root.getChildren().addAll(toolbar, mainContentPane);
        root.setVgrow(toolbar, Priority.ALWAYS);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }
}
