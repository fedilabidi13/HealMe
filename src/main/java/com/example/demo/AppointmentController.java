package com.example.demo;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {
    @FXML
    private Button editBtn;
    @FXML
    private TableView<Appointment> tableView;

    @FXML
    private TableColumn<Appointment,String> refColumn;


    @FXML
    private TableColumn<Appointment,String> dateColumn;
    @FXML
    private TableColumn<Appointment,String> statusColumn;
    @FXML
    private TableColumn<Appointment, String> patientColumn;
    @FXML
    private TextField searchArea;
    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;
    @FXML
    private Button deleteBtn;

    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : white");
            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnSignout) {
            Stage stage = (Stage) btnOrders.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("login");
            stage.setScene(scene);
            stage.show();
        }
        if (actionEvent.getSource() == btnOverview) {
            Stage stage = (Stage) btnOrders.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Heal Me | HealthCare App");
            stage.setScene(scene);
            stage.show();
        }
        if(actionEvent.getSource()==btnOrders)
        {
            Stage stage = (Stage) btnOrders.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AppointmentHome.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Heal Me | HealthCare App");
            stage.setScene(scene);
            stage.show();

        }
    }
    public void fnctnAdd() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AppointmentAdd.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Add appointment");
        stage.setScene(new Scene(root));
        stage.show();



    }
    public void loadData(){
        AppointmentCRUD apd = new AppointmentCRUD();
        refColumn.setCellValueFactory(new PropertyValueFactory<>("ref"));
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patient2"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));




        tableView.setItems(apd.AppointmentList());
        FilteredList<Appointment> filteredData = new FilteredList<>((apd.AppointmentList()), b -> true);
        searchArea.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(contrat1 -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (String.valueOf(contrat1.getRef()).toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (String.valueOf(contrat1.getStatus()).toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }  else if (contrat1.getDate().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if (contrat1.getPatient2().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else
                    return false;
            });
        });
        SortedList<Appointment> sortedList = new SortedList<>(filteredData);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
        // edit and delete icons
    }


    public void editLoadData() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AppointmentEdit.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Edit appointment");
        stage.setScene(new Scene(root));
        stage.show();


    }
    public void deleteAppointment(){
        Appointment appointment = tableView.getSelectionModel().getSelectedItem();
        AppointmentCRUD apd = new AppointmentCRUD();
        apd.deleteAppointment(appointment);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("appointment deleted !");
        alert.showAndWait();
        loadData();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();

    }
}
