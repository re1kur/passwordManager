package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.App;

import java.io.IOException;

public class RegController {
    @FXML
    private Label errorLabel;

    @FXML
    private Button backToAuthBtn;

    @FXML
    private TextField logToReg;

    @FXML
    private Button logToRegBtn;

    @FXML
    private AnchorPane regScene;
    @FXML
    private Button backToRegBtn;

    @FXML
    private Label enteredLogin;

    @FXML
    private Button regFinallyBtn;

    @FXML
    private Label seed1;

    @FXML
    private Label seed10;

    @FXML
    private Label seed2;

    @FXML
    private Label seed3;

    @FXML
    private Label seed4;

    @FXML
    private Label seed5;

    @FXML
    private Label seed6;

    @FXML
    private Label seed7;

    @FXML
    private Label seed8;

    @FXML
    private Label seed9;

    @FXML
    void backToReg(ActionEvent event) {
        Stage stage = (Stage) backToRegBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/regScene.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
    }

    @FXML
    void finalReg(ActionEvent event) {

    }

    @FXML
    void backToAuth(ActionEvent event) {
        Stage stage = (Stage) backToAuthBtn.getScene().getWindow();
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
    void continueReg(ActionEvent event) {
        String usInp = logToReg.getText();
        if (App.checkLogin(usInp).equals(true)) {
            errorLabel.setText("entered login is already registered");
            errorLabel.setVisible(true);
            return;
        }
        if (usInp.isEmpty()) {
            errorLabel.setText("enter not empty field");
            errorLabel.setVisible(true);
            return;
        }
        Stage stage = (Stage) logToReg.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/seedPhsScene.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        System.out.println(usInp);
        enteredLogin.setText(usInp);


    }
}
