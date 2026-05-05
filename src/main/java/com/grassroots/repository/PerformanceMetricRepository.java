package com.grassroots.repository;

import com.grassroots.entity.Athlete;
import com.grassroots.entity.PerformanceMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceMetricRepository extends JpaRepository<PerformanceMetric, Long> {
    List<PerformanceMetric> findByAthleteOrderByTimestampDesc(Athlete athlete);
    List<PerformanceMetric> findByAthleteAndMetricTypeOrderByTimestampDesc(Athlete athlete, String metricType);
}
