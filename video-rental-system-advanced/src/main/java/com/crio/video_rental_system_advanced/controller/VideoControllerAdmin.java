package com.crio.video_rental_system_advanced.controller;

import com.crio.video_rental_system_advanced.entity.Video;
import com.crio.video_rental_system_advanced.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
public class VideoControllerAdmin {
    private final VideoService videoService;

    public VideoControllerAdmin(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/video")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Video> createVideo(@RequestBody Video video) {
        return new ResponseEntity<>(videoService.createVideo(video), HttpStatus.CREATED);
    }

    @PutMapping("/video/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Video> updateVideo(@PathVariable Long id, @RequestBody Video video) {
        return new ResponseEntity<>(videoService.updateVideo(id, video), HttpStatus.OK);
    }

    @DeleteMapping("/video/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return new ResponseEntity<>("Video with id: " + id + " is deleted successfully", HttpStatus.NO_CONTENT);
    }
}
