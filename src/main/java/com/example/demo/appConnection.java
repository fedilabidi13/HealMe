package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class appConnection {
    public String url="jdbc:mysql://localhost:3306/healthcare";
    public String login="root";
    public String pwd="";
    Connection cnx;
    public static appConnection instance;
    private appConnection(){
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connection established!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public Connection getCnx(){
        return cnx;
    }
    public static appConnection getInstance(){
        if (instance == null)
        {
            instance = new appConnection();
        }
        return instance;
    }
}
