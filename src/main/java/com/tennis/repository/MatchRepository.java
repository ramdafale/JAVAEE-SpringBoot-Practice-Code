package com.tennis.repository;

import com.tennis.entity.Match;
import com.tennis.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Match entity
 * Provides database operations for match data
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    
    /**
     * Find matches by status
     * @param status match status
     * @return list of matches with that status
     */
    List<Match> findByMatchStatus(String status);
    
    /**
     * Find live matches
     * @return list of currently live matches
     */
    List<Match> findByMatchStatusOrderByStartTimeDesc(String status);
    
    /**
     * Find matches by player
     * @param player player to search for
     * @return list of matches involving the player
     */
    List<Match> findByPlayer1OrPlayer2OrderByStartTimeDesc(Player player1, Player player2);
    
    /**
     * Find matches by tournament
     * @param tournamentName tournament name
     * @return list of matches in that tournament
     */
    List<Match> findByTournamentNameIgnoreCaseOrderByStartTimeDesc(String tournamentName);
    
    /**
     * Find matches by surface
     * @param surface surface type
     * @return list of matches on that surface
     */
    List<Match> findBySurfaceIgnoreCaseOrderByStartTimeDesc(String surface);
    
    /**
     * Find matches between two specific players
     * @param player1 first player
     * @param player2 second player
     * @return list of matches between these players
     */
    List<Match> findByPlayer1AndPlayer2OrPlayer2AndPlayer1OrderByStartTimeDesc(
        Player player1, Player player2, Player player2Again, Player player1Again);
    
    /**
     * Find matches by date range
     * @param startDate start date
     * @param endDate end date
     * @return list of matches in date range
     */
    List<Match> findByStartTimeBetweenOrderByStartTimeDesc(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Find completed matches
     * @return list of completed matches
     */
    List<Match> findByMatchStatusOrderByEndTimeDesc(String status);
    
    /**
     * Find matches by player with status
     * @param player player to search for
     * @param status match status
     * @return list of matches for player with specific status
     */
    @Query("SELECT m FROM Match m WHERE (m.player1 = :player OR m.player2 = :player) AND m.matchStatus = :status ORDER BY m.startTime DESC")
    List<Match> findByPlayerAndStatus(@Param("player") Player player, @Param("status") String status);
    
    /**
     * Find matches by surface and status
     * @param surface surface type
     * @param status match status
     * @return list of matches on surface with status
     */
    List<Match> findBySurfaceIgnoreCaseAndMatchStatusOrderByStartTimeDesc(String surface, String status);
    
    /**
     * Find recent matches
     * @param days number of days to look back
     * @return list of recent matches
     */
    @Query("SELECT m FROM Match m WHERE m.startTime >= :startDate ORDER BY m.startTime DESC")
    List<Match> findRecentMatches(@Param("startDate") LocalDateTime startDate);
    
    /**
     * Find matches with specific score pattern
     * @param player1SetsWon sets won by player1
     * @param player2SetsWon sets won by player2
     * @return list of matches with that score
     */
    List<Match> findByPlayer1SetsWonAndPlayer2SetsWon(Integer player1SetsWon, Integer player2SetsWon);
    
    /**
     * Find matches by tournament and status
     * @param tournamentName tournament name
     * @param status match status
     * @return list of matches in tournament with status
     */
    List<Match> findByTournamentNameIgnoreCaseAndMatchStatusOrderByStartTimeDesc(String tournamentName, String status);
}