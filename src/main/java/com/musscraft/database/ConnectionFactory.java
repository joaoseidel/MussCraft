package com.musscraft.database;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private Connection connection;
    private DSLContext context;

    public ConnectionFactory() {
        this.connection = getConnection();
    }

    public Connection getConnection() {
        try {
            String host = "jdbc:mysql://localhost/MussCraft";
            String user = "root";
            String pass = "root";
            connection = DriverManager.getConnection(host, user, pass);
            context = DSL.using(connection, SQLDialect.MYSQL);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DSLContext getContext(Connection connection) {
        return context;
    }

    public DSLContext getContext() {
        return context;
    }
}
