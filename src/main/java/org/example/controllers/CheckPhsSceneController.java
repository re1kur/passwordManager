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

public class CheckPhsSceneController {
    @FXML
    private Label errorLabel;

    @FXML
    private Label seedNumber;
    @FXML
    private AnchorPane checkSeedPhsScene;

    @FXML
    private TextField enteredSeed;

    @FXML
    void backToSeeds(ActionEvent event) {
        Stage stage = (Stage) checkSeedPhsScene.getScene().getWindow();
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

    @FXML
    void finalReg(ActionEvent event) {
        String usInp = enteredSeed.getText().trim();
        if (!usInp.equals(Handler.getSeeds()[Handler.getSeedNumber()])) {
            errorLabel.setVisible(true);
            return;
        }
        String[] seeds = Handler.getSeeds();
        DataBaseHandler.signUpUser(Handler.getCurrentLogin(), seeds[0],
                seeds[1], seeds[2], seeds[3], seeds[4], seeds[5],
                seeds[6], seeds[7], seeds[8], seeds[9]);
        Stage stage = (Stage) checkSeedPhsScene.getScene().getWindow();
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
    public void initialize() {
        seedNumber.setText(
                "Enter the seed phrase with number "
                        + Handler.getRandInt()
        );
    }
}
