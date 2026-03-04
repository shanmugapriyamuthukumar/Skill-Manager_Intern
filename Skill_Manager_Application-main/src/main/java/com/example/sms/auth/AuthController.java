package com.example.sms.auth;

import com.example.sms.auth.dto.JwtResponse;
import com.example.sms.auth.dto.LoginRequest;
import com.example.sms.auth.dto.SignUpRequest;
import com.example.sms.security.JwtService;
import com.example.sms.user.Role;
import com.example.sms.user.User;
import com.example.sms.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

// Optional during dev so Angular can call directly
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public AuthController(AuthenticationManager authManager, JwtService jwtService,
                          UserRepository userRepo, PasswordEncoder encoder) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest req) {
        try {
            if (req.email() == null || req.password() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Missing 'email' or 'password' in request");
            }

            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.email(), req.password())
            );

            // Fetch the domain user from DB to include details and claims
            User user = userRepo.findByEmail(req.email())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            String token = jwtService.generateToken(
                    user.getEmail(),
                    java.util.Map.of(
                            "role", user.getRole().name(),
                            "userId", user.getId(),
                            "name", user.getName()
                    )
            );

            return ResponseEntity.ok(
                    new JwtResponse(token, user.getRole().name(), user.getId(), user.getName(), user.getEmail())
            );
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        } catch (Exception ex) {
            // Log the stacktrace on the server for debugging
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignUpRequest req) {
        try {
            if (userRepo.existsByEmail(req.email())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
            }

            User user = new User();
            user.setName(req.name());
            user.setEmail(req.email());
            user.setPassword(encoder.encode(req.password()));
            user.setRole(Role.EMPLOYEE);

            userRepo.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Signup successful");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Signup failed");
        }
    }
   
    
}