package org.example.classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PasswordPane extends VBox {
    private VBox main;
    private static String password;
    private static String note;

    public PasswordPane(String password, String note, VBox main) {
        this.password = password;
        this.note = note;
        this.main = main;
        createUI();
    }

    private void createUI() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/passwordPane.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        getChildren().add(new VBox(root));
    }

    public static String getPassword() {
        return password;
    }

    public static String getNote() {
        return note;
    }

}
