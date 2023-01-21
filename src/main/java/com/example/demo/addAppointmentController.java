package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class addAppointmentController implements Initializable {
    @FXML
    private Button SubmitBtn;
    @FXML
    private ComboBox statusCombo;
    @FXML
    private ComboBox patientCombo;
    @FXML
    private DatePicker dateInput;
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
    public void confirmAdd(){
        String date = String.valueOf(dateInput.getValue());
        String status = (String) statusCombo.getValue();
        Patient patient = (Patient) patientCombo.getValue();
        Appointment appointment = new Appointment();
        appointment.setDate(date);
        appointment.setPatient(patient.getId());
        appointment.setStatus(status);
        AppointmentCRUD apd = new AppointmentCRUD();
        try {
            apd.addAppointment(appointment);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("appointment added !");
        alert.showAndWait();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PatientCRUD ppd= new PatientCRUD();
        patientCombo.setItems(ppd.patientList());
        ObservableList<String> status =  FXCollections.observableArrayList();
        status.add("done");
        status.add("cancelled");
        status.add("pending");
        statusCombo.setItems(status);

    }
}
