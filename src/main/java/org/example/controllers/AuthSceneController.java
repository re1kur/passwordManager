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

public class AuthSceneController {
    @FXML
    public AnchorPane authScene;

    @FXML
    public Label errorLabel;

    @FXML
    private Label invalidLog;

    @FXML
    private TextField loginField;

    @FXML
    void logBtnClicked(ActionEvent event) {
        String usInp = loginField.getText().trim();
        if (!DataBaseHandler.checkLogin(usInp)) {
            invalidLog.setVisible(true);
            return;
        }
        Handler.setCurrentLogin(usInp);
        Stage stage = (Stage) authScene.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/authPhsScene.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
    }

    @FXML
    void regBtnClicked(ActionEvent event) {
        Stage stage = (Stage) authScene.getScene().getWindow();
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
