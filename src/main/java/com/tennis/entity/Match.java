package com.tennis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Match entity representing a tennis match
 * Stores live match data, scores, and current match state
 */
@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "player1_id", nullable = false)
    private Player player1;
    
    @ManyToOne
    @JoinColumn(name = "player2_id", nullable = false)
    private Player player2;
    
    @Column(name = "tournament_name")
    private String tournamentName;
    
    @Column(name = "match_type")
    private String matchType; // "Best of 3", "Best of 5"
    
    @Column(name = "surface")
    private String surface; // "Hard", "Clay", "Grass"
    
    @Column(name = "match_status")
    private String matchStatus; // "Scheduled", "Live", "Completed", "Cancelled"
    
    @Column(name = "start_time")
    private LocalDateTime startTime;
    
    @Column(name = "end_time")
    private LocalDateTime endTime;
    
    // Current Score
    @Column(name = "player1_sets_won")
    private Integer player1SetsWon = 0;
    
    @Column(name = "player2_sets_won")
    private Integer player2SetsWon = 0;
    
    @Column(name = "current_set")
    private Integer currentSet = 1;
    
    @Column(name = "player1_games_current_set")
    private Integer player1GamesCurrentSet = 0;
    
    @Column(name = "player2_games_current_set")
    private Integer player2GamesCurrentSet = 0;
    
    @Column(name = "player1_points_current_game")
    private Integer player1PointsCurrentGame = 0;
    
    @Column(name = "player2_points_current_game")
    private Integer player2PointsCurrentGame = 0;
    
    @Column(name = "current_server")
    private String currentServer; // "player1" or "player2"
    
    // Live Statistics
    @Column(name = "player1_aces")
    private Integer player1Aces = 0;
    
    @Column(name = "player2_aces")
    private Integer player2Aces = 0;
    
    @Column(name = "player1_double_faults")
    private Integer player1DoubleFaults = 0;
    
    @Column(name = "player2_double_faults")
    private Integer player2DoubleFaults = 0;
    
    @Column(name = "player1_first_serve_percentage")
    private Double player1FirstServePercentage = 0.0;
    
    @Column(name = "player2_first_serve_percentage")
    private Double player2FirstServePercentage = 0.0;
    
    @Column(name = "player1_break_points_won")
    private Integer player1BreakPointsWon = 0;
    
    @Column(name = "player2_break_points_won")
    private Integer player2BreakPointsWon = 0;
    
    @Column(name = "player1_break_points_opportunities")
    private Integer player1BreakPointsOpportunities = 0;
    
    @Column(name = "player2_break_points_opportunities")
    private Integer player2BreakPointsOpportunities = 0;
    
    @Column(name = "player1_total_points_won")
    private Integer player1TotalPointsWon = 0;
    
    @Column(name = "player2_total_points_won")
    private Integer player2TotalPointsWon = 0;
    
    @Column(name = "total_points_played")
    private Integer totalPointsPlayed = 0;
    
    // Set History (JSON format for simplicity)
    @Column(name = "set_scores", columnDefinition = "TEXT")
    private String setScores; // JSON array of set scores
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Relationships
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MatchPrediction> predictions;
    
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
     * Get current match score in standard tennis format
     * @return formatted score string
     */
    public String getCurrentScore() {
        return String.format("%d-%d (%d-%d)", 
            player1SetsWon, player2SetsWon,
            player1GamesCurrentSet, player2GamesCurrentSet);
    }
    
    /**
     * Get current game score in tennis points format
     * @return game score (0, 15, 30, 40, AD)
     */
    public String getCurrentGameScore() {
        return String.format("%s-%s", 
            convertPointsToTennisScore(player1PointsCurrentGame),
            convertPointsToTennisScore(player2PointsCurrentGame));
    }
    
    /**
     * Convert points to tennis score format
     * @param points number of points
     * @return tennis score string
     */
    private String convertPointsToTennisScore(Integer points) {
        switch (points) {
            case 0: return "0";
            case 1: return "15";
            case 2: return "30";
            case 3: return "40";
            default: return "AD";
        }
    }
    
    /**
     * Check if match is completed
     * @return true if match is finished
     */
    public boolean isCompleted() {
        return "Completed".equals(matchStatus);
    }
    
    /**
     * Check if match is live
     * @return true if match is currently being played
     */
    public boolean isLive() {
        return "Live".equals(matchStatus);
    }
    
    /**
     * Get match winner
     * @return player name or null if match not completed
     */
    public String getWinner() {
        if (!isCompleted()) return null;
        return player1SetsWon > player2SetsWon ? player1.getName() : player2.getName();
    }
}