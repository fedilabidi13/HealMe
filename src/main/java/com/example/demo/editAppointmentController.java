package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class editAppointmentController implements Initializable {

    @FXML
    private ComboBox comboStatus;

    @FXML
    private ComboBox comboPatient;



    @FXML
    private TextField SearchInput;

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


    @FXML
    private Button SubmitBtn;
    @FXML
    private Button btnSearch;


    public void confirmUpdate(){
        String ref = "Ap-";
        ref+= SearchInput.getText();
        AppointmentCRUD apd = new AppointmentCRUD();
        PatientCRUD ppd = new PatientCRUD();
        Appointment appointment = apd.findAppointment(ref);

        String statustxt = (String) comboStatus.getValue();
        Patient patient = (Patient) comboPatient.getValue();
        String date = String.valueOf(dateInput.getValue());
       appointment.setStatus(statustxt);
       appointment.setDate(date);
       appointment.setPatient2(patient.toString2());
       appointment.setPatient(patient.getId());
       apd.updateAppointment(appointment);
        ObservableList<String> status =  FXCollections.observableArrayList();
        status.add("done");
        status.add("cancelled");
        status.add("pending");
        comboStatus.setItems(status);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("appointment updated !");
        alert.showAndWait();

    }
    public void searchPatient(){

        String ref = "Ap-";
        ref+= SearchInput.getText();
        AppointmentCRUD apd = new AppointmentCRUD();
        PatientCRUD ppd = new PatientCRUD();
        Appointment appointment = apd.findAppointment(ref);
        if (appointment.getRef().equals("empty"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("appointment not found !");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("appointment found !");
            alert.showAndWait();

            dateInput.setValue(LocalDate.parse(appointment.getDate()));
            comboStatus.setValue(appointment.getStatus());
            Patient patient = ppd.findPatientById(appointment.getPatient());
            comboPatient.setValue(patient);




        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PatientCRUD ppd= new PatientCRUD();
        comboPatient.setItems(ppd.patientList());
        ObservableList<String> status =  FXCollections.observableArrayList();
        status.add("done");
        status.add("cancelled");
        status.add("pending");
        comboStatus.setItems(status);

    }
}
