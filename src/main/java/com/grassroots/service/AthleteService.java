package com.grassroots.service;

import com.grassroots.dto.AthleteProfileRequest;
import com.grassroots.dto.AthleteProfileResponse;
import com.grassroots.entity.Athlete;
import com.grassroots.entity.User;
import com.grassroots.repository.AthleteRepository;
import com.grassroots.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AthleteService {

    private final AthleteRepository athleteRepository;
    private final UserRepository userRepository;

    public AthleteProfileResponse createOrUpdateProfile(AthleteProfileRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Athlete athlete = athleteRepository.findByUserId(user.getId())
                .orElse(Athlete.builder().user(user).build());

        athlete.setSport(request.getSport());
        athlete.setAgeGroup(request.getAgeGroup());
        athlete.setCity(request.getCity());
        athlete.setState(request.getState());
        athlete.setBio(request.getBio());
        athlete.setVideoUrl(request.getVideoUrl());
        athlete.setPhotoUrl(request.getPhotoUrl());
        athlete.setLatitude(request.getLatitude());
        athlete.setLongitude(request.getLongitude());

        return toResponse(athleteRepository.save(athlete));
    }

    public AthleteProfileResponse getAthleteById(Long id) {
        Athlete athlete = athleteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Athlete profile not found with id: " + id));
        return toResponse(athlete);
    }

    public AthleteProfileResponse getCurrentAthleteProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Athlete athlete = athleteRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("No profile found for current user"));

        return toResponse(athlete);
    }

    private AthleteProfileResponse toResponse(Athlete athlete) {
        return AthleteProfileResponse.builder()
                .id(athlete.getId())
                .userId(athlete.getUser().getId())
                .name(athlete.getUser().getName())
                .email(athlete.getUser().getEmail())
                .sport(athlete.getSport())
                .ageGroup(athlete.getAgeGroup())
                .city(athlete.getCity())
                .state(athlete.getState())
                .bio(athlete.getBio())
                .videoUrl(athlete.getVideoUrl())
                .photoUrl(athlete.getPhotoUrl())
                .latitude(athlete.getLatitude())
                .longitude(athlete.getLongitude())
                .createdAt(athlete.getCreatedAt())
                .build();
    }
}
