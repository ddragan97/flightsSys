package com;

import java.sql.*;

public class DBUtil {
    private final static String DBURL = "jdbc:mysql://127.0.0.1:3306/flights";
    private final static String DBUSER = "root";
    private final static String DBPASS = "";
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public static Connection connect() {
        try {
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static ResultSet getResultSet(String sqlString) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlString);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

}
