package com.ismaillagouilly.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class JavaCodingGame extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        setRoot("welcomeScreen"); // Load the welcome view initially
        primaryStage.setTitle("Welcome to the Java Coding Game");
        primaryStage.show();
    }

    public static void setRoot(String fxml) {
        try {
            String path = "/com/ismaillagouilly/views/" + fxml + ".fxml";
            URL resource = JavaCodingGame.class.getResource(path);

            if (resource == null) {
                throw new IOException("FXML file not found: " + path);
            }

            FXMLLoader fxmlLoader = new FXMLLoader(resource);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root, 800, 600));
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
