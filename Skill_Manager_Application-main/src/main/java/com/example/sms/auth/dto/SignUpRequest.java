package com.example.sms.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String password
) {

	public String name() {
		return name;
	}

	public String email() {
		return email;
	}

	public String password() {
		return password;
	}
	
	
}
