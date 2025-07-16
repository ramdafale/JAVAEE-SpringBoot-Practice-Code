package com.tennis.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * MatchPrediction entity representing prediction results for a tennis match
 * Stores various types of predictions with confidence scores
 */
@Entity
@Table(name = "match_predictions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchPrediction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;
    
    @Column(name = "prediction_type")
    private String predictionType; // "MATCH_WINNER", "CURRENT_GAME_WINNER", "CURRENT_SET_WINNER"
    
    @Column(name = "predicted_winner")
    private String predictedWinner; // "player1", "player2"
    
    @Column(name = "confidence_score")
    private Double confidenceScore; // 0.0 to 1.0
    
    @Column(name = "player1_win_probability")
    private Double player1WinProbability; // 0.0 to 1.0
    
    @Column(name = "player2_win_probability")
    private Double player2WinProbability; // 0.0 to 1.0
    
    // Factors that influenced the prediction
    @Column(name = "ranking_factor")
    private Double rankingFactor;
    
    @Column(name = "head_to_head_factor")
    private Double headToHeadFactor;
    
    @Column(name = "surface_factor")
    private Double surfaceFactor;
    
    @Column(name = "recent_form_factor")
    private Double recentFormFactor;
    
    @Column(name = "live_stats_factor")
    private Double liveStatsFactor;
    
    @Column(name = "momentum_factor")
    private Double momentumFactor;
    
    @Column(name = "prediction_reasoning")
    private String predictionReasoning; // Detailed explanation of the prediction
    
    @Column(name = "prediction_timestamp")
    private LocalDateTime predictionTimestamp;
    
    @Column(name = "is_correct")
    private Boolean isCorrect; // null if prediction not yet verified
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        predictionTimestamp = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    /**
     * Get the predicted winner's name
     * @return player name
     */
    public String getPredictedWinnerName() {
        if ("player1".equals(predictedWinner)) {
            return match.getPlayer1().getName();
        } else if ("player2".equals(predictedWinner)) {
            return match.getPlayer2().getName();
        }
        return "Unknown";
    }
    
    /**
     * Get confidence level as a string
     * @return confidence level description
     */
    public String getConfidenceLevel() {
        if (confidenceScore >= 0.8) return "Very High";
        if (confidenceScore >= 0.6) return "High";
        if (confidenceScore >= 0.4) return "Medium";
        if (confidenceScore >= 0.2) return "Low";
        return "Very Low";
    }
    
    /**
     * Get the most influential factor
     * @return factor name with highest weight
     */
    public String getMostInfluentialFactor() {
        Double maxFactor = Math.max(Math.max(rankingFactor, headToHeadFactor), 
                                  Math.max(surfaceFactor, recentFormFactor));
        maxFactor = Math.max(maxFactor, Math.max(liveStatsFactor, momentumFactor));
        
        if (maxFactor.equals(rankingFactor)) return "Player Ranking";
        if (maxFactor.equals(headToHeadFactor)) return "Head-to-Head Record";
        if (maxFactor.equals(surfaceFactor)) return "Surface Performance";
        if (maxFactor.equals(recentFormFactor)) return "Recent Form";
        if (maxFactor.equals(liveStatsFactor)) return "Live Match Statistics";
        if (maxFactor.equals(momentumFactor)) return "Match Momentum";
        
        return "Unknown";
    }
    
    /**
     * Check if prediction is verified
     * @return true if prediction has been verified
     */
    public boolean isVerified() {
        return isCorrect != null;
    }
}