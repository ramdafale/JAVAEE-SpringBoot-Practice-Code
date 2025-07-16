package com.tennis.service;

import com.tennis.entity.*;
import com.tennis.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class for tennis match predictions
 * Implements algorithms for predicting match winner, current game winner, and current set winner
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PredictionService {
    
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final MatchPredictionRepository predictionRepository;
    private final HeadToHeadRepository headToHeadRepository;
    
    // Weight factors for different prediction components
    private static final double RANKING_WEIGHT = 0.15;
    private static final double HEAD_TO_HEAD_WEIGHT = 0.20;
    private static final double SURFACE_WEIGHT = 0.15;
    private static final double RECENT_FORM_WEIGHT = 0.15;
    private static final double LIVE_STATS_WEIGHT = 0.25;
    private static final double MOMENTUM_WEIGHT = 0.10;
    
    /**
     * Predict match winner
     * @param match the match to predict
     * @return prediction result
     */
    public MatchPrediction predictMatchWinner(Match match) {
        log.info("Predicting match winner for match: {} vs {}", 
                match.getPlayer1().getName(), match.getPlayer2().getName());
        
        // Calculate base probabilities
        double player1Probability = calculatePlayer1WinProbability(match);
        double player2Probability = 1.0 - player1Probability;
        
        // Determine predicted winner
        String predictedWinner = player1Probability > player2Probability ? "player1" : "player2";
        double confidenceScore = Math.max(player1Probability, player2Probability);
        
        // Calculate factor weights
        Map<String, Double> factors = calculatePredictionFactors(match);
        
        // Create prediction reasoning
        String reasoning = buildPredictionReasoning(match, factors, predictedWinner, confidenceScore);
        
        // Create and save prediction
        MatchPrediction prediction = new MatchPrediction();
        prediction.setMatch(match);
        prediction.setPredictionType("MATCH_WINNER");
        prediction.setPredictedWinner(predictedWinner);
        prediction.setConfidenceScore(confidenceScore);
        prediction.setPlayer1WinProbability(player1Probability);
        prediction.setPlayer2WinProbability(player2Probability);
        prediction.setRankingFactor(factors.get("ranking"));
        prediction.setHeadToHeadFactor(factors.get("headToHead"));
        prediction.setSurfaceFactor(factors.get("surface"));
        prediction.setRecentFormFactor(factors.get("recentForm"));
        prediction.setLiveStatsFactor(factors.get("liveStats"));
        prediction.setMomentumFactor(factors.get("momentum"));
        prediction.setPredictionReasoning(reasoning);
        
        return predictionRepository.save(prediction);
    }
    
    /**
     * Predict current game winner
     * @param match the match to predict
     * @return prediction result
     */
    public MatchPrediction predictCurrentGameWinner(Match match) {
        log.info("Predicting current game winner for match: {} vs {}", 
                match.getPlayer1().getName(), match.getPlayer2().getName());
        
        // For game prediction, focus more on serving and current momentum
        double player1Probability = calculateGameWinProbability(match, true);
        double player2Probability = 1.0 - player1Probability;
        
        String predictedWinner = player1Probability > player2Probability ? "player1" : "player2";
        double confidenceScore = Math.max(player1Probability, player2Probability);
        
        Map<String, Double> factors = calculateGamePredictionFactors(match);
        String reasoning = buildGamePredictionReasoning(match, factors, predictedWinner, confidenceScore);
        
        MatchPrediction prediction = new MatchPrediction();
        prediction.setMatch(match);
        prediction.setPredictionType("CURRENT_GAME_WINNER");
        prediction.setPredictedWinner(predictedWinner);
        prediction.setConfidenceScore(confidenceScore);
        prediction.setPlayer1WinProbability(player1Probability);
        prediction.setPlayer2WinProbability(player2Probability);
        prediction.setRankingFactor(factors.get("ranking"));
        prediction.setHeadToHeadFactor(factors.get("headToHead"));
        prediction.setSurfaceFactor(factors.get("surface"));
        prediction.setRecentFormFactor(factors.get("recentForm"));
        prediction.setLiveStatsFactor(factors.get("liveStats"));
        prediction.setMomentumFactor(factors.get("momentum"));
        prediction.setPredictionReasoning(reasoning);
        
        return predictionRepository.save(prediction);
    }
    
    /**
     * Predict current set winner
     * @param match the match to predict
     * @return prediction result
     */
    public MatchPrediction predictCurrentSetWinner(Match match) {
        log.info("Predicting current set winner for match: {} vs {}", 
                match.getPlayer1().getName(), match.getPlayer2().getName());
        
        double player1Probability = calculateSetWinProbability(match, true);
        double player2Probability = 1.0 - player1Probability;
        
        String predictedWinner = player1Probability > player2Probability ? "player1" : "player2";
        double confidenceScore = Math.max(player1Probability, player2Probability);
        
        Map<String, Double> factors = calculateSetPredictionFactors(match);
        String reasoning = buildSetPredictionReasoning(match, factors, predictedWinner, confidenceScore);
        
        MatchPrediction prediction = new MatchPrediction();
        prediction.setMatch(match);
        prediction.setPredictionType("CURRENT_SET_WINNER");
        prediction.setPredictedWinner(predictedWinner);
        prediction.setConfidenceScore(confidenceScore);
        prediction.setPlayer1WinProbability(player1Probability);
        prediction.setPlayer2WinProbability(player2Probability);
        prediction.setRankingFactor(factors.get("ranking"));
        prediction.setHeadToHeadFactor(factors.get("headToHead"));
        prediction.setSurfaceFactor(factors.get("surface"));
        prediction.setRecentFormFactor(factors.get("recentForm"));
        prediction.setLiveStatsFactor(factors.get("liveStats"));
        prediction.setMomentumFactor(factors.get("momentum"));
        prediction.setPredictionReasoning(reasoning);
        
        return predictionRepository.save(prediction);
    }
    
    /**
     * Calculate player1's win probability for the match
     */
    private double calculatePlayer1WinProbability(Match match) {
        Player player1 = match.getPlayer1();
        Player player2 = match.getPlayer2();
        
        // Base probability from rankings
        double rankingProb = calculateRankingProbability(player1, player2);
        
        // Head-to-head probability
        double h2hProb = calculateHeadToHeadProbability(player1, player2, match.getSurface());
        
        // Surface performance probability
        double surfaceProb = calculateSurfaceProbability(player1, player2, match.getSurface());
        
        // Recent form probability
        double formProb = calculateRecentFormProbability(player1, player2);
        
        // Live stats probability
        double liveStatsProb = calculateLiveStatsProbability(match);
        
        // Momentum probability
        double momentumProb = calculateMomentumProbability(match);
        
        // Weighted average
        double weightedProb = (rankingProb * RANKING_WEIGHT) +
                            (h2hProb * HEAD_TO_HEAD_WEIGHT) +
                            (surfaceProb * SURFACE_WEIGHT) +
                            (formProb * RECENT_FORM_WEIGHT) +
                            (liveStatsProb * LIVE_STATS_WEIGHT) +
                            (momentumProb * MOMENTUM_WEIGHT);
        
        return Math.max(0.1, Math.min(0.9, weightedProb)); // Clamp between 0.1 and 0.9
    }
    
    /**
     * Calculate game win probability
     */
    private double calculateGameWinProbability(Match match, boolean isPlayer1) {
        // For game prediction, focus more on serving and current game state
        double serveAdvantage = 0.6; // Server has advantage
        double currentScoreFactor = calculateCurrentScoreFactor(match, isPlayer1);
        double serveQualityFactor = calculateServeQualityFactor(match, isPlayer1);
        
        return (serveAdvantage + currentScoreFactor + serveQualityFactor) / 3.0;
    }
    
    /**
     * Calculate set win probability
     */
    private double calculateSetWinProbability(Match match, boolean isPlayer1) {
        // For set prediction, consider current set score and overall match momentum
        double currentSetScoreFactor = calculateCurrentSetScoreFactor(match, isPlayer1);
        double matchMomentumFactor = calculateMatchMomentumFactor(match, isPlayer1);
        double overallMatchProbability = calculatePlayer1WinProbability(match);
        
        if (!isPlayer1) {
            overallMatchProbability = 1.0 - overallMatchProbability;
        }
        
        return (currentSetScoreFactor + matchMomentumFactor + overallMatchProbability) / 3.0;
    }
    
    /**
     * Calculate ranking-based probability
     */
    private double calculateRankingProbability(Player player1, Player player2) {
        if (player1.getCurrentRanking() == null || player2.getCurrentRanking() == null) {
            return 0.5; // Neutral if rankings not available
        }
        
        int rankDiff = player2.getCurrentRanking() - player1.getCurrentRanking();
        // Higher ranked player has advantage
        return 0.5 + (rankDiff * 0.02); // Each ranking difference = 2% advantage
    }
    
    /**
     * Calculate head-to-head probability
     */
    private double calculateHeadToHeadProbability(Player player1, Player player2, String surface) {
        // This would typically query the HeadToHead repository
        // For now, return neutral probability
        return 0.5;
    }
    
    /**
     * Calculate surface performance probability
     */
    private double calculateSurfaceProbability(Player player1, Player player2, String surface) {
        double player1SurfaceRate = getPlayerSurfaceWinRate(player1, surface);
        double player2SurfaceRate = getPlayerSurfaceWinRate(player2, surface);
        
        if (player1SurfaceRate + player2SurfaceRate == 0) {
            return 0.5;
        }
        
        return player1SurfaceRate / (player1SurfaceRate + player2SurfaceRate);
    }
    
    /**
     * Get player's win rate on specific surface
     */
    private double getPlayerSurfaceWinRate(Player player, String surface) {
        switch (surface.toLowerCase()) {
            case "hard":
                return player.getHardCourtWinRate() != null ? player.getHardCourtWinRate() : 0.5;
            case "clay":
                return player.getClayCourtWinRate() != null ? player.getClayCourtWinRate() : 0.5;
            case "grass":
                return player.getGrassCourtWinRate() != null ? player.getGrassCourtWinRate() : 0.5;
            default:
                return 0.5;
        }
    }
    
    /**
     * Calculate recent form probability
     */
    private double calculateRecentFormProbability(Player player1, Player player2) {
        double player1Form = player1.getRecentFormWinRate() != null ? player1.getRecentFormWinRate() : 0.5;
        double player2Form = player2.getRecentFormWinRate() != null ? player2.getRecentFormWinRate() : 0.5;
        
        if (player1Form + player2Form == 0) {
            return 0.5;
        }
        
        return player1Form / (player1Form + player2Form);
    }
    
    /**
     * Calculate live statistics probability
     */
    private double calculateLiveStatsProbability(Match match) {
        if (match.getTotalPointsPlayed() == 0) {
            return 0.5;
        }
        
        double player1PointRate = (double) match.getPlayer1TotalPointsWon() / match.getTotalPointsPlayed();
        return player1PointRate;
    }
    
    /**
     * Calculate momentum probability
     */
    private double calculateMomentumProbability(Match match) {
        // Simple momentum calculation based on recent points won
        // In a real implementation, this would be more sophisticated
        return 0.5;
    }
    
    /**
     * Calculate current score factor for game prediction
     */
    private double calculateCurrentScoreFactor(Match match, boolean isPlayer1) {
        int playerPoints = isPlayer1 ? match.getPlayer1PointsCurrentGame() : match.getPlayer2PointsCurrentGame();
        int opponentPoints = isPlayer1 ? match.getPlayer2PointsCurrentGame() : match.getPlayer1PointsCurrentGame();
        
        if (playerPoints == opponentPoints) return 0.5;
        if (playerPoints > opponentPoints) return 0.7;
        return 0.3;
    }
    
    /**
     * Calculate serve quality factor
     */
    private double calculateServeQualityFactor(Match match, boolean isPlayer1) {
        if ("player1".equals(match.getCurrentServer()) && isPlayer1) {
            return match.getPlayer1FirstServePercentage() != null ? match.getPlayer1FirstServePercentage() / 100.0 : 0.5;
        } else if ("player2".equals(match.getCurrentServer()) && !isPlayer1) {
            return match.getPlayer2FirstServePercentage() != null ? match.getPlayer2FirstServePercentage() / 100.0 : 0.5;
        }
        return 0.5;
    }
    
    /**
     * Calculate current set score factor
     */
    private double calculateCurrentSetScoreFactor(Match match, boolean isPlayer1) {
        int playerGames = isPlayer1 ? match.getPlayer1GamesCurrentSet() : match.getPlayer2GamesCurrentSet();
        int opponentGames = isPlayer1 ? match.getPlayer2GamesCurrentSet() : match.getPlayer1GamesCurrentSet();
        
        if (playerGames == opponentGames) return 0.5;
        if (playerGames > opponentGames) return 0.7;
        return 0.3;
    }
    
    /**
     * Calculate match momentum factor
     */
    private double calculateMatchMomentumFactor(Match match, boolean isPlayer1) {
        int playerSets = isPlayer1 ? match.getPlayer1SetsWon() : match.getPlayer2SetsWon();
        int opponentSets = isPlayer1 ? match.getPlayer2SetsWon() : match.getPlayer1SetsWon();
        
        if (playerSets == opponentSets) return 0.5;
        if (playerSets > opponentSets) return 0.7;
        return 0.3;
    }
    
    /**
     * Calculate prediction factors for detailed analysis
     */
    private Map<String, Double> calculatePredictionFactors(Match match) {
        Map<String, Double> factors = new HashMap<>();
        
        Player player1 = match.getPlayer1();
        Player player2 = match.getPlayer2();
        
        factors.put("ranking", calculateRankingProbability(player1, player2));
        factors.put("headToHead", calculateHeadToHeadProbability(player1, player2, match.getSurface()));
        factors.put("surface", calculateSurfaceProbability(player1, player2, match.getSurface()));
        factors.put("recentForm", calculateRecentFormProbability(player1, player2));
        factors.put("liveStats", calculateLiveStatsProbability(match));
        factors.put("momentum", calculateMomentumProbability(match));
        
        return factors;
    }
    
    /**
     * Calculate game prediction factors
     */
    private Map<String, Double> calculateGamePredictionFactors(Match match) {
        Map<String, Double> factors = new HashMap<>();
        
        // For game prediction, adjust weights
        factors.put("ranking", 0.1);
        factors.put("headToHead", 0.1);
        factors.put("surface", 0.1);
        factors.put("recentForm", 0.1);
        factors.put("liveStats", 0.4); // Higher weight for live stats
        factors.put("momentum", 0.2); // Higher weight for momentum
        
        return factors;
    }
    
    /**
     * Calculate set prediction factors
     */
    private Map<String, Double> calculateSetPredictionFactors(Match match) {
        Map<String, Double> factors = new HashMap<>();
        
        // For set prediction, balance between overall match and current state
        factors.put("ranking", 0.15);
        factors.put("headToHead", 0.15);
        factors.put("surface", 0.15);
        factors.put("recentForm", 0.15);
        factors.put("liveStats", 0.25);
        factors.put("momentum", 0.15);
        
        return factors;
    }
    
    /**
     * Build prediction reasoning
     */
    private String buildPredictionReasoning(Match match, Map<String, Double> factors, String predictedWinner, double confidence) {
        StringBuilder reasoning = new StringBuilder();
        reasoning.append("Match Winner Prediction: ");
        reasoning.append(predictedWinner.equals("player1") ? match.getPlayer1().getName() : match.getPlayer2().getName());
        reasoning.append(" (Confidence: ").append(String.format("%.1f%%", confidence * 100)).append(")\n\n");
        
        reasoning.append("Key Factors:\n");
        reasoning.append("- Ranking Factor: ").append(String.format("%.1f%%", factors.get("ranking") * 100)).append("\n");
        reasoning.append("- Head-to-Head: ").append(String.format("%.1f%%", factors.get("headToHead") * 100)).append("\n");
        reasoning.append("- Surface Performance: ").append(String.format("%.1f%%", factors.get("surface") * 100)).append("\n");
        reasoning.append("- Recent Form: ").append(String.format("%.1f%%", factors.get("recentForm") * 100)).append("\n");
        reasoning.append("- Live Statistics: ").append(String.format("%.1f%%", factors.get("liveStats") * 100)).append("\n");
        reasoning.append("- Match Momentum: ").append(String.format("%.1f%%", factors.get("momentum") * 100));
        
        return reasoning.toString();
    }
    
    /**
     * Build game prediction reasoning
     */
    private String buildGamePredictionReasoning(Match match, Map<String, Double> factors, String predictedWinner, double confidence) {
        StringBuilder reasoning = new StringBuilder();
        reasoning.append("Current Game Winner Prediction: ");
        reasoning.append(predictedWinner.equals("player1") ? match.getPlayer1().getName() : match.getPlayer2().getName());
        reasoning.append(" (Confidence: ").append(String.format("%.1f%%", confidence * 100)).append(")\n\n");
        
        reasoning.append("Game-specific Factors:\n");
        reasoning.append("- Current Server: ").append("player1".equals(match.getCurrentServer()) ? match.getPlayer1().getName() : match.getPlayer2().getName()).append("\n");
        reasoning.append("- Serve Quality: ").append(String.format("%.1f%%", factors.get("liveStats") * 100)).append("\n");
        reasoning.append("- Game Momentum: ").append(String.format("%.1f%%", factors.get("momentum") * 100));
        
        return reasoning.toString();
    }
    
    /**
     * Build set prediction reasoning
     */
    private String buildSetPredictionReasoning(Match match, Map<String, Double> factors, String predictedWinner, double confidence) {
        StringBuilder reasoning = new StringBuilder();
        reasoning.append("Current Set Winner Prediction: ");
        reasoning.append(predictedWinner.equals("player1") ? match.getPlayer1().getName() : match.getPlayer2().getName());
        reasoning.append(" (Confidence: ").append(String.format("%.1f%%", confidence * 100)).append(")\n\n");
        
        reasoning.append("Set-specific Factors:\n");
        reasoning.append("- Current Set Score: ").append(match.getPlayer1GamesCurrentSet()).append("-").append(match.getPlayer2GamesCurrentSet()).append("\n");
        reasoning.append("- Overall Match Score: ").append(match.getPlayer1SetsWon()).append("-").append(match.getPlayer2SetsWon()).append("\n");
        reasoning.append("- Set Momentum: ").append(String.format("%.1f%%", factors.get("momentum") * 100));
        
        return reasoning.toString();
    }
}