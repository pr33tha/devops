package com.spacepark.park.Controller;

import com.spacepark.park.domain.Booking;
import com.spacepark.park.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*") // Allow cross-origin requests
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // REST API to create a booking
    @PostMapping
    public Booking bookSlot(@RequestBody Booking bookingRequest) {
        return bookingService.createBooking(
                bookingRequest.getVehicleNumber(),
                bookingRequest.getOwnerName()
        );
    }

    // REST API to fetch all bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // Endpoint to handle booking from a web form
    @PostMapping("/park")
    public String bookSlot(Booking booking, Model model) {
        bookingService.saveBooking(booking);
        model.addAttribute("message", "Slot booked successfully!");
        return "redirect:/home";
    }
}
