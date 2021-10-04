package com.example.quizapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage currentStage;

    @Override
    public void start(Stage stage) throws IOException {
        currentStage = stage;
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("welcome-view.fxml"));

        stage.setTitle("Squizh Game");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void switchScene(String sceneView) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(sceneView));
        currentStage.getScene().setRoot(pane);
    }
}