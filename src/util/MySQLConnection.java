package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/cookwithme";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "maladmin";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; //MySQL version 8

    public static Connection getConnection() {
        Connection connection = null;

        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Error Driver");
        } catch (SQLException e) {
            System.err.println("Error SQL");
        }

        return connection;
    }
}
