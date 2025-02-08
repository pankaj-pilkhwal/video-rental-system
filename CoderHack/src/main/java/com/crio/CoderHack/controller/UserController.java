package com.crio.CoderHack.controller;

import com.crio.CoderHack.dto.UserRegistrationRequest;
import com.crio.CoderHack.entity.User;
import com.crio.CoderHack.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllByOrderByScoreDesc();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
            return userService.createUser(userRegistrationRequest);
    }

    @PutMapping("/{userId}/{newScore}")
    public ResponseEntity<User> updateUserScore(@PathVariable String userId, @Valid @RequestBody User updatedUser) {

        return userService.updateUserScore(userId, updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
