package com.example.loginregistrationwiththymeleaf.service;

import com.example.loginregistrationwiththymeleaf.dto.UserDto;
import com.example.loginregistrationwiththymeleaf.model.Roles;
import com.example.loginregistrationwiththymeleaf.model.Users;
import com.example.loginregistrationwiththymeleaf.repository.RoleRepo;
import com.example.loginregistrationwiththymeleaf.repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service

public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepository,
                           RoleRepo roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepo = userRepository;
        this.roleRepo = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        Users user = new Users();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Roles role = roleRepo.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepo.save(user);
    }

    @Override
    public Users findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<Users> users = userRepo.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(Users user) {
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Roles checkRoleExist() {
        Roles role = new Roles();
        role.setName("ROLE_ADMIN");
        return roleRepo.save(role);
    }
}
