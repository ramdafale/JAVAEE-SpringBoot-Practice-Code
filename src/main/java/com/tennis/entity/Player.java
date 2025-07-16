package com.tennis.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Player entity representing a tennis player
 * Stores comprehensive player information including rankings, statistics, and performance data
 */
@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(nullable = false)
    private String country;
    
    @Column(name = "current_ranking")
    private Integer currentRanking;
    
    @Column(name = "career_high_ranking")
    private Integer careerHighRanking;
    
    @Column(name = "age")
    private Integer age;
    
    @Column(name = "height_cm")
    private Integer heightCm;
    
    @Column(name = "weight_kg")
    private Integer weightKg;
    
    @Column(name = "playing_style")
    private String playingStyle; // e.g., "Aggressive Baseline", "Serve and Volley"
    
    @Column(name = "preferred_hand")
    private String preferredHand; // "Right", "Left"
    
    // Surface Performance Statistics (0.0 to 1.0)
    @Column(name = "hard_court_win_rate")
    private Double hardCourtWinRate;
    
    @Column(name = "clay_court_win_rate")
    private Double clayCourtWinRate;
    
    @Column(name = "grass_court_win_rate")
    private Double grassCourtWinRate;
    
    // Service Statistics
    @Column(name = "first_serve_percentage")
    private Double firstServePercentage;
    
    @Column(name = "first_serve_win_rate")
    private Double firstServeWinRate;
    
    @Column(name = "second_serve_win_rate")
    private Double secondServeWinRate;
    
    @Column(name = "aces_per_match")
    private Double acesPerMatch;
    
    @Column(name = "double_faults_per_match")
    private Double doubleFaultsPerMatch;
    
    // Return Statistics
    @Column(name = "first_serve_return_win_rate")
    private Double firstServeReturnWinRate;
    
    @Column(name = "second_serve_return_win_rate")
    private Double secondServeReturnWinRate;
    
    @Column(name = "break_points_converted_percentage")
    private Double breakPointsConvertedPercentage;
    
    // Recent Form (last 10 matches)
    @Column(name = "recent_form_win_rate")
    private Double recentFormWinRate;
    
    @Column(name = "matches_played_this_year")
    private Integer matchesPlayedThisYear;
    
    @Column(name = "wins_this_year")
    private Integer winsThisYear;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Relationships
    @OneToMany(mappedBy = "player1", cascade = CascadeType.ALL)
    private List<HeadToHead> headToHeadAsPlayer1;
    
    @OneToMany(mappedBy = "player2", cascade = CascadeType.ALL)
    private List<HeadToHead> headToHeadAsPlayer2;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    /**
     * Calculate overall win rate for the player
     * @return overall win rate as percentage
     */
    public Double getOverallWinRate() {
        if (matchesPlayedThisYear == 0) return 0.0;
        return (double) winsThisYear / matchesPlayedThisYear * 100;
    }
    
    /**
     * Get best surface for the player
     * @return surface name with highest win rate
     */
    public String getBestSurface() {
        if (hardCourtWinRate >= clayCourtWinRate && hardCourtWinRate >= grassCourtWinRate) {
            return "Hard Court";
        } else if (clayCourtWinRate >= grassCourtWinRate) {
            return "Clay Court";
        } else {
            return "Grass Court";
        }
    }
}