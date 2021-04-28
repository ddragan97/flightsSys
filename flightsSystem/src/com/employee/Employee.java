package com.employee;

import com.DBUtil;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.Scanner;

public class Employee {
    // pracownik1 | tajne || pracownik2 | haslo

    private boolean loggedAccount = false;
    private Connection conn = DBUtil.connect();
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet;


    public void loginToAccount() {
        System.out.println("Podaj login:");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.next();
        System.out.println("Podaj halo:");
        String password = scanner.next();

        try {
            ResultSet resultSet = DBUtil.getResultSet("SELECT Password FROM users WHERE Nick='"+login+"'");
            String pass ="";
            while (resultSet.next()) {
                pass = resultSet.getString(1);
            }
            String hashedPass = DigestUtils.sha256Hex(password);
            if(pass.toLowerCase().equals(hashedPass)) {
                System.out.println("Zalogowano poprawnie");
                loggedAccount = true;
            }
            else
                System.out.println("Bledne haslo");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isLoggedAccount() {
        return loggedAccount;
    }

    public static void createNewFlight(String from, String to, Object dateTimeDeparture, Object dateTimeArrival, int seats, String fly, int price) {
        try {
            preparedStatement = DBUtil.connect().prepareStatement("INSERT INTO flight(From_place,To_place,Departure,Arrival,Seats,Free_seats,Status,Fly,Price) VALUES(?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, from);
            preparedStatement.setString(2, to);
            preparedStatement.setObject(3, dateTimeDeparture);
            preparedStatement.setObject(4, dateTimeArrival);
            preparedStatement.setInt(5, seats);
            preparedStatement.setInt(6, seats);
            preparedStatement.setString(7, "OK");
            preparedStatement.setString(8, fly);
            preparedStatement.setInt(9, price);
            preparedStatement.execute();
            System.out.println("Lot dodany");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void modifyFlight(String columnName, Object newValue, int flyId) {
        try {
            preparedStatement = DBUtil.connect().prepareStatement("UPDATE flight SET "+columnName+" = ? WHERE Id = ?");
            preparedStatement.setObject(1, newValue);
            preparedStatement.setInt(2, flyId);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteFlight(int flyId) {
        try {
            statement = DBUtil.connect().createStatement();
            statement.execute("DELETE FROM flight WHERE Id="+flyId);
            System.out.println("Lot nr: " +flyId+ " zostal usuniety.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}