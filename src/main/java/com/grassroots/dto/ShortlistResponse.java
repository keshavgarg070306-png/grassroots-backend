package com.grassroots.dto;

import com.grassroots.entity.AgeGroup;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShortlistResponse {
    private Long shortlistId;
    private String note;
    private LocalDateTime shortlistedAt;

    // Athlete snapshot
    private Long athleteId;
    private String athleteName;
    private String sport;
    private AgeGroup ageGroup;
    private String city;
    private String state;
    private String photoUrl;
}
