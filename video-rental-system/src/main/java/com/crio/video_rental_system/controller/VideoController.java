package com.crio.video_rental_system.controller;

import com.crio.video_rental_system.entity.Video;
import com.crio.video_rental_system.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/videos")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<List<Video>> getAllVideos() {
        return new ResponseEntity<>(videoService.getAllVideos(), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<Video> createVideo(@RequestBody Video video) {
        return new ResponseEntity<>(videoService.createVideo(video), HttpStatus.CREATED);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable Long id, @RequestBody Video video) {
        return new ResponseEntity<>(videoService.updateVideo(id, video), HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return new ResponseEntity<>("Video with id: " + id + " is deleted successfully", HttpStatus.NO_CONTENT);
    }
}