package com.backendExam.TafBookingService.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Booking {

    private Long bookingId;
    private User user;
    private Flight flight;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}