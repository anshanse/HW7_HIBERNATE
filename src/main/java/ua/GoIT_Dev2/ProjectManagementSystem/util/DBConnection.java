package ua.GoIT_Dev2.ProjectManagementSystem.util;

import lombok.SneakyThrows;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection implements Closeable {
    private final static String URL = PropertiesLoader.getProperty("db.url");
    private final static String USER = PropertiesLoader.getProperty("db.user");
    private final static String PASS = PropertiesLoader.getProperty("db.password");
    private final static String JDBC_DRIVER = PropertiesLoader.getProperty("db.driver");

    private static DBConnection dbConnection;
    private Connection connection;

    @SneakyThrows
    private DBConnection() {
            Class.forName(JDBC_DRIVER);
            this.connection = DriverManager.getConnection(URL, USER, PASS);
    }

    @SneakyThrows
    public static DBConnection getInstance(){
        if (dbConnection == null || dbConnection.getConnection().isClosed()){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }

    @SneakyThrows
    @Override
    public void close() throws IOException {
        connection.close();
    }
}
