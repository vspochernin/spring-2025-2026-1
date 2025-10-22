package ru.vspochernin.booking_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.vspochernin.booking_service.dto.BookingDto;
import ru.vspochernin.booking_service.dto.CreateBookingRequest;
import ru.vspochernin.booking_service.entity.User;
import ru.vspochernin.booking_service.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
@Slf4j
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<BookingDto> createBooking(@Valid @RequestBody CreateBookingRequest request,
                                                     Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        log.info("Creating booking for user: {}", user.getUsername());
        BookingDto booking = bookingService.createBooking(request, user);
        return ResponseEntity.ok(booking);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<BookingDto>> getUserBookings(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        log.info("Retrieving bookings for user: {}", user.getUsername());
        List<BookingDto> bookings = bookingService.getUserBookings(user.getId());
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<BookingDto> getBooking(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        log.info("Retrieving booking ID: {} for user: {}", id, user.getUsername());
        BookingDto booking = bookingService.getBookingById(id, user.getId());
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        log.info("Cancelling booking ID: {} for user: {}", id, user.getUsername());
        bookingService.cancelBooking(id, user.getId());
        return ResponseEntity.ok().build();
    }
}
