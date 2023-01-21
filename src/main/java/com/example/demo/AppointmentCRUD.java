package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class AppointmentCRUD {
    Connection cnx;

    public AppointmentCRUD() {
        cnx= appConnection.getInstance().getCnx();
    }
    public void addAppointment(Appointment appointment) throws SQLException {
        PatientCRUD ppd = new PatientCRUD();
        String req2="INSERT INTO `appointment` ("
                + "`ref`,`patient`, `status`,`patient2`,"
                + " `date`) "
                + "VALUES (?,?,?,?,?)";
        PreparedStatement pst = cnx.prepareStatement(req2);
        //pst.setInt(1, c.getId_bien_contrat());
        String ref = generateRef();
        while (verifierRef(ref)==true)
        {
            ref = generateRef();
        }
        pst.setString(1,ref);
        pst.setInt(2, appointment.getPatient());
        pst.setString(3, appointment.getStatus());
        pst.setString(4,ppd.findPatientById(appointment.getPatient()).toString2());
        pst.setString(5, appointment.getDate());


        pst.executeUpdate();
        System.out.println("appointment added!");

    }
    public void updateAppointment(Appointment appointment)
    {
        PatientCRUD ppd = new PatientCRUD();

        try {
            String req4= "UPDATE `appointment` SET `status`=?,`patient`=?,`patient2`=?,`date`=? WHERE `ref` = ?";
            PreparedStatement pst2 = cnx.prepareStatement(req4);
            pst2.setString(1, appointment.getStatus());
            pst2.setInt(2, appointment.getPatient());
            pst2.setString(3,ppd.findPatientById(appointment.getPatient()).toString2());
            pst2.setString(4, appointment.getDate());
            pst2.setString(5, appointment.getRef());
            pst2.executeUpdate();
            System.out.println("appointment updated "+ appointment);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Appointment findAppointment(String ref)
    {
        Appointment c2 = new Appointment();
        c2.setRef("empty");
        try {

            String req3 = "Select * from appointment";
            Statement st;
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req3);

            while(rs.next())
            {
                System.out.println(rs.getString(2));
                System.out.println(" | ");
                if (rs.getString(2).equals(ref) )
                {c2.setId(rs.getInt(1));
                    c2.setId(rs.getInt(1));
                    c2.setRef(rs.getString(2));
                    c2.setDate(rs.getString(3));
                    c2.setPatient(rs.getInt(4));
                    c2.setStatus(rs.getString(5));

                    System.out.println("appointment trouve !!");
                    System.out.println(c2);


                }

            }


        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return c2;
    }
    public void deleteAppointment(Appointment appointment)
    {
        String req4= "DELETE from `appointment` WHERE `ref` = ?";
        PreparedStatement pst2 = null;
        try {
            pst2 = cnx.prepareStatement(req4);
            pst2.setString(1, appointment.getRef());
            pst2.executeUpdate();
            System.out.println("patient deleted "+ appointment);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String generateRef()
    {
        String ref = "Ap-";

        int max = 9;
        int min = 0;
        int range = max - min + 1;

        // generate random numbers within 1 to 10
        for (int i = 0; i < 4; i++) {
            int rand = (int)(Math.random() * range) + min;

            // Output is different everytime this code is executed
            ref+=Integer.toString(rand);
        }
        return ref;
    }
    public boolean verifierRef(String ref)
    {
        boolean test = false;
        List<Appointment> appointmentList= new ArrayList<>();
        AppointmentCRUD apd = new AppointmentCRUD();
        Appointment appointment = new Appointment();
        appointmentList = apd.AppointmentList();

        for (int i=0; i < appointmentList.size();i++)
        {
            appointment = appointmentList.get(i);
            if (appointment.getRef()==ref)
                test = true;
        }

        return test;
    }

    public ObservableList<Appointment> AppointmentList(){
        PatientCRUD ppd = new PatientCRUD();
        ObservableList<Appointment> appointmentSet = FXCollections.observableArrayList();
        String req = "SELECT * from appointment";
        Statement st;

        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next())
            {
                Appointment appointment =new Appointment();
                appointment.setId(rs.getInt(1));
                appointment.setRef(rs.getString(2));
                appointment.setDate(rs.getString(3));
                appointment.setPatient(rs.getInt(4));
                appointment.setPatient2(ppd.findPatientById(rs.getInt(4)).toString2());
                appointment.setStatus(rs.getString(5));
                appointmentSet.add(appointment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return appointmentSet;
    }

}
