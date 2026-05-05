package com.grassroots.service;

import com.grassroots.dto.AthleteSearchResponse;
import com.grassroots.dto.MapPinResponse;
import com.grassroots.entity.AgeGroup;
import com.grassroots.entity.Athlete;
import com.grassroots.repository.AthleteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final AthleteRepository athleteRepository;

    /**
     * Paginated search with optional sport / state / ageGroup filters.
     * Blank strings are treated the same as null (filter ignored).
     */
    public Page<AthleteSearchResponse> searchAthletes(
            String sport,
            String state,
            String ageGroup,
            int page,
            int size
    ) {
        // Normalise blanks to null so the JPQL "IS NULL" check skips the filter
        String sportParam    = StringUtils.hasText(sport)    ? sport.trim()    : null;
        String stateParam    = StringUtils.hasText(state)    ? state.trim()    : null;
        AgeGroup ageGroupParam = null;
        if (StringUtils.hasText(ageGroup)) {
            try {
                ageGroupParam = AgeGroup.valueOf(ageGroup.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Invalid ageGroup value: '" + ageGroup +
                        "'. Valid values: U16, U17, U19, U23, SENIOR");
            }
        }

        PageRequest pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return athleteRepository
                .searchAthletes(sportParam, stateParam, ageGroupParam, pageable)
                .map(this::toSearchResponse);
    }

    /**
     * Returns all athletes with geo-coordinates for map pin rendering.
     */
    public List<MapPinResponse> getMapPins() {
        return athleteRepository.findAllForMap()
                .stream()
                .map(this::toMapPin)
                .toList();
    }

    // ── Mappers ──────────────────────────────────────────────────────────────

    private AthleteSearchResponse toSearchResponse(Athlete a) {
        return AthleteSearchResponse.builder()
                .id(a.getId())
                .name(a.getUser().getName())
                .sport(a.getSport())
                .ageGroup(a.getAgeGroup())
                .city(a.getCity())
                .state(a.getState())
                .photoUrl(a.getPhotoUrl())
                .videoUrl(a.getVideoUrl())
                .latitude(a.getLatitude())
                .longitude(a.getLongitude())
                .build();
    }

    private MapPinResponse toMapPin(Athlete a) {
        return MapPinResponse.builder()
                .id(a.getId())
                .name(a.getUser().getName())
                .sport(a.getSport())
                .city(a.getCity())
                .latitude(a.getLatitude())
                .longitude(a.getLongitude())
                .build();
    }
}
