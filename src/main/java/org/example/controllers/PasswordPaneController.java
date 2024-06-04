package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.MotionBlur;
import org.example.assets.Password;
import org.example.assets.PasswordPane;

public class PasswordPaneController {

    @FXML
    public Button deletePasswBtn;

    @FXML
    public Button hidePasswBtn;

    @FXML
    private Label noteLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button showPasswBtn;

    @FXML
    void initialize () {
        noteLabel.setText(PasswordPane.getNote());
        passwordLabel.setText(PasswordPane.getPassword());
        showPasswBtn.setOnAction(actionEvent -> passwordLabel.setEffect(new GaussianBlur(0)));
        hidePasswBtn.setOnAction(actionEvent -> passwordLabel.setEffect(new GaussianBlur(10)));
        deletePasswBtn.setOnAction(actionEvent -> System.out.println("delete"));

    }

}
