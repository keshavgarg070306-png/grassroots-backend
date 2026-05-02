package com.grassroots.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MapPinResponse {
    private Long id;
    private String name;
    private String sport;
    private String city;
    private Double latitude;
    private Double longitude;
}
