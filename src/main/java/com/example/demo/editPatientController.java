package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class editPatientController implements Initializable {
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
    private TextField SearchInput;

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

    
    public void confirmUpdate() throws SQLException {
        String ref = "Pt-";
        ref+= SearchInput.getText();
        PatientCRUD  ppd = new PatientCRUD();
        Patient patient = ppd.findPatient(ref);
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
        ppd.updatePatient(patient);
        ObservableList<String> gender =  FXCollections.observableArrayList();
        gender.add("Male");
        gender.add("Female");
        GenderInput.setItems(gender);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("patient updated !");
        alert.showAndWait();
        
    }


    public void searchPatient(){
        String ref = "Pt-";
        ref+= SearchInput.getText();
        PatientCRUD  ppd = new PatientCRUD();
        Patient patient = ppd.findPatient(ref);
        if (patient.getRef().equals("empty"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("patient not found !");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("patient found !");
            alert.showAndWait();
            NameInput.setText(patient.getNom());
            SurNameInput.setText(patient.getPrenom());
            EmailInput.setText(patient.getEmail());
            BirthDateInput.setValue(LocalDate.parse(patient.getDateNaissance()));
            GenderInput.setValue(patient.getSexe());
            NotesInput.setText(patient.getNotes());



        }


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
