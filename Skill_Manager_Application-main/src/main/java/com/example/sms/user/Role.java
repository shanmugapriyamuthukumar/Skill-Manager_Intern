package com.example.sms.user;

/**
 * Application roles used for authorization.
 * Persisted as STRING in the User entity with @Enumerated(EnumType.STRING).
 */
public enum Role {
    ADMIN,
    EMPLOYEE
}