package com.tennis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * HeadToHead entity representing the head-to-head record between two players
 * Stores match history and statistics between specific player pairs
 */
@Entity
@Table(name = "head_to_head")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeadToHead {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "player1_id", nullable = false)
    @JsonIgnore
    private Player player1;
    
    @ManyToOne
    @JoinColumn(name = "player2_id", nullable = false)
    @JsonIgnore
    private Player player2;
    
    @Column(name = "total_matches")
    private Integer totalMatches;
    
    @Column(name = "player1_wins")
    private Integer player1Wins;
    
    @Column(name = "player2_wins")
    private Integer player2Wins;
    
    // Surface-specific head-to-head records
    @Column(name = "hard_court_matches")
    private Integer hardCourtMatches;
    
    @Column(name = "hard_court_player1_wins")
    private Integer hardCourtPlayer1Wins;
    
    @Column(name = "clay_court_matches")
    private Integer clayCourtMatches;
    
    @Column(name = "clay_court_player1_wins")
    private Integer clayCourtPlayer1Wins;
    
    @Column(name = "grass_court_matches")
    private Integer grassCourtMatches;
    
    @Column(name = "grass_court_player1_wins")
    private Integer grassCourtPlayer1Wins;
    
    @Column(name = "last_match_date")
    private LocalDateTime lastMatchDate;
    
    @Column(name = "last_match_tournament")
    private String lastMatchTournament;
    
    @Column(name = "last_match_surface")
    private String lastMatchSurface;
    
    @Column(name = "last_match_winner")
    private String lastMatchWinner; // "player1" or "player2"
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
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
     * Calculate player1's win rate against player2
     * @return win rate as percentage
     */
    public Double getPlayer1WinRate() {
        if (totalMatches == 0) return 0.0;
        return (double) player1Wins / totalMatches * 100;
    }
    
    /**
     * Calculate player2's win rate against player1
     * @return win rate as percentage
     */
    public Double getPlayer2WinRate() {
        if (totalMatches == 0) return 0.0;
        return (double) player2Wins / totalMatches * 100;
    }
    
    /**
     * Get head-to-head record for a specific surface
     * @param surface the surface type
     * @return win rate for player1 on that surface
     */
    public Double getSurfaceWinRate(String surface) {
        switch (surface.toLowerCase()) {
            case "hard":
                return hardCourtMatches > 0 ? (double) hardCourtPlayer1Wins / hardCourtMatches * 100 : 0.0;
            case "clay":
                return clayCourtMatches > 0 ? (double) clayCourtPlayer1Wins / clayCourtMatches * 100 : 0.0;
            case "grass":
                return grassCourtMatches > 0 ? (double) grassCourtPlayer1Wins / grassCourtMatches * 100 : 0.0;
            default:
                return getPlayer1WinRate();
        }
    }
}