package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML
    private Button login;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pwd;
    public void auth() throws IOException {
        String mail = email.getText();
        String pass = pwd.getText();
        if ((mail.equals("admin"))&& (pass.equals("admin")))
        {
            Stage stage = (Stage) login.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Heal Me | HealthCare App ");
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Credentials!");
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
