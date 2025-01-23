package com.crio.video_rental_system_advanced.repository;

import com.crio.video_rental_system_advanced.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
