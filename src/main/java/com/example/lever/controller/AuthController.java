package com.example.lever.controller;

import com.example.lever.dto.RegisterRequest;
import com.example.lever.model.Role;
import com.example.lever.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {

        try{
            String token = authService.registerUser(registerRequest.getEmail(), registerRequest.getPassword(), Role.valueOf(registerRequest.getRole().toUpperCase()));
            /*if (registerRequest.getEmail() == null || registerRequest.getPassword() == null || registerRequest.getRole() == null) {
                return ResponseEntity.badRequest().body("Email, password, and role are required.");
            }
            if (!registerRequest.getRole().equalsIgnoreCase("USER") && !registerRequest.getRole().equalsIgnoreCase("ADMIN")) {
                return ResponseEntity.badRequest().body("Role must be either USER or ADMIN.");
            */
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully. Token: " + token);
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: " + e.getMessage());
        }
    }


    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return authService.loginUser(email, password);
    }
}

