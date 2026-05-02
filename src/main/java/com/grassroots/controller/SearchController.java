package com.grassroots.controller;

import com.grassroots.dto.AthleteSearchResponse;
import com.grassroots.dto.MapPinResponse;
import com.grassroots.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
@PreAuthorize("hasRole('SCOUT')")
public class SearchController {

    private final SearchService searchService;

    /**
     * Paginated athlete search with optional filters.
     *
     * GET /api/search/athletes?sport=Football&state=Maharashtra&ageGroup=UNDER_19&page=0&size=12
     */
    @GetMapping("/athletes")
    public ResponseEntity<Page<AthleteSearchResponse>> searchAthletes(
            @RequestParam(required = false)               String sport,
            @RequestParam(required = false)               String state,
            @RequestParam(required = false)               String ageGroup,
            @RequestParam(defaultValue = "0")             int page,
            @RequestParam(defaultValue = "12")            int size
    ) {
        return ResponseEntity.ok(searchService.searchAthletes(sport, state, ageGroup, page, size));
    }

    /**
     * All athletes with coordinates, for rendering map pins.
     *
     * GET /api/search/athletes/map
     */
    @GetMapping("/athletes/map")
    public ResponseEntity<List<MapPinResponse>> getMapPins() {
        return ResponseEntity.ok(searchService.getMapPins());
    }
}
