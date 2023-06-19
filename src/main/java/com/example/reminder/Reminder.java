package com.example.reminder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Reminder class is the entry point for the Reminder application.
 * It extends the JavaFX Application class and launches the application's graphical user interface.
 */
public class Reminder extends Application {

    /**
     * The start() method is called when the JavaFX application is started.
     * It loads the FXML file, creates a scene, and displays the main stage.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Reminder.class.getResource("reminder.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 370, 300);
        stage.setTitle("Reminder");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is the entry point for the Java application.
     * It launches the JavaFX application by calling the launch() method.
     *
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch();
    }
}
