package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Main class is used to launch the program and open the loginForm.fxml first.
 * @author Mary Williams
 * @version 1
 */
public class Main extends Application {
    /**
     * The start method starts the program by showing loginForm.fxml.
     * @param primaryStage The Stage to set at the beginning of the program execution.
     * @exception IOException Failed to load loginForm.fxml.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/loginForm.fxml"));
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle("Login Form");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * The main method starts the program.
     * @param args String array used by main.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
