package com.grassroots.dto;

import com.grassroots.entity.AgeGroup;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AthleteProfileResponse {

    private Long id;

    // User info
    private Long userId;
    private String name;
    private String email;

    // Profile info
    private String sport;
    private AgeGroup ageGroup;
    private String city;
    private String state;
    private String bio;
    private String videoUrl;
    private String photoUrl;
    private Double latitude;
    private Double longitude;
    private LocalDateTime createdAt;
}
