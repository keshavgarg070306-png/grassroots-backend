package com.grassroots.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "interactions", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "reel_id", "type"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reel_id", nullable = false)
    private Reel reel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InteractionType type;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
