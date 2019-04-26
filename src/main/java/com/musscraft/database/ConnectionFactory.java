package com.musscraft.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            String host = "jdbc:mysql://localhost/musscraft";
            String user = "root";
            String pass = "root";
            Connection connection = DriverManager
                    .getConnection(host, user, pass);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
