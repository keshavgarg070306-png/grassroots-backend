package com.grassroots.controller;

import com.grassroots.dto.ReelResponse;
import com.grassroots.entity.User;
import com.grassroots.repository.UserRepository;
import com.grassroots.service.ReelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reels")
@RequiredArgsConstructor
@Tag(name = "Reels", description = "Talent Feed and Reel interactions")
@SecurityRequirement(name = "bearerAuth")
public class ReelController {

    private final ReelService reelService;
    private final UserRepository userRepository;

    @GetMapping
    @Operation(summary = "Get the talent feed (all reels)")
    public ResponseEntity<List<ReelResponse>> getFeed() {
        User user = getCurrentUser();
        return ResponseEntity.ok(reelService.getTalentFeed(user));
    }

    @PostMapping("/{id}/like")
    @Operation(summary = "Toggle like on a reel")
    public ResponseEntity<Void> toggleLike(@PathVariable Long id) {
        User user = getCurrentUser();
        if (user == null) return ResponseEntity.status(401).build();
        reelService.toggleLike(id, user);
        return ResponseEntity.ok().build();
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElse(null);
    }
}
