package com.example.demo;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class addPatientController implements Initializable {

    @FXML
    private TextField NameInput;
    @FXML
    private TextField SurNameInput;
    @FXML
    private TextField EmailInput;
    @FXML
    private ComboBox GenderInput;

    @FXML
    private Button CloseBtnAdd;

    @FXML
    private DatePicker BirthDateInput;
    @FXML
    private void getDate(ActionEvent event) {


    }

    public String formatDate(String Date) {
        SimpleDateFormat sdf = null;
        java.util.Date d = null;
        try {
            sdf = new SimpleDateFormat("yy-mm-dd");
            d = sdf.parse(Date);
            sdf.applyPattern("EEEE d MMM yyyy");

        } catch (ParseException e) {

            System.out.println(e);
        }


        return sdf.format(d);
    }

    @FXML
    private TextArea NotesInput;
    @FXML
    private Button SubmitBtn;
    @FXML
    private Button SubmitBtn1;

    public void confirmUpdate(){

    }

    public void confirmAdd(ActionEvent event) throws SQLException, IOException {
        Patient patient = new Patient();
        PatientCRUD  ppd = new PatientCRUD();
        String nom = NameInput.getText();
        String prenom = SurNameInput.getText();
        String gender1 = (String) GenderInput.getValue();
        String email = EmailInput.getText();
        String Notes = NotesInput.getText();
        String date = String.valueOf(BirthDateInput.getValue());
        patient.setNom(nom);
        patient.setPrenom(prenom);
        patient.setSexe(gender1);
        patient.setEmail(email);
        patient.setNotes(Notes);
        patient.setDateNaissance(date);
        ppd.addPatient(patient);
        ObservableList<String> gender =  FXCollections.observableArrayList();
        gender.add("Male");
        gender.add("Female");
        GenderInput.setItems(gender);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("patient added !");
        alert.showAndWait();


    }
    public void showConfirm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("noteAdd.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("added ! ");
        stage.setScene(new Scene(root));
        stage.show();

    }


    private void LoadMainWindow(){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> gender =  FXCollections.observableArrayList();
        gender.add("Male");
        gender.add("Female");
        GenderInput.setItems(gender);
        SubmitBtn1.setVisible(false);
    }
}
