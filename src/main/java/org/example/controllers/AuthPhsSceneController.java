package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.handlers.DataBaseHandler;
import org.example.handlers.Handler;

import java.io.IOException;

public class AuthPhsSceneController {

    @FXML
    private AnchorPane authCheckSeedPhsScene;

    @FXML
    private TextField enteredSeed;

    @FXML
    private Label errorLabel;

    @FXML
    private Label seedNumber;

    @FXML
    void backToAuth(ActionEvent event) {
        Stage stage = (Stage) authCheckSeedPhsScene.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/authScene.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
    }

    @FXML
    void finallyAuth(ActionEvent event) {
        String seed = enteredSeed.getText();
        DataBaseHandler dbh = new DataBaseHandler();
        if (!dbh.logInUser(Handler.getCurrentLogin(),
                seed, Handler.getSeedNumber()).equals(true)) {
            errorLabel.setVisible(true);
            return;
        }
        System.out.println("YOU FINALLY LOGIN");
        Stage stage = (Stage) authCheckSeedPhsScene.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/app.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
    }
    @FXML
    public void initialize(){
        seedNumber.setText(
                "Enter the seed phrase with number "
                        + Handler.getRandInt()
        );
    }

}
