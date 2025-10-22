package ru.vspochernin.booking_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vspochernin.booking_service.entity.User;

import java.util.Base64;

@Service
@Slf4j
public class JwtService {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.ttl-seconds}")
    private Long ttlSeconds;

    public String generateToken(User user) {
        log.info("Generating JWT token for user: {}", user.getUsername());

        // Упрощённая генерация токена для данного этапа
        String payload = user.getUsername() + ":" + user.getRole().name() + ":" + System.currentTimeMillis();
        String token = Base64.getEncoder().encodeToString(payload.getBytes());

        log.info("Token generated for user: {}", user.getUsername());
        return token;
    }

    public String extractUsername(String token) {
        try {
            String payload = new String(Base64.getDecoder().decode(token));
            return payload.split(":")[0];
        } catch (Exception e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            return null;
        }
    }

    public String extractRole(String token) {
        try {
            String payload = new String(Base64.getDecoder().decode(token));
            return payload.split(":")[1];
        } catch (Exception e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            return null;
        }
    }

    public boolean isTokenValid(String token) {
        try {
            String payload = new String(Base64.getDecoder().decode(token));
            String[] parts = payload.split(":");
            if (parts.length != 3) return false;

            long tokenTime = Long.parseLong(parts[2]);
            long currentTime = System.currentTimeMillis();
            long ttlMillis = ttlSeconds * 1000;

            return (currentTime - tokenTime) < ttlMillis;
        } catch (Exception e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }
}
