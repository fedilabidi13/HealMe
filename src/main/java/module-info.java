module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires itextpdf;
    requires java.mail;



    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}