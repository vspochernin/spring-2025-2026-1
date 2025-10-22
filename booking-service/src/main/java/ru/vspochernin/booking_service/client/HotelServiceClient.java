package ru.vspochernin.booking_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "hotel-service", url = "http://localhost:8081")
public interface HotelServiceClient {

    @PostMapping("/api/rooms/{id}/confirm-availability")
    Boolean confirmAvailability(@org.springframework.web.bind.annotation.PathVariable("id") Long roomId,
                               @RequestHeader("X-Request-Id") String requestId);

    @PostMapping("/api/rooms/{id}/release")
    void releaseSlot(@org.springframework.web.bind.annotation.PathVariable("id") Long roomId,
                    @RequestHeader("X-Request-Id") String requestId);

    @PostMapping("/api/rooms/{id}/increment-bookings")
    void incrementTimesBooked(@org.springframework.web.bind.annotation.PathVariable("id") Long roomId,
                              @RequestHeader("X-Request-Id") String requestId);
}
