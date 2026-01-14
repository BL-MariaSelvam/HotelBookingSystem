package main.java.service;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    
    // UC3: Find cheapest hotel based on weekday & weekend rates
    // Here `isRewardCustomer` can be false for UC3 (only regular rates)
    public Hotel findCheapestHotel(List<LocalDate> dates, boolean isRewardCustomer) {
        return hotels.stream()
                .sorted((h1, h2) -> {
                    int cost1 = calculateTotalCost(h1, dates, isRewardCustomer);
                    int cost2 = calculateTotalCost(h2, dates, isRewardCustomer);

                    if (cost1 != cost2) return Integer.compare(cost1, cost2);

                    // Tie-breaker: higher rating wins
                    return Integer.compare(h2.getRating(), h1.getRating());
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hotels available"));
    }

    public int calculateTotalCost(Hotel hotel, List<LocalDate> dates, boolean isRewardCustomer) {
        int total = 0;
        for (LocalDate date : dates) {
            DayOfWeek day = date.getDayOfWeek();
            boolean isWeekend = (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);
            total += isWeekend ? hotel.getWeekendRate(isRewardCustomer) : hotel.getWeekdayRate(isRewardCustomer);
        }
        return total;
    }

 // UC4: Get all cheapest hotels for given date range
    public List<Hotel> findAllCheapestHotels(List<LocalDate> dates, boolean isRewardCustomer) {
        if (hotels.isEmpty()) {
            throw new RuntimeException("No hotels available");
        }

        // Calculate minimum total cost
        int minCost = hotels.stream()
                .mapToInt(hotel -> calculateTotalCost(hotel, dates, isRewardCustomer))
                .min()
                .orElseThrow(() -> new RuntimeException("No hotels available"));

        // Return all hotels with this min cost
        return hotels.stream()
                .filter(hotel -> calculateTotalCost(hotel, dates, isRewardCustomer) == minCost)
                .collect(Collectors.toList());
    }
}
