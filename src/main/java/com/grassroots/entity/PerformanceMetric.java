package com.grassroots.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "performance_metrics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "athlete_id", nullable = false)
    private Athlete athlete;

    @Column(nullable = false)
    private String metricType; // e.g., SPEED, ACCURACY, VERTICAL

    @Column(name = "metric_value", nullable = false)
    private Double metricValue;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime timestamp;
}
