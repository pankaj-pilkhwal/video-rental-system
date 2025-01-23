package com.crio.video_rental_system_advanced.service;

import com.crio.video_rental_system_advanced.dto.LoginDto;
import com.crio.video_rental_system_advanced.dto.UserDto;
import com.crio.video_rental_system_advanced.dto.UserResponseDto;
import com.crio.video_rental_system_advanced.entity.User;
import com.crio.video_rental_system_advanced.repository.UserRepository;
import com.crio.video_rental_system_advanced.util.JwtUtil;
import com.crio.video_rental_system_advanced.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    public UserResponseDto register(UserDto userDto) {
        if(userDto.getRole() == null) {
            userDto.setRole(Role.CUSTOMER);
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole());

        User user1 = userRepository.save(user);

        return UserResponseDto.builder()
                .firstName(user1.getFirstName())
                .lastName(user1.getLastName())
                .id(user1.getId())
                .email(user1.getEmail())
                .role(user1.getRole())
                .build();
    }

    public String login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());
        return jwtUtil.generateToken(userDetails);
    }
}
