package com.crio.video_rental_system.service;

import com.crio.video_rental_system.dto.UserDto;
import com.crio.video_rental_system.dto.UserResponseDto;
import com.crio.video_rental_system.entity.User;
import com.crio.video_rental_system.repository.UserRepository;
import com.crio.video_rental_system.util.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto saveUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        if(userDto.getRole() != null && userDto.getRole().toString() == "ADMIN") {
            user.setRole(Role.ADMIN);
        }else {
            user.setRole(Role.CUSTOMER);

        }

        User user1 = userRepository.save(user);

        return UserResponseDto.builder()
                .firstName(user1.getFirstName())
                .lastName(user1.getLastName())
                .id(user1.getId())
                .email(user1.getEmail())
                .role(user1.getRole())
                .build();
    }

}