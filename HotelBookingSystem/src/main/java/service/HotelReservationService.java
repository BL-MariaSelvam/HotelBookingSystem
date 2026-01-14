package main.java.service;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import main.java.model.Hotel;

public class HotelReservationService {

    // List to store all hotels
    private final List<Hotel> hotels = new ArrayList<>();

    // UC1: Add a hotel
    public void addHotel(Hotel hotel) {
        if (hotel == null) {
            System.out.println("Cannot add null hotel!");
            return;
        }

        // Optional: Check if hotel already exists by name
        boolean exists = hotels.stream()
                .anyMatch(h -> h.getName().equalsIgnoreCase(hotel.getName()));

        if (exists) {
            System.out.println("Hotel already exists: " + hotel.getName());
        } else {
            hotels.add(hotel);
            System.out.println("Hotel added successfully: " + hotel.getName());
        }
    }

    // Optional: get the list of all hotels (for verification)
    public List<Hotel> getHotels() {
        return hotels;
    }
    
    // UC2: Find cheapest hotel for given date range
    public Hotel findCheapestHotel(List<LocalDate> dates) {
        return hotels.stream()
                .sorted((h1, h2) -> {
                    int cost1 = calculateTotalCost(h1, dates);
                    int cost2 = calculateTotalCost(h2, dates);

                    if (cost1 != cost2) return Integer.compare(cost1, cost2);

                    // Tie-breaker: higher rating wins
                    return Integer.compare(h2.getRating(), h1.getRating());
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hotels available"));
    }

    // Calculate total cost for a hotel given the date range
    private int calculateTotalCost(Hotel hotel, List<LocalDate> dates) {
        int total = 0;
        for (LocalDate date : dates) {
            DayOfWeek day = date.getDayOfWeek();
            boolean isWeekend = (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);
            total += isWeekend ? hotel.getWeekendRate(false) : hotel.getWeekdayRate(false); // Regular customer assumed
        }
        return total;
    }

    // Optional helper to get total cost for a specific hotel & date range
    public int getTotalCostForHotel(Hotel hotel, List<LocalDate> dates) {
        return calculateTotalCost(hotel, dates);
    }}
