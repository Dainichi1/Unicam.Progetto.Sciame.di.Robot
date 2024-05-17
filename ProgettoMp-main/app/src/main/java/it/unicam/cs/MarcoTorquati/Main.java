package it.unicam.cs.MarcoTorquati;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

import java.io.*;
import java.util.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/fxml/RobotSimulation.fxml")));
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));
        this.configureStage(primaryStage);
        RobotSimulation controller = loader.getController();
        controller.setExitConfiguration(primaryStage);
        primaryStage.show();
    }

    private void configureStage(Stage stage) {
        stage.setTitle("Simulazione Sciame di Robot");
        stage.setResizable(false);
    }
    public static void main(String[] args) {
        launch(args);
    }
}