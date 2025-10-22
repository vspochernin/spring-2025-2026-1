package ru.vspochernin.booking_service.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.vspochernin.booking_service.entity.User;
import ru.vspochernin.booking_service.repository.UserRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class BootstrapConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String adminUsername = System.getenv().getOrDefault("BOOTSTRAP_ADMIN_USER", "admin");
        String adminPassword = System.getenv().getOrDefault("BOOTSTRAP_ADMIN_PASS", "admin");

        if (userRepository.findByUsername(adminUsername).isEmpty()) {
            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole(User.Role.ADMIN);

            userRepository.save(admin);
            log.info("Bootstrap ADMIN user created: {}", adminUsername);
        } else {
            log.info("Bootstrap ADMIN user already exists: {}", adminUsername);
        }
    }
}
