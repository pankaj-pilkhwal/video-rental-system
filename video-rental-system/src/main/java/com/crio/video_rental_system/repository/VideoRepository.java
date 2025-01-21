package com.crio.video_rental_system.repository;

import com.crio.video_rental_system.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
