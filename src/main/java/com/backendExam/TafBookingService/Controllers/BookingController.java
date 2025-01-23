package com.backendExam.TafBookingService.Controllers;

import com.backendExam.TafBookingService.Model.Booking;
import com.backendExam.TafBookingService.Services.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private final BookingServiceImpl bookingServiceImpl;

    public BookingController(BookingServiceImpl bookingServiceImpl) {
        this.bookingServiceImpl = bookingServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
        return new ResponseEntity<>(bookingServiceImpl.add(booking), HttpStatus.CREATED);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long bookingId) {
        return new ResponseEntity<>(bookingServiceImpl.get(bookingId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(bookingServiceImpl.getByUserId(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long bookingId) {
        return new ResponseEntity<>(bookingServiceImpl.delete(bookingId),HttpStatus.OK);
    }

}
