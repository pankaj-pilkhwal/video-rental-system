package com.crio.CoderHack.service;

import com.crio.CoderHack.dto.UserRegistrationRequest;
import com.crio.CoderHack.entity.User;
import com.crio.CoderHack.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAllByOrderByScoreDesc() {
        return userRepository.
                findAllByOrderByScoreDesc();
    }

    public ResponseEntity<User> getUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);

        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> createUser(UserRegistrationRequest userRegistrationRequest) {
        if (userRepository.existsByUsername(userRegistrationRequest.getUsername()) || userRepository.existsById(userRegistrationRequest.getUserId())) {
            return ResponseEntity.badRequest().body("User already exits with the given details.");
        }

        User user = new User();
        user.setUserId(userRegistrationRequest.getUserId());
        user.setUsername(userRegistrationRequest.getUsername());
        user.setScore(0);
        user.setBadges(new HashSet<>());

        User savedUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser.getUserId());
    }


    public ResponseEntity<User> updateUserScore(String userId, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(userId);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setScore(updatedUser.getScore());
            user.updateBadges();
            userRepository.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void deleteById(String userId) {
        userRepository.deleteById(userId);
    }
}
