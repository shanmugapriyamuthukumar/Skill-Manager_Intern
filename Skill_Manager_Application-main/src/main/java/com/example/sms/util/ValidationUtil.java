package com.example.sms.util;

public final class ValidationUtil {
    private ValidationUtil(){}

    public static void requireRange(int value, int min, int max, String message) {
        if (value < min || value > max) throw new IllegalArgumentException(message);
    }

    public static void requireNonNegative(double value, String message) {
        if (value < 0) throw new IllegalArgumentException(message);
    }
}