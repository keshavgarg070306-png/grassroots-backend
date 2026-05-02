package com.grassroots.service;

import com.grassroots.dto.ShortlistRequest;
import com.grassroots.dto.ShortlistResponse;
import com.grassroots.entity.Athlete;
import com.grassroots.entity.Shortlist;
import com.grassroots.entity.User;
import com.grassroots.repository.AthleteRepository;
import com.grassroots.repository.ShortlistRepository;
import com.grassroots.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShortlistService {

    private final ShortlistRepository shortlistRepository;
    private final AthleteRepository   athleteRepository;
    private final UserRepository      userRepository;

    public ShortlistResponse addToShortlist(Long athleteId, ShortlistRequest request) {
        User scout   = currentUser();
        Athlete athlete = athleteRepository.findById(athleteId)
                .orElseThrow(() -> new RuntimeException("Athlete not found: " + athleteId));

        if (shortlistRepository.existsByScoutIdAndAthleteId(scout.getId(), athleteId)) {
            throw new RuntimeException("Athlete is already in your shortlist");
        }

        Shortlist entry = Shortlist.builder()
                .scout(scout)
                .athlete(athlete)
                .note(request != null ? request.getNote() : null)
                .build();

        return toResponse(shortlistRepository.save(entry));
    }

    @Transactional
    public void removeFromShortlist(Long athleteId) {
        User scout = currentUser();
        if (!shortlistRepository.existsByScoutIdAndAthleteId(scout.getId(), athleteId)) {
            throw new RuntimeException("Athlete is not in your shortlist");
        }
        shortlistRepository.deleteByScoutIdAndAthleteId(scout.getId(), athleteId);
    }

    public List<ShortlistResponse> getMyShortlist() {
        User scout = currentUser();
        return shortlistRepository.findByScoutId(scout.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    private User currentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private ShortlistResponse toResponse(Shortlist s) {
        Athlete a = s.getAthlete();
        return ShortlistResponse.builder()
                .shortlistId(s.getId())
                .note(s.getNote())
                .shortlistedAt(s.getCreatedAt())
                .athleteId(a.getId())
                .athleteName(a.getUser().getName())
                .sport(a.getSport())
                .ageGroup(a.getAgeGroup())
                .city(a.getCity())
                .state(a.getState())
                .photoUrl(a.getPhotoUrl())
                .build();
    }
}
