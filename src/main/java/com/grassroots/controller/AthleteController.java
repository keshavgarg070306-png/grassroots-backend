package com.grassroots.controller;

import com.grassroots.dto.AthleteProfileRequest;
import com.grassroots.dto.AthleteProfileResponse;
import com.grassroots.service.AthleteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/athletes")
@RequiredArgsConstructor
public class AthleteController {

    private final AthleteService athleteService;

    /**
     * Create or update the authenticated athlete's profile.
     * Only accessible by users with role ATHLETE.
     */
    @PostMapping("/profile")
    @PreAuthorize("hasRole('ATHLETE')")
    public ResponseEntity<AthleteProfileResponse> createOrUpdateProfile(
            @Valid @RequestBody AthleteProfileRequest request) {
        return ResponseEntity.ok(athleteService.createOrUpdateProfile(request));
    }

    /**
     * Retrieve any athlete's public profile by their athlete ID.
     * Public endpoint — no authentication required.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AthleteProfileResponse> getAthleteById(@PathVariable Long id) {
        return ResponseEntity.ok(athleteService.getAthleteById(id));
    }

    /**
     * Retrieve the currently authenticated athlete's own profile.
     * Only accessible by users with role ATHLETE.
     */
    @GetMapping("/me")
    @PreAuthorize("hasRole('ATHLETE')")
    public ResponseEntity<AthleteProfileResponse> getCurrentAthleteProfile() {
        return ResponseEntity.ok(athleteService.getCurrentAthleteProfile());
    }
}
