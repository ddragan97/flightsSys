package com;

import com.client.Reservation;
import com.employee.Employee;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DBUtil.connect();
        int menuNumber = 1;
        int employeeMenuNumber = -1;

        while(menuNumber != 0) {
            System.out.println("1. Pokaz wszystkie loty\n2. Wyszukaj lot\n3. Pokaz konkretny lot\n4. Zarezerwuj lot\n5. Pokaz moje rezerwacje\n9. Logowanie pracownika\n0. Zakoncz");
            Scanner scanner = new Scanner(System.in);
            try {
                menuNumber = scanner.nextInt();
                switch (menuNumber) {
                    case 1 -> Flight.showAllFlights();
                    case 2 -> {
                        System.out.println("Podaj slowo kluczowe");
                        String searchTag = scanner.next();
                        Flight.searchFlight(searchTag);
                    }
                    case 3 -> {
                        System.out.println("Podaj numer lotu:");
                        int flyNumber = scanner.nextInt();
                        Flight.showOneFlight(flyNumber);
                    }
                    case 4 -> {
                        System.out.println("Podaj numer lotu");
                        int flyId = scanner.nextInt();
                        System.out.println("Podaj numer PESEL");
                        String pesel = scanner.next();
                        System.out.println("Podaj ilosc biletow");
                        int ticketsCount = scanner.nextInt();
                        Reservation.reserveFlight(flyId, pesel, ticketsCount);
                    }
                    case 5 -> {
                        System.out.println("Podaj numer PESEL");
                        String pesel = scanner.next();
                        Reservation.showMyReservations(pesel);
                    }
                    case 9 -> {
                        Employee employee = new Employee();
                        employee.loginToAccount();
                        if (employee.isLoggedAccount()) {
                            while (employeeMenuNumber != 0) {
                                System.out.println("1. Pokaz lot\n2. Dodaj lot\n3. Modyfikuj lot\n4. Usun lot\n0. Zakoncz");
                                Scanner scanner2 = new Scanner(System.in);
                                Scanner scanner3 = new Scanner(System.in);
                                try {
                                    employeeMenuNumber = scanner2.nextInt();
                                    switch (employeeMenuNumber) {
                                        case 1 -> {
                                            System.out.println("Podaj numer lotu:");
                                            int flyEmployeeNumber = scanner3.nextInt();
                                            Flight.showOneFlight(flyEmployeeNumber);
                                        }
                                        case 2 -> {
                                            System.out.println("Lot z: ");
                                            String from = scanner3.next();
                                            System.out.println("Lot do: ");
                                            String to = scanner3.next();
                                            System.out.println("Odlot (rrr-mm-dd hh:mm:ss): ");
                                            scanner3 = new Scanner(System.in);
                                            Object departure = scanner3.nextLine();
                                            System.out.println("Przylot (rrr-mm-dd hh:mm:ss): ");
                                            scanner3 = new Scanner(System.in);
                                            Object arrival = scanner3.nextLine();
                                            System.out.println("Ilosc miejsc: ");
                                            int seats = scanner3.nextInt();
                                            System.out.println("Typ lotu (Direct/Connecting): ");
                                            String fly = scanner3.next();
                                            System.out.println("Cena: ");
                                            int price = scanner3.nextInt();
                                            Employee.createNewFlight(from, to, departure, arrival, seats, fly, price);
                                        }
                                        case 3 -> {
                                            System.out.println("Podaj id lotu:");
                                            int flyModifyEmployeeNumber = scanner3.nextInt();
                                            Flight.showOneFlight(flyModifyEmployeeNumber);
                                            System.out.println("\nModyfikuj:\n1. Odlot\n2. Przylot\n3. Liczba wolnych miejsc\n4. Status\n5. Cena");
                                            Scanner scanner4 = new Scanner(System.in);
                                            int employeeToDo = scanner4.nextInt();
                                            Scanner scanner5 = new Scanner(System.in);
                                            switch (employeeToDo) {
                                                case 1 -> {
                                                    System.out.println("Podaj nowa date odlotu (yyyy-mm-dd HH:MM): ");
                                                    String newDeparture = scanner5.nextLine();
                                                    Employee.modifyFlight("Departure", newDeparture, flyModifyEmployeeNumber);
                                                }
                                                case 2 -> {
                                                    System.out.println("Podaj nowa date przylotu (yyyy-mm-dd HH:MM): ");
                                                    String newArrival = scanner5.nextLine();
                                                    Employee.modifyFlight("Arrival", newArrival, flyModifyEmployeeNumber);
                                                }
                                                case 3 -> {
                                                    System.out.println("Podaj nowa ilosc wolnych miejsc: ");
                                                    int newFreeSeats = scanner5.nextInt();
                                                    Employee.modifyFlight("Free_seats", newFreeSeats, flyModifyEmployeeNumber);
                                                }
                                                case 4 -> {
                                                    System.out.println("Podaj nowy status: ");
                                                    String newStatus = scanner5.nextLine();
                                                    Employee.modifyFlight("Status", newStatus, flyModifyEmployeeNumber);
                                                }
                                                case 5 -> {
                                                    System.out.println("Podaj nowa cene: ");
                                                    int newPrice = scanner5.nextInt();
                                                    Employee.modifyFlight("Price", newPrice, flyModifyEmployeeNumber);
                                                }
                                                default -> System.out.println("Bledny wybor");
                                            }
                                        }
                                        case 4 -> {
                                            System.out.println("Podaj numer lotu do usuniecia:");
                                            int flyDeleteEmployeeNumber = scanner3.nextInt();
                                            Employee.deleteFlight(flyDeleteEmployeeNumber);
                                        }
                                        case 0 -> System.out.println("Wylogowano");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Niepoprawny wybor opcji!");
                                }
                            }
                        }
                    }
                    case 0 -> System.out.println("Zapraszamy ponownie");
                }
            } catch (Exception e) {
                System.out.println("Niepoprawny wybor opcji!");
            }
        }
    }
}