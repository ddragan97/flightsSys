package com.client;

import com.DBUtil;
import com.Flight;
import java.sql.*;

public class Reservation {
    private Connection conn = DBUtil.connect();
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet;

    public static void reserveFlight(int flyId, String pesel, int ticketsCount) {
        if(Flight.getFreeSeatsCount(flyId) - ticketsCount >= 0 && ticketsCount > 0) {
            Flight.updateFreeSeatsCount(flyId, Flight.getFreeSeatsCount(flyId) - ticketsCount);
            try {
                preparedStatement = DBUtil.connect().prepareStatement("INSERT INTO reservation(Fly_id,Pesel,Amount) VALUES(?,?,?)");
                preparedStatement.setInt(1, flyId);
                preparedStatement.setString(2, pesel);
                preparedStatement.setInt(3, ticketsCount);
                preparedStatement.execute();
                System.out.println("Rezerwacja dokonana");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else {
            System.out.println("Na ten lot pozostalo jedynie: " +Flight.getFreeSeatsCount(flyId) + " wolnych miejsc.");
        }
    }

    public static void showMyReservations(String pesel) {
        try {
            resultSet = DBUtil.getResultSet("SELECT From_place, To_place, Departure, Arrival, Price, Amount FROM flight INNER JOIN reservation ON flight.Id=reservation.Fly_id WHERE Pesel=pesel");
            while (resultSet.next()) {
                System.out.println("Lot z: " +resultSet.getString(1));
                System.out.println("Lot do: " +resultSet.getString(2));
                System.out.println("Czas odlotu: " +resultSet.getString(3));
                System.out.println("Czas przylotu: " +resultSet.getString(4));
                System.out.println("Ilosc biletow: " +resultSet.getInt(6));
                System.out.println("Cena laczna: " +resultSet.getInt(5)*resultSet.getInt(6));
                System.out.println("");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
