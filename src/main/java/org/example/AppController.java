package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AppController {
    public Button mainBtn;
    public AnchorPane mainScene;
    public AnchorPane authorizationScene;
    public Button logBtn;
    public TextField loginField;
    public Button regBtn;
    public Label ivalidLog;
    public AnchorPane regScene;
    public TextField logToReg;
    public Button logToRegBtn;
    public Button backToAuthBtn;
    public Label alreadyRegistredLogin;

    public AppController() {

    }

    public void mainBtnClicked(ActionEvent actionEvent) {
            System.out.println("You've pressed the mainButton.");

    }

    public void loginEntered(ActionEvent actionEvent) {
        String usInp = loginField.getText();
        if (checkLog(usInp) == false) {
            ivalidLog.setVisible(true);
        } else {
            System.out.println("you have successfully login");
    }
    }

    public void regBrnClicked(ActionEvent actionEvent) {
        authorizationScene.setVisible(false);
        regScene.setVisible(true);
    }

    public void logBtnClicked(ActionEvent actionEvent) {
        loginEntered(actionEvent);
    }
    static Boolean checkLog (String login) {
        if (login.equals("re1kur")) {
            return true;
        }
        return false;
    }

    public void continueReg(ActionEvent actionEvent) {
        String newLog = logToReg.getText();
        if (checkLog(newLog) == true) {
            alreadyRegistredLogin.setVisible(true);
            return;
        }
        logToReg.setVisible(false);
        logToRegBtn.setVisible(false);
    }

    public void backToAuth(ActionEvent actionEvent) {
        regScene.setVisible(false);
        authorizationScene.setVisible(true);
    }
}
