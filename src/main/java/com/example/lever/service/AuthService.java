package com.example.lever.service;

import com.example.lever.model.Role;
import com.example.lever.model.User;
import com.example.lever.repository.UserRepository;
import com.example.lever.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    // Constructor injection for UserRepository, PasswordEncoder, and JwtUtil
    //used @RequiredArgsConstructor to avoid below commented constructor
    /*
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    */

    public String registerUser(String email, String password, Role role) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User user = new User(null, email, passwordEncoder.encode(password), role);
        userRepository.save(user);
        return jwtUtil.generateToken(email);
    }

    public String loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return jwtUtil.generateToken(email);
        }
        throw new RuntimeException("Invalid email or password");
    }
}
