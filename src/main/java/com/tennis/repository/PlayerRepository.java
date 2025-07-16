package com.tennis.repository;

import com.tennis.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Player entity
 * Provides database operations for player data
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    
    /**
     * Find player by name (case-insensitive)
     * @param name player name
     * @return optional player
     */
    Optional<Player> findByNameIgnoreCase(String name);
    
    /**
     * Find players by country
     * @param country country name
     * @return list of players from that country
     */
    List<Player> findByCountryIgnoreCase(String country);
    
    /**
     * Find players by ranking range
     * @param minRanking minimum ranking
     * @param maxRanking maximum ranking
     * @return list of players in ranking range
     */
    List<Player> findByCurrentRankingBetweenOrderByCurrentRankingAsc(Integer minRanking, Integer maxRanking);
    
    /**
     * Find top ranked players
     * @param limit number of players to return
     * @return list of top ranked players
     */
    List<Player> findTop10ByOrderByCurrentRankingAsc();
    
    /**
     * Find players by surface performance
     * @param surface surface type (hard, clay, grass)
     * @param minWinRate minimum win rate
     * @return list of players with good performance on surface
     */
    @Query("SELECT p FROM Player p WHERE " +
           "CASE WHEN :surface = 'hard' THEN p.hardCourtWinRate " +
           "WHEN :surface = 'clay' THEN p.clayCourtWinRate " +
           "WHEN :surface = 'grass' THEN p.grassCourtWinRate " +
           "ELSE 0.0 END >= :minWinRate " +
           "ORDER BY " +
           "CASE WHEN :surface = 'hard' THEN p.hardCourtWinRate " +
           "WHEN :surface = 'clay' THEN p.clayCourtWinRate " +
           "WHEN :surface = 'grass' THEN p.grassCourtWinRate " +
           "ELSE 0.0 END DESC")
    List<Player> findPlayersBySurfacePerformance(@Param("surface") String surface, @Param("minWinRate") Double minWinRate);
    
    /**
     * Find players by recent form
     * @param minWinRate minimum recent form win rate
     * @return list of players with good recent form
     */
    List<Player> findByRecentFormWinRateGreaterThanEqualOrderByRecentFormWinRateDesc(Double minWinRate);
    
    /**
     * Search players by name containing pattern
     * @param namePattern name pattern to search
     * @return list of matching players
     */
    List<Player> findByNameContainingIgnoreCase(String namePattern);
    
    /**
     * Find players by playing style
     * @param playingStyle playing style
     * @return list of players with that style
     */
    List<Player> findByPlayingStyleIgnoreCase(String playingStyle);
    
    /**
     * Find players by preferred hand
     * @param preferredHand preferred hand (Right, Left)
     * @return list of players with that hand preference
     */
    List<Player> findByPreferredHandIgnoreCase(String preferredHand);
}