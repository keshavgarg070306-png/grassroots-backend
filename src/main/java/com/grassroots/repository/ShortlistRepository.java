package com.grassroots.repository;

import com.grassroots.entity.Shortlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShortlistRepository extends JpaRepository<Shortlist, Long> {

    List<Shortlist> findByScoutId(Long scoutId);

    Optional<Shortlist> findByScoutIdAndAthleteId(Long scoutId, Long athleteId);

    boolean existsByScoutIdAndAthleteId(Long scoutId, Long athleteId);

    void deleteByScoutIdAndAthleteId(Long scoutId, Long athleteId);
}
