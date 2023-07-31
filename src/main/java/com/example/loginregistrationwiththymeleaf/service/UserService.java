package com.example.loginregistrationwiththymeleaf.service;

import com.example.loginregistrationwiththymeleaf.dto.UserDto;
import com.example.loginregistrationwiththymeleaf.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    void saveUser(UserDto userDto);

    Users findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
