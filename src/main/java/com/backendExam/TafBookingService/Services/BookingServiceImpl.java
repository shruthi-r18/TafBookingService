package com.backendExam.TafBookingService.Services;

import com.backendExam.TafBookingService.Model.Booking;
import com.backendExam.TafBookingService.Model.Flight;
import com.backendExam.TafBookingService.Model.User;
import com.backendExam.TafBookingService.Services.Interface.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private final RestTemplate restTemplate;



    @Value("${datastore.ms.url}")
    String datastore_url;

    @Value("${user.ms.url}")
    String user_url;

    @Value("${flight.ms.url}")
    String flight_url;

    public BookingServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Booking add(Booking booking) {
        // Validate User
        String userUrl = user_url + "/" + booking.getUser().getUserId();
        User user = restTemplate.getForObject(userUrl, User.class);
        if (user == null) {
            throw new IllegalArgumentException("User does not exist");
        }

        // Validate Flight
        String flightUrl = flight_url + "/" + booking.getFlight().getFlightId();
        Flight flight = restTemplate.getForObject(flightUrl, Flight.class);
        if (flight == null) {
            throw new IllegalArgumentException("Flight does not exist");
        }

        // Create Booking
        String datastoreUrl = datastore_url + "/bookings";
        Booking bookingCreated = restTemplate.postForObject(datastoreUrl, booking, Booking.class);
        return bookingCreated;
    }

    @Override
    public List<Booking> getAll() {
        String datastoreUrl = datastore_url + "/bookings";
        ResponseEntity<List<Booking>> response = restTemplate.exchange(
                datastoreUrl,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Booking>>() {
                }
        );
        return response.getBody();
    }

    @Override
    public Booking get(Long id) {
        String datastoreUrl = datastore_url + "/bookings/" + id;
        Booking bookingById = restTemplate.getForObject(datastoreUrl, Booking.class);
        return bookingById;
    }



    @Override
    public Booking delete(Long id) {
        String datastoreUrl = datastore_url + "/bookings/" + id;


        Booking booking= restTemplate.exchange( datastoreUrl, HttpMethod.DELETE, HttpEntity.EMPTY, Booking.class).getBody();
        return booking;
    }

    @Override
    public List<Booking> getByUserId(Long userId) {
        String datastoreUrl = datastore_url + "/bookings/user/" + userId;
        ResponseEntity<List<Booking>> response = restTemplate.exchange(
                datastoreUrl,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Booking>>() {
                }
        );

        return response.getBody();
    }
}


