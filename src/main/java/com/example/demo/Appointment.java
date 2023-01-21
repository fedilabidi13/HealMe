package com.example.demo;

public class Appointment {
    private Integer id;
    private String ref ;
    private String date ;
    private String status;
    private Integer patient;
    private String patient2;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    public String getPatient2() {
        return patient2;
    }

    public void setPatient2(String patient2) {
        this.patient2 = patient2;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", patient=" + patient +
                '}';
    }

    public Appointment() {
    }

    public Appointment(Integer id, String ref, String date, String status, Integer patient) {
        this.id = id;
        this.ref = ref;
        this.date = date;
        this.status = status;
        this.patient = patient;
    }

    public Appointment(String ref, String date, String status, Integer patient) {
        this.ref = ref;
        this.date = date;
        this.status = status;
        this.patient = patient;
    }
}
