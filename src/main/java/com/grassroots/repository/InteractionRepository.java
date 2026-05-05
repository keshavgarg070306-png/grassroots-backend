package com.grassroots.repository;

import com.grassroots.entity.Interaction;
import com.grassroots.entity.InteractionType;
import com.grassroots.entity.Reel;
import com.grassroots.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    Optional<Interaction> findByUserAndReelAndType(User user, Reel reel, InteractionType type);
    boolean existsByUserAndReelAndType(User user, Reel reel, InteractionType type);
}
