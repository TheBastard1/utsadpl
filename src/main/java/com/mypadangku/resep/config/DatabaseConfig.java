package com.mypadangku.resep.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    // Sesuaikan URL, username, dan password dengan XAMPP MySQL Anda
    private static final String URL = "jdbc:mysql://localhost:3306/mypadangku_resep";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
