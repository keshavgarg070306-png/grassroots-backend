package com.grassroots.service;

import com.grassroots.dto.ReelResponse;
import com.grassroots.entity.*;
import com.grassroots.repository.InteractionRepository;
import com.grassroots.repository.ReelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReelService {

    private final ReelRepository reelRepository;
    private final InteractionRepository interactionRepository;

    public List<ReelResponse> getTalentFeed(User currentUser) {
        List<Reel> reels = reelRepository.findAllByOrderByCreatedAtDesc();
        return reels.stream()
                .map(reel -> mapToResponse(reel, currentUser))
                .collect(Collectors.toList());
    }

    @Transactional
    public void toggleLike(Long reelId, User user) {
        Reel reel = reelRepository.findById(reelId)
                .orElseThrow(() -> new RuntimeException("Reel not found"));

        interactionRepository.findByUserAndReelAndType(user, reel, InteractionType.LIKE)
                .ifPresentOrElse(
                        interaction -> {
                            interactionRepository.delete(interaction);
                            reel.setLikesCount(Math.max(0, reel.getLikesCount() - 1));
                        },
                        () -> {
                            Interaction interaction = Interaction.builder()
                                    .user(user)
                                    .reel(reel)
                                    .type(InteractionType.LIKE)
                                    .build();
                            interactionRepository.save(interaction);
                            reel.setLikesCount(reel.getLikesCount() + 1);
                        }
                );
        reelRepository.save(reel);
    }

    private ReelResponse mapToResponse(Reel reel, User currentUser) {
        boolean isLiked = false;
        if (currentUser != null) {
            isLiked = interactionRepository.existsByUserAndReelAndType(currentUser, reel, InteractionType.LIKE);
        }

        return ReelResponse.builder()
                .id(reel.getId())
                .athleteId(reel.getAthlete().getId())
                .athleteName(reel.getAthlete().getUser().getName())
                .videoUrl(reel.getVideoUrl())
                .description(reel.getDescription())
                .aiMetadata(reel.getAiMetadata())
                .likesCount(reel.getLikesCount())
                .sharesCount(reel.getSharesCount())
                .isLikedByMe(isLiked)
                .createdAt(reel.getCreatedAt())
                .build();
    }
}
