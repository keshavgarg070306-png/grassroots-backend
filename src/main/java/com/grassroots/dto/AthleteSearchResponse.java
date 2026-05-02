package com.grassroots.dto;

import com.grassroots.entity.AgeGroup;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AthleteSearchResponse {
    private Long id;
    private String name;
    private String sport;
    private AgeGroup ageGroup;
    private String city;
    private String state;
    private String photoUrl;
    private String videoUrl;
    private Double latitude;
    private Double longitude;
}
