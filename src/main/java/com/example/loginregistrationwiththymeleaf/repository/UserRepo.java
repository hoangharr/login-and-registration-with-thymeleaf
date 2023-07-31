package com.example.loginregistrationwiththymeleaf.repository;

import com.example.loginregistrationwiththymeleaf.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
