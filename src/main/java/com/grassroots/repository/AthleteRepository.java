package com.grassroots.repository;

import com.grassroots.entity.AgeGroup;
import com.grassroots.entity.Athlete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    Optional<Athlete> findByUserId(Long userId);
    Optional<Athlete> findByUserEmail(String email);

    /**
     * Optional multi-filter search. A null value for any param disables that filter.
     */
    @Query("""
            SELECT a FROM Athlete a
            WHERE (:sport    IS NULL OR LOWER(a.sport) = LOWER(:sport))
              AND (:state    IS NULL OR LOWER(a.state) = LOWER(:state))
              AND (:ageGroup IS NULL OR a.ageGroup = :ageGroup)
            """)
    Page<Athlete> searchAthletes(
            @Param("sport")    String sport,
            @Param("state")    String state,
            @Param("ageGroup") AgeGroup ageGroup,
            Pageable pageable
    );

    /**
     * Lightweight fetch for map pins — all athletes that have coordinates.
     */
    @Query("""
            SELECT a FROM Athlete a
            WHERE a.latitude IS NOT NULL AND a.longitude IS NOT NULL
            """)
    List<Athlete> findAllForMap();
}
