package com.example.sms.auth;

import com.example.sms.user.Role;
import com.example.sms.user.User;
import com.example.sms.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminBootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Value("${app.admin.name:System Admin}") String adminName;
    @Value("${app.admin.email:admin@sms.local}") String adminEmail;
    @Value("${app.admin.password:Admin@123}") String adminPassword;

    public AdminBootstrap(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        userRepository.findByEmail(adminEmail).ifPresentOrElse(
            u -> System.out.println("Admin exists: " + adminEmail),
            () -> {
                com.example.sms.user.User admin = new com.example.sms.user.User();
                admin.setName(adminName);
                admin.setEmail(adminEmail);
                admin.setPassword(encoder.encode(adminPassword));
                admin.setRole(com.example.sms.user.Role.ADMIN);

                userRepository.save(admin);
                System.out.println("Admin user created: " + adminEmail);
            }
        );
    }
}