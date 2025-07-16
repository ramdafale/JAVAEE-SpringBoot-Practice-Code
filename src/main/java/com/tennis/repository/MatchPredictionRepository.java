package com.tennis.repository;

import com.tennis.entity.Match;
import com.tennis.entity.MatchPrediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for MatchPrediction entity
 * Provides database operations for prediction data
 */
@Repository
public interface MatchPredictionRepository extends JpaRepository<MatchPrediction, Long> {
    
    /**
     * Find predictions by match
     * @param match match to search for
     * @return list of predictions for that match
     */
    List<MatchPrediction> findByMatchOrderByPredictionTimestampDesc(Match match);
    
    /**
     * Find predictions by prediction type
     * @param predictionType type of prediction
     * @return list of predictions of that type
     */
    List<MatchPrediction> findByPredictionTypeOrderByPredictionTimestampDesc(String predictionType);
    
    /**
     * Find predictions by match and type
     * @param match match to search for
     * @param predictionType type of prediction
     * @return list of predictions for match and type
     */
    List<MatchPrediction> findByMatchAndPredictionTypeOrderByPredictionTimestampDesc(Match match, String predictionType);
    
    /**
     * Find latest prediction for a match and type
     * @param match match to search for
     * @param predictionType type of prediction
     * @return latest prediction
     */
    Optional<MatchPrediction> findFirstByMatchAndPredictionTypeOrderByPredictionTimestampDesc(Match match, String predictionType);
    
    /**
     * Find predictions by confidence score range
     * @param minConfidence minimum confidence score
     * @param maxConfidence maximum confidence score
     * @return list of predictions in confidence range
     */
    List<MatchPrediction> findByConfidenceScoreBetweenOrderByConfidenceScoreDesc(Double minConfidence, Double maxConfidence);
    
    /**
     * Find high confidence predictions
     * @param minConfidence minimum confidence threshold
     * @return list of high confidence predictions
     */
    List<MatchPrediction> findByConfidenceScoreGreaterThanEqualOrderByConfidenceScoreDesc(Double minConfidence);
    
    /**
     * Find predictions by date range
     * @param startDate start date
     * @param endDate end date
     * @return list of predictions in date range
     */
    List<MatchPrediction> findByPredictionTimestampBetweenOrderByPredictionTimestampDesc(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Find verified predictions (where isCorrect is not null)
     * @return list of verified predictions
     */
    List<MatchPrediction> findByIsCorrectIsNotNullOrderByPredictionTimestampDesc();
    
    /**
     * Find correct predictions
     * @return list of predictions that were correct
     */
    List<MatchPrediction> findByIsCorrectTrueOrderByPredictionTimestampDesc();
    
    /**
     * Find incorrect predictions
     * @return list of predictions that were incorrect
     */
    List<MatchPrediction> findByIsCorrectFalseOrderByPredictionTimestampDesc();
    
    /**
     * Find predictions by predicted winner
     * @param predictedWinner predicted winner ("player1" or "player2")
     * @return list of predictions with that winner
     */
    List<MatchPrediction> findByPredictedWinnerOrderByPredictionTimestampDesc(String predictedWinner);
    
    /**
     * Find predictions for live matches
     * @return list of predictions for live matches
     */
    @Query("SELECT mp FROM MatchPrediction mp WHERE mp.match.matchStatus = 'Live' ORDER BY mp.predictionTimestamp DESC")
    List<MatchPrediction> findPredictionsForLiveMatches();
    
    /**
     * Find predictions by match status
     * @param matchStatus match status
     * @return list of predictions for matches with that status
     */
    @Query("SELECT mp FROM MatchPrediction mp WHERE mp.match.matchStatus = :matchStatus ORDER BY mp.predictionTimestamp DESC")
    List<MatchPrediction> findByMatchStatus(@Param("matchStatus") String matchStatus);
    
    /**
     * Find predictions by surface
     * @param surface surface type
     * @return list of predictions for matches on that surface
     */
    @Query("SELECT mp FROM MatchPrediction mp WHERE mp.match.surface = :surface ORDER BY mp.predictionTimestamp DESC")
    List<MatchPrediction> findBySurface(@Param("surface") String surface);
    
    /**
     * Calculate prediction accuracy
     * @return prediction accuracy percentage
     */
    @Query("SELECT (COUNT(CASE WHEN mp.isCorrect = true THEN 1 END) * 100.0 / COUNT(mp)) FROM MatchPrediction mp WHERE mp.isCorrect IS NOT NULL")
    Double calculatePredictionAccuracy();
    
    /**
     * Find predictions by tournament
     * @param tournamentName tournament name
     * @return list of predictions for matches in that tournament
     */
    @Query("SELECT mp FROM MatchPrediction mp WHERE mp.match.tournamentName = :tournamentName ORDER BY mp.predictionTimestamp DESC")
    List<MatchPrediction> findByTournament(@Param("tournamentName") String tournamentName);
    
    /**
     * Find recent predictions
     * @param hours number of hours to look back
     * @return list of recent predictions
     */
    @Query("SELECT mp FROM MatchPrediction mp WHERE mp.predictionTimestamp >= :startTime ORDER BY mp.predictionTimestamp DESC")
    List<MatchPrediction> findRecentPredictions(@Param("startTime") LocalDateTime startTime);
}