package com.example.appdist.Config;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfiguration {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/appdist", "root", "");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return con;
    }
}
