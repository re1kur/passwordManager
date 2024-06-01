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

public class AuthSceneController {
    @FXML
    private AnchorPane authorizationScene;

    @FXML
    private Label ivalidLog;

    @FXML
    private Button logBtn;

    @FXML
    private TextField loginField;

    @FXML
    private Button regBtn;

    @FXML
    void logBtnClicked(ActionEvent event) {
        String usInp = loginField.getText();
        if (App.checkLogin(usInp) == false) {
            ivalidLog.setVisible(true);
            return;
        }
        System.out.printf("You've successfully login.");
    }

    @FXML
    void regBtnClicked(ActionEvent event) {
        Stage stage = (Stage) regBtn.getScene().getWindow();
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
}
