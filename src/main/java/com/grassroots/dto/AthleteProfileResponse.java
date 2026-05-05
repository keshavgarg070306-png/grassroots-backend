package com.grassroots.dto;

import com.grassroots.entity.AgeGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    // Performance Metrics
    private List<MetricDTO> metrics;

    private LocalDateTime createdAt;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MetricDTO {
        private String type;
        private Double value;
        private LocalDateTime timestamp;
    }
}
