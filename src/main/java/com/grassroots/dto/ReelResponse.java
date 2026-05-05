package com.grassroots.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReelResponse {
    private Long id;
    private Long athleteId;
    private String athleteName;
    private String videoUrl;
    private String description;
    private String aiMetadata;
    private Integer likesCount;
    private Integer sharesCount;
    private boolean isLikedByMe;
    private LocalDateTime createdAt;
}
