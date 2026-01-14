package main.java.service;


import java.util.ArrayList;
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
}
