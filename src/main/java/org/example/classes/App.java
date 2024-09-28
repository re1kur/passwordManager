package org.example.classes;

import org.example.handlers.DataBaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws NullPointerException, IOException {
        DataBaseHandler.createTables();
        InputStream getIcon = getClass().getResourceAsStream("/icon.jpg");
        Image icon = new Image(getIcon);
        primaryStage.getIcons().add(icon);
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainScene.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

