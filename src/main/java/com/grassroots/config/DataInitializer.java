package com.grassroots.config;

import com.grassroots.entity.*;
import com.grassroots.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AthleteRepository athleteRepository;
    private final ReelRepository reelRepository;
    private final PerformanceMetricRepository metricRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) return;

        // 1. Create Athletes
        User u1 = User.builder()
                .name("Ranjit Bajaj")
                .email("ranjit@example.com")
                .passwordHash(passwordEncoder.encode("password"))
                .role(Role.ATHLETE)
                .build();
        userRepository.save(u1);

        Athlete a1 = Athlete.builder()
                .user(u1)
                .sport("Football")
                .ageGroup(AgeGroup.U19)
                .city("Chandigarh")
                .state("Punjab")
                .bio("Top prospect forward with explosive speed and clinical finishing.")
                .build();
        athleteRepository.save(a1);

        User u2 = User.builder()
                .name("Arjun Singh")
                .email("arjun@example.com")
                .passwordHash(passwordEncoder.encode("password"))
                .role(Role.ATHLETE)
                .build();
        userRepository.save(u2);

        Athlete a2 = Athlete.builder()
                .user(u2)
                .sport("Basketball")
                .ageGroup(AgeGroup.U17)
                .city("Mumbai")
                .state("Maharashtra")
                .bio("Elite point guard with exceptional vision and ball handling.")
                .build();
        athleteRepository.save(a2);


        // 2. Create Reels
        Reel r1 = Reel.builder()
                .athlete(a1)
                .videoUrl("https://res.cloudinary.com/demo/video/upload/v1631530605/sample.mp4")
                .description("Highlights from the weekend game #topbins")
                .likesCount(1240)
                .aiMetadata("{\"skeletons\": []}") // Mock JSON
                .build();
        reelRepository.save(r1);

        // 3. Create Performance Metrics
        metricRepository.save(PerformanceMetric.builder().athlete(a1).metricType("SPEED").metricValue(94.2).build());
        metricRepository.save(PerformanceMetric.builder().athlete(a1).metricType("ACCURACY").metricValue(88.5).build());
        metricRepository.save(PerformanceMetric.builder().athlete(a1).metricType("VERTICAL").metricValue(42.5).build());

        // 4. Create Scouts
        User s1 = User.builder()
                .name("Senior Scout Jose")
                .email("jose@gmail.com")
                .passwordHash(passwordEncoder.encode("password"))
                .role(Role.SCOUT)
                .build();
        userRepository.save(s1);

        System.out.println("Mock data initialized (including Scouts)!");
    }
}
