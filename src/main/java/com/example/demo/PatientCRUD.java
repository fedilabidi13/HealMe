package com.example.demo;

import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PatientCRUD {
    Connection cnx;

    public PatientCRUD() {
        cnx= appConnection.getInstance().getCnx();
    }
    public void addPatient(Patient patient) throws SQLException {
        String req2="INSERT INTO `patient` ("
                + "`ref`,`nom`, `prenom`, `sexe`,"
                + " `notes`, `email`,`dateNaissance`) "
                + "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst = cnx.prepareStatement(req2);
        //pst.setInt(1, c.getId_bien_contrat());
        String ref = generateRef();
        while (verifierRef(ref)==true)
        {
            ref = generateRef();
        }
        pst.setString(1,ref);
        pst.setString(2, patient.getNom());
        pst.setString(3, patient.getPrenom());
        pst.setString(4, patient.getSexe());
        pst.setString(5, patient.getNotes());
        pst.setString(6, patient.getEmail());
        pst.setString(7, patient.getDateNaissance());

        pst.executeUpdate();
        System.out.println("patient added!");

    }
    public void updatePatient(Patient patient)
    {

        try {
            String req4= "UPDATE `patient` SET `nom`=?,`prenom`=?,`sexe`=?,`email`=?,`notes`=?,`dateNaissance`=? WHERE `ref` = ?";
            PreparedStatement pst2 = cnx.prepareStatement(req4);
            pst2.setString(1, patient.getNom());
            pst2.setString(2, patient.getPrenom());
            pst2.setString(3, patient.getSexe());
            pst2.setString(4, patient.getEmail());
            pst2.setString(5, patient.getNotes());
            pst2.setString(6, patient.getDateNaissance());
            pst2.setString(7, patient.getRef());
            pst2.executeUpdate();
            System.out.println("patient updated "+ patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Patient findPatient(String ref)
    {
        Patient c2 = new Patient();
        c2.setRef("empty");
        try {

            String req3 = "Select * from patient";
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
                    c2.setNom(rs.getString(3));
                    c2.setPrenom(rs.getString(4));
                   c2.setDateNaissance(rs.getString(5));
                    c2.setSexe(rs.getString(6));
                    c2.setEmail(rs.getString(7));
                    c2.setNotes(rs.getString(8));
                    System.out.println("contrat trouve !!");
                    System.out.println(c2);


                }

            }


        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return c2;
    }
    public Patient findPatientById(Integer id)
    {
        Patient c2 = new Patient();
        c2.setRef("empty");
        try {

            String req3 = "Select * from patient";
            Statement st;
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req3);

            while(rs.next())
            {

                if (rs.getInt(1)==id )
                {c2.setId(rs.getInt(1));
                    c2.setId(rs.getInt(1));
                    c2.setRef(rs.getString(2));
                    c2.setNom(rs.getString(3));
                    c2.setPrenom(rs.getString(4));
                    c2.setDateNaissance(rs.getString(5));
                    c2.setSexe(rs.getString(6));
                    c2.setEmail(rs.getString(7));
                    c2.setNotes(rs.getString(8));



                }

            }


        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return c2;
    }
    public void deletePatient(Patient patient)
    {
        String req4= "DELETE from `patient` WHERE `ref` = ?";
        PreparedStatement pst2 = null;
        try {
            pst2 = cnx.prepareStatement(req4);
            pst2.setString(1, patient.getRef());
            pst2.executeUpdate();
            System.out.println("patient deleted "+ patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String generateRef()
    {
        String ref = "Pt-";

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
        List<Patient> patientList= new ArrayList<>();
        PatientCRUD ppd = new PatientCRUD();
        Patient patient = new Patient();
        patientList = ppd.patientList();

        for (int i=0; i < patientList.size();i++)
        {
            patient = patientList.get(i);
            if (patient.getRef()==ref)
                test = true;
        }

        return test;
    }

    public ObservableList<Patient> patientList(){
        ObservableList<Patient> patientSet = FXCollections.observableArrayList();
        String req = "SELECT * from patient";
        Statement st;

        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next())
            {
                Patient patient =new Patient();
                patient.setId(rs.getInt(1));
                patient.setRef(rs.getString(2));
                patient.setNom(rs.getString(3));
                patient.setPrenom(rs.getString(4));
                patient.setDateNaissance(rs.getString(5));
                patient.setSexe(rs.getString(6));
                patient.setEmail(rs.getString(7));
                patient.setNotes(rs.getString(8));
                patientSet.add(patient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patientSet;
    }
    public void exportPatient(Patient patient) throws IOException, DocumentException {
        String file_name="C:\\Users\\Fedi Labidi\\Desktop\\PatientCards\\"+patient.getRef()+".pdf";
        Document document = new Document() ;
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph ("Heal Me | Patient Details:"+"\n \n \n");

        document.add(para);
        Paragraph para1 = new Paragraph("Patient Code: : "+patient.getRef()+"\n \n \n");
        Paragraph para7 = new Paragraph("Name: "+patient.getNom()+" " + patient.getPrenom()+"\n \n \n");
        Paragraph para8 = new Paragraph("Email: "+patient.getEmail()+"\n \n \n");
        Paragraph para2 = new Paragraph("Birthdate: "+patient.getDateNaissance()+"\n \n \n");
        Paragraph para3 = new Paragraph("Gender : "+patient.getSexe()+"\n \n \n");

        document.add(para1);
        document.add(para7);
        document.add(para2);
        document.add(para3);


        document.add(para8);




        document.close();
    }

    }

