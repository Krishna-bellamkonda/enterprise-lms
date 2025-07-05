package com.example.lever.repository;

import java.util.Optional;
import com.example.lever.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
