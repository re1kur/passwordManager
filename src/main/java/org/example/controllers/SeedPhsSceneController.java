package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.handlers.Handler;

import java.io.IOException;

public class SeedPhsSceneController {

    @FXML
    private Label enteredLogin;

    @FXML
    private Label errorLabel;

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
    private AnchorPane seedPhsScene;

    @FXML
    void backToReg(ActionEvent event) {
        Stage stage = (Stage) seedPhsScene.getScene().getWindow();
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
    void checkSeed(ActionEvent event) {
        Stage stage = (Stage) seedPhsScene.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/checkPhsScene.fxml"));
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
        String enteredLog = Handler.getCurrentLogin();
        enteredLogin.setText(enteredLog);
        Handler.generateRandomWords();
        String[] seeds = Handler.getSeeds();
        seed1.setText(seeds[0]);
        seed2.setText(seeds[1]);
        seed3.setText(seeds[2]);
        seed4.setText(seeds[3]);
        seed5.setText(seeds[4]);
        seed6.setText(seeds[5]);
        seed7.setText(seeds[6]);
        seed8.setText(seeds[7]);
        seed9.setText(seeds[8]);
        seed10.setText(seeds[9]);
    }

}
