package com.example.demo;

import com.itextpdf.text.DocumentException;
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
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    PatientCRUD ppd = new PatientCRUD();
    @FXML
    private Button editBtn;
    @FXML
    private TableView<Patient> tableView;

    @FXML
    private TableColumn<Patient,String> refColumn;

    @FXML
    private TableColumn<Patient,String> nomColumn;
    @FXML
    private TableColumn<Patient,String> prenomColumn;
    @FXML
    private TableColumn<Patient,String> sexeColumn;
    @FXML
    private TableColumn<Patient,String> emailColumn;
    @FXML
    private TableColumn<Patient,String> dateNaissanceColumn;
    @FXML
    private TableColumn<Patient,String> notesColumn;
    @FXML
    private TableColumn<Task, Boolean> actionsColumn;
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
    @FXML
    private Button printBtn;
    @FXML
    private Button mailBtn;













    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : white");
            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnSignout) {
            Stage stage = (Stage) btnOrders.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Heal Me | HealthCare App");
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
    public void editLoadData() throws IOException {
        Patient selectedItem = tableView.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edit.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("edit patient");
        stage.setScene(new Scene(root));
        stage.show();


    }
    public void printPatient() throws DocumentException, IOException {
        Patient patient = tableView.getSelectionModel().getSelectedItem();
        PatientCRUD ppd = new PatientCRUD();
        ppd.exportPatient(patient);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("patient card exported to PatientsCard folder in desktop !");
        alert.showAndWait();
        loadData();
    }

    public void loadData(){
        PatientCRUD ppd = new PatientCRUD();
        refColumn.setCellValueFactory(new PropertyValueFactory<>("ref"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexeColumn.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        dateNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));



        tableView.setItems(ppd.patientList());
        FilteredList<Patient> filteredData = new FilteredList<>((ppd.patientList()), b -> true);
        searchArea.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(contrat1 -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (String.valueOf(contrat1.getRef()).toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (String.valueOf(contrat1.getNom()).toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (contrat1.getPrenom().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (contrat1.getEmail().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if (contrat1.getSexe().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if (contrat1.getDateNaissance().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if (contrat1.getNotes().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else
                    return false;
            });
        });
        SortedList<Patient> sortedList = new SortedList<>(filteredData);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
        // edit and delete icons
    }



    public void fnctnAdd() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("add patient");
        stage.setScene(new Scene(root));
        stage.show();



    }

    public void showConfirm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("noteAdd.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("added");
        stage.setScene(new Scene(root));
        stage.show();


    }


    private void LoadMainWindow(){

    }

    public void deletePatient(){
        Patient patient = tableView.getSelectionModel().getSelectedItem();
        PatientCRUD ppd = new PatientCRUD();
        ppd.deletePatient(patient);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("patient deleted !");
        alert.showAndWait();
        loadData();
    }

    public void sendmail() throws Exception {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();

    }

}

