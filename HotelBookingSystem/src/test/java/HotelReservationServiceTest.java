package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.model.Hotel;
import main.java.service.HotelReservationService;

import java.util.List;

public class HotelReservationServiceTest {

    private HotelReservationService service;

    @BeforeEach
    public void setup() {
        service = new HotelReservationService();
    }

    @Test
    public void givenHotel_whenAdded_ShouldBeStoredInService() {
        // Arrange
        Hotel lakewood = new Hotel("Lakewood", 3, 110, 90, 80, 80);

        // Act
        service.addHotel(lakewood);

        // Assert
        List<Hotel> hotels = service.getHotels();
        assertEquals(1, hotels.size(), "There should be 1 hotel in the service");
        assertEquals("Lakewood", hotels.get(0).getName(), "Hotel name should be Lakewood");
        assertEquals(3, hotels.get(0).getRating(), "Hotel rating should be 3");
        assertTrue(hotels.get(0).getWeekdayRate(false) == 110, "Weekday Regular rate should be 110");
        assertTrue(hotels.get(0).getWeekendRate(false) == 90, "Weekend Regular rate should be 90");
    }

    @Test
    public void givenDuplicateHotel_whenAdded_ShouldNotAddAgain() {
        Hotel lakewood1 = new Hotel("Lakewood", 3, 110, 90, 80, 80);
        Hotel lakewood2 = new Hotel("Lakewood", 4, 120, 100, 90, 85);

        service.addHotel(lakewood1);
        service.addHotel(lakewood2); // Duplicate name

        List<Hotel> hotels = service.getHotels();
        assertEquals(1, hotels.size(), "Duplicate hotel should not be added");
    }
}

