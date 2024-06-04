package org.example.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.assets.Password;
import org.example.assets.PasswordPane;
import org.example.handlers.DataBaseHandler;
import org.example.handlers.Handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppController {

    @FXML
    public Button backToAuthBtn;

    @FXML
    private AnchorPane passwordPane;

    @FXML
    private VBox vboxPasswords;

    @FXML
    private Button addPassword;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    public void initialize () {
        addPasswordPanes(Handler.getCurrentLogin());
        addPassword.setOnAction(event -> showPasswordDialog());
        backToAuthBtn.setOnAction(actionEvent -> {
            Stage stage = (Stage) scrollpane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/authScene.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
        });
    }
    private void showPasswordDialog () {
        DataBaseHandler db = new DataBaseHandler();
        List<String> choices = new ArrayList<>();
        choices.add("Generate password");
        choices.add("Add existing password");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Generate password", choices);
        dialog.setTitle("Password options");
        dialog.setHeaderText("Choose an option");
        dialog.setContentText("Select:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(choice -> {
            if ("Generate password".equals(choice)) {
                TextInputDialog lengthDialog = new TextInputDialog();
                lengthDialog.setTitle("Generate password");
                lengthDialog.setHeaderText("Enter password length");
                lengthDialog.setContentText("Length:");

                Optional<String> lengthResult = lengthDialog.showAndWait();
                lengthResult.ifPresent(length -> {
                    TextInputDialog noteDialog = new TextInputDialog();
                    noteDialog.setTitle("Add note");
                    noteDialog.setHeaderText("Enter a note for the password");
                    noteDialog.setContentText("Note:");
                    Optional<String> noteResult = noteDialog.showAndWait();
                    try {
                        noteResult.ifPresent(note -> {
                            db.addGeneratedPassword(Handler.getCurrentLogin(), length, note);
                        });
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid length entered");
                    }
                });
            } else if ("Add existing password".equals(choice)) {
                TextInputDialog existingPasswordDialog = new TextInputDialog();
                existingPasswordDialog.setTitle("Add existing password");
                existingPasswordDialog.setHeaderText("Enter existing password");
                existingPasswordDialog.setContentText("Password:");

                Optional<String> existingPasswordResult = existingPasswordDialog.showAndWait();
                existingPasswordResult.ifPresent(existingPassword -> {
                    TextInputDialog noteDialog = new TextInputDialog();
                    noteDialog.setTitle("Add note");
                    noteDialog.setHeaderText("Enter a note for the password");
                    noteDialog.setContentText("Note:");

                    Optional<String> noteResult = noteDialog.showAndWait();
                    noteResult.ifPresent(note -> {
                        db.addExistingPassword(Handler.getCurrentLogin(),existingPassword, note);
                    });
                });
            }
        });
        reloadScene();
    }
    private void addPasswordPanes (String login) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        List<Password> passwords = dbHandler.parsePasswords(login);
        for (Password password: passwords) {
            vboxPasswords.getChildren().add(new PasswordPane(password.getPassword(),
                    password.getNote(), vboxPasswords));
        }
    }

    private void reloadScene () {
        Stage stage = (Stage) scrollpane.getScene().getWindow();
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
}

