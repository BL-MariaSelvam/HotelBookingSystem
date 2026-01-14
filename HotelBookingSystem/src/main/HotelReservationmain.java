package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import main.java.model.Hotel;
import main.java.service.HotelReservationService;

public class HotelReservationmain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HotelReservationService service = new HotelReservationService();

        boolean exit = false;

        do {
            System.out.println("\n==== Hotel Reservation System ====");
            System.out.println("1. Add Hotel (UC1)");
            System.out.println("2. Find Cheapest Hotel (UC2)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Hotel Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Hotel Rating: ");
                    int rating = sc.nextInt();

                    System.out.print("Enter Weekday Rate for Regular Customer: ");
                    int weekdayRegular = sc.nextInt();

                    System.out.print("Enter Weekend Rate for Regular Customer: ");
                    int weekendRegular = sc.nextInt();

                    System.out.print("Enter Weekday Rate for Rewards Customer: ");
                    int weekdayReward = sc.nextInt();

                    System.out.print("Enter Weekend Rate for Rewards Customer: ");
                    int weekendReward = sc.nextInt();
                    sc.nextLine(); // consume newline

                    Hotel hotel = new Hotel(name, rating, weekdayRegular, weekendRegular,
                            weekdayReward, weekendReward);

                    service.addHotel(hotel);

                    System.out.println("Hotel added successfully: " + hotel.getName());
                    break;

                case 2:
                	 // UC2: Find Cheapest Hotel
                    System.out.println("Enter dates separated by comma (e.g., 10Sep2020,11Sep2020):");
                    String inputDates = sc.nextLine();

                    List<LocalDate> dates = Arrays.stream(inputDates.split(","))
                            .map(date -> LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("ddMMMyyyy")))
                            .collect(Collectors.toList());

                    Hotel cheapestHotel = service.findCheapestHotel(dates);
                    int totalCost = service.getTotalCostForHotel(cheapestHotel, dates);

                    System.out.println("Cheapest Hotel: " + cheapestHotel.getName() +
                            ", Total Rates: $" + totalCost);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option! Try again.");
            }

        } while (!exit);

        sc.close();
    }
}

