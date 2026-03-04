package com.example.sms.auth.dto;

public record JwtResponse(
        String token,
        String role,
        Long userId,
        String name,
        String email
) {}