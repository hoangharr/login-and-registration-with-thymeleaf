package com.example.loginregistrationwiththymeleaf.repository;

import com.example.loginregistrationwiththymeleaf.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Roles, Long> {
    Roles findByName(String name);
}
