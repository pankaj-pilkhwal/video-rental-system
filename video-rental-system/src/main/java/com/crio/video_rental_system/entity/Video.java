package com.crio.video_rental_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String director;

    private String genre;

    private boolean available;

}