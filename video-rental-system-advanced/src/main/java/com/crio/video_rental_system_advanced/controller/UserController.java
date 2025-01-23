package com.crio.video_rental_system_advanced.controller;

import com.crio.video_rental_system_advanced.dto.LoginDto;
import com.crio.video_rental_system_advanced.dto.UserDto;
import com.crio.video_rental_system_advanced.dto.UserResponseDto;
import com.crio.video_rental_system_advanced.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class UserController {

    private final AuthService authService;

    @Autowired
    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserDto userDto) {

        try {
            return new ResponseEntity<>(authService.register(userDto), HttpStatus.CREATED);
        }catch (Exception e) {
            System.out.println("User with email: " + userDto.getEmail() + " is already registered before");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser( @RequestBody LoginDto loginDto) {
        try {
            String token = authService.login(loginDto);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }

}