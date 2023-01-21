package com.example.demo;


public class Patient {
    private Integer id;
    private String ref;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String email;
    private String sexe;
    private String notes;

    public Patient(String ref, String nom, String prenom, String dateNaissance, String email, String sexe, String notes) {
        this.ref = ref;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;

        this.email = email;
        this.sexe = sexe;
        this.notes = notes;
    }

    public Patient() {
    }

    public Patient(Integer id, String ref, String nom, String prenom, String dateNaissance, String email, String sexe, String notes) {
        this.id = id;
        this.ref = ref;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.sexe = sexe;
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        String patientOutput = ref+ " | "+ nom + " "+ prenom;
        return patientOutput;
    }
    public String toString2() {
        String patientOutput =  nom + " "+ prenom;
        return patientOutput;
    }
}
