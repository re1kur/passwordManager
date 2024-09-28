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

public class RegSceneController {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField logToReg;

    @FXML
    private AnchorPane regScene;

    @FXML
    void backToAuth(ActionEvent event) {
        Stage stage = (Stage) regScene.getScene().getWindow();
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
    void logToReg(ActionEvent event) {
        String login = logToReg.getText().trim();
        if (DataBaseHandler.checkLogin(login).equals(true)) {
            errorLabel.setText("This login is already registered");
            errorLabel.setVisible(true);
            return;
        }
        if (login.length() > 20) {
            errorLabel.setText("Too long login.");
            errorLabel.setVisible(true);
            return;
        }
        if (login.isEmpty()) {
            errorLabel.setText("Enter not empty field");
            errorLabel.setVisible(true);
            return;
        }
        Handler.setCurrentLogin(login);
        Stage stage = (Stage) regScene.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/seedPhsScene.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
    }

}
