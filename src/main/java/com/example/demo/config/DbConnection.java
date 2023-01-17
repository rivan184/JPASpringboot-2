package com.example.demo.config;

import java.sql.DriverManager;

import java.sql.Connection;

public class DbConnection {
    private static Connection con;

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_employee?zeroDateTimeBehavior=convertToNull","root","pass");
            
        } catch (Exception e) {
            System.out.println("Error + "+ e.getMessage());
        }
        
        return con;
    }
    
}
