package com;

import java.sql.*;

public class Flight {
    private Connection conn = DBUtil.connect();
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet;


    public static void showOneFlight(int flyId) {
        try {
            resultSet = DBUtil.getResultSet("SELECT * FROM flight WHERE Id='"+flyId+"'");
            while (resultSet.next()) {
                System.out.println("ID lotu: " +resultSet.getInt(1));
                System.out.println("Lot z: " +resultSet.getString(2));
                System.out.println("Lot do: " +resultSet.getString(3));
                System.out.println("Czas odlotu: " +resultSet.getString(4));
                System.out.println("Czas przylotu: " +resultSet.getString(5));
                System.out.println("Ilosc miejsc: " +resultSet.getInt(6));
                System.out.println("Wolnych miejsc: " +resultSet.getInt(7));
                System.out.println("Status lotu: " +resultSet.getString(8));
                System.out.println("Typ lotu: " +resultSet.getString(9));
                System.out.println("Cena: " +resultSet.getInt(10));
                System.out.println("");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void showAllFlights() {
        try {
            resultSet = DBUtil.getResultSet("SELECT * FROM flight");
            while (resultSet.next()) {
                System.out.println("ID lotu: " +resultSet.getInt(1));
                System.out.println("Lot z: " +resultSet.getString(2));
                System.out.println("Lot do: " +resultSet.getString(3));
                System.out.println("Czas odlotu: " +resultSet.getString(4));
                System.out.println("Czas przylotu: " +resultSet.getString(5));
                System.out.println("Ilosc miejsc: " +resultSet.getInt(6));
                System.out.println("Wolnych miejsc: " +resultSet.getInt(7));
                System.out.println("Status lotu: " +resultSet.getString(8));
                System.out.println("Typ lotu: " +resultSet.getString(9));
                System.out.println("Cena: " +resultSet.getInt(10));
                System.out.println("");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int getFreeSeatsCount(int flyId) {
        int freeSeats = 0;
        try {
            resultSet = DBUtil.getResultSet("SELECT Free_seats FROM flight WHERE Id='"+flyId+"'");
            while (resultSet.next()) {
                 freeSeats = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return freeSeats;
    }

    public static void updateFreeSeatsCount(int flyId, int seatsCount) {
        try {
            preparedStatement = DBUtil.connect().prepareStatement("UPDATE flight SET Free_seats = ? WHERE Id = ?");
            preparedStatement.setInt(1, seatsCount);
            preparedStatement.setInt(2, flyId);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void searchFlight(String searchTag) {
        try {
            ResultSet resultSet2 = DBUtil.getResultSet("SELECT * FROM flight");
            while (resultSet2.next()) {
                int id = resultSet2.getInt(1);
                String fromPlace = resultSet2.getString(2);
                String toPlace = resultSet2.getString(3);
                String departure = resultSet2.getString(4);
                String arrival = resultSet2.getString(5);
                String status = resultSet2.getString(8);
                String fly = resultSet2.getString(9);
                int price = resultSet2.getInt(10);
                if(fromPlace.contains(searchTag) || toPlace.contains(searchTag) || departure.contains(searchTag) || arrival.contains(searchTag)
                || status.contains(searchTag) || fly.contains(searchTag) || String.valueOf(price).contains(searchTag)) {
                    Flight.showOneFlight(id);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
