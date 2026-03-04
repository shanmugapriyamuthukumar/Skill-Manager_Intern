package com.example.sms.user;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User getByEmail(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found: " + email));
    }

    public User getCurrentUser(Authentication auth) {
        return getByEmail(auth.getName());
    }
}