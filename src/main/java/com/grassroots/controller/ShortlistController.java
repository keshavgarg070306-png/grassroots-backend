package com.grassroots.controller;

import com.grassroots.dto.ShortlistRequest;
import com.grassroots.dto.ShortlistResponse;
import com.grassroots.service.ShortlistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scout/shortlist")
@RequiredArgsConstructor
@Tag(name = "Shortlist", description = "Scout shortlist management")
@SecurityRequirement(name = "bearerAuth")
public class ShortlistController {

    private final ShortlistService shortlistService;

    @PostMapping("/{athleteId}")
    @PreAuthorize("hasRole('SCOUT')")
    @Operation(summary = "Add an athlete to the scout's shortlist")
    public ResponseEntity<ShortlistResponse> addToShortlist(
            @PathVariable Long athleteId,
            @RequestBody(required = false) ShortlistRequest request
    ) {
        return ResponseEntity.ok(shortlistService.addToShortlist(athleteId, request));
    }

    @DeleteMapping("/{athleteId}")
    @PreAuthorize("hasRole('SCOUT')")
    @Operation(summary = "Remove an athlete from the scout's shortlist")
    public ResponseEntity<Void> removeFromShortlist(@PathVariable Long athleteId) {
        shortlistService.removeFromShortlist(athleteId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('SCOUT')")
    @Operation(summary = "Get all shortlisted athletes for the current scout")
    public ResponseEntity<List<ShortlistResponse>> getMyShortlist() {
        return ResponseEntity.ok(shortlistService.getMyShortlist());
    }

    @GetMapping("/athlete")
    @PreAuthorize("hasRole('ATHLETE')")
    @Operation(summary = "Get all scouts who shortlisted the current athlete")
    public ResponseEntity<List<ShortlistResponse>> getScoutsWhoShortlistedMe() {
        return ResponseEntity.ok(shortlistService.getScoutsWhoShortlistedMe());
    }
}
