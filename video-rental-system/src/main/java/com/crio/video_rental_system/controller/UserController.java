package com.crio.video_rental_system.controller;

import com.crio.video_rental_system.dto.LoginDto;
import com.crio.video_rental_system.dto.UserDto;
import com.crio.video_rental_system.dto.UserResponseDto;
import com.crio.video_rental_system.entity.User;
import com.crio.video_rental_system.service.UserService;
import com.crio.video_rental_system.util.Role;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserDto userDto) {

        return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDto loginDto) {
        System.out.println("login page hit");
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

}