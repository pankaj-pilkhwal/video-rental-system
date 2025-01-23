package com.crio.video_rental_system_advanced.repository;

import com.crio.video_rental_system_advanced.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
