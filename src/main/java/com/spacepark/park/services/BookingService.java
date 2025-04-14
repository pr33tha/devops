package com.spacepark.park.services;

import com.spacepark.park.domain.Booking;
import com.spacepark.park.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Create a booking
    public Booking createBooking(String vehicleNumber, String ownerName) {
        Booking booking = new Booking();
        booking.setVehicleNumber(vehicleNumber);
        booking.setOwnerName(ownerName);
        return bookingRepository.save(booking);
    }

    // Fetch all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Save a booking directly
    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }
}
