package com.backendExam.TafBookingService.Services.Interface;

import com.backendExam.TafBookingService.Model.Booking;

import java.util.List;

public interface BookingService {

    Booking add(Booking booking);

    List<Booking> getAll();

    Booking get(Long id);

    Booking delete(Long id);

    List<Booking> getByUserId(Long userId);
}
