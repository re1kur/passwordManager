package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import org.example.classes.PasswordPane;
import org.example.handlers.DataBaseHandler;
import org.example.handlers.Handler;

import java.io.IOException;

public class PasswordPaneController {

    @FXML
    public Button deletePasswBtn;

    @FXML
    public Button hidePasswBtn;

    @FXML
    public Button copyBtn;

    @FXML
    private Label noteLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button showPasswBtn;

    @FXML
    void initialize() {
        copyBtn.setOnAction(_ -> {
            final ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(passwordLabel.getText());
            Clipboard.getSystemClipboard().setContent(clipboardContent);
        });
        noteLabel.setText(PasswordPane.getNote());
        passwordLabel.setText(PasswordPane.getPassword());
        showPasswBtn.setOnAction(_ -> passwordLabel.setEffect(new GaussianBlur(0)));
        hidePasswBtn.setOnAction(_ -> passwordLabel.setEffect(new GaussianBlur(10)));
        deletePasswBtn.setOnAction(_ -> {
            DataBaseHandler.deletePassword(Handler.getCurrentLogin(), passwordLabel.getText());
            Stage stage = (Stage) passwordLabel.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/app.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
        });

    }

}
