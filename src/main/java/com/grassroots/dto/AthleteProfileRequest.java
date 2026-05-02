package com.grassroots.dto;

import com.grassroots.entity.AgeGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AthleteProfileRequest {

    @NotBlank(message = "Sport is required")
    private String sport;

    @NotNull(message = "Age group is required")
    private AgeGroup ageGroup;

    private String city;
    private String state;
    private String bio;
    private String videoUrl;
    private String photoUrl;
    private Double latitude;
    private Double longitude;
}
