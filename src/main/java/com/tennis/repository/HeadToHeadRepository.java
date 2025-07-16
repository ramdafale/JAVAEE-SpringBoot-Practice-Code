package com.tennis.repository;

import com.tennis.entity.HeadToHead;
import com.tennis.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for HeadToHead entity
 * Provides database operations for head-to-head records
 */
@Repository
public interface HeadToHeadRepository extends JpaRepository<HeadToHead, Long> {
    
    /**
     * Find head-to-head record between two players
     * @param player1 first player
     * @param player2 second player
     * @return optional head-to-head record
     */
    Optional<HeadToHead> findByPlayer1AndPlayer2OrPlayer2AndPlayer1(Player player1, Player player2, Player player2Again, Player player1Again);
    
    /**
     * Find head-to-head records for a player
     * @param player player to search for
     * @return list of head-to-head records involving the player
     */
    List<HeadToHead> findByPlayer1OrPlayer2OrderByTotalMatchesDesc(Player player1, Player player2);
    
    /**
     * Find head-to-head records with minimum matches
     * @param minMatches minimum number of matches
     * @return list of head-to-head records with minimum matches
     */
    List<HeadToHead> findByTotalMatchesGreaterThanEqualOrderByTotalMatchesDesc(Integer minMatches);
    
    /**
     * Find head-to-head records by surface
     * @param surface surface type
     * @param minMatches minimum matches on surface
     * @return list of head-to-head records for surface
     */
    @Query("SELECT h FROM HeadToHead h WHERE " +
           "(:surface = 'hard' AND h.hardCourtMatches >= :minMatches) OR " +
           "(:surface = 'clay' AND h.clayCourtMatches >= :minMatches) OR " +
           "(:surface = 'grass' AND h.grassCourtMatches >= :minMatches) " +
           "ORDER BY " +
           "CASE WHEN :surface = 'hard' THEN h.hardCourtPlayer1Wins * 1.0 / h.hardCourtMatches " +
           "WHEN :surface = 'clay' THEN h.clayCourtPlayer1Wins * 1.0 / h.clayCourtMatches " +
           "WHEN :surface = 'grass' THEN h.grassCourtPlayer1Wins * 1.0 / h.grassCourtMatches " +
           "ELSE 0.0 END DESC")
    List<HeadToHead> findBySurfaceAndMinMatches(@Param("surface") String surface, @Param("minMatches") Integer minMatches);
    
    /**
     * Find recent head-to-head records
     * @param days number of days to look back
     * @return list of recent head-to-head records
     */
    @Query("SELECT h FROM HeadToHead h WHERE h.lastMatchDate >= :startDate ORDER BY h.lastMatchDate DESC")
    List<HeadToHead> findRecentHeadToHeadRecords(@Param("startDate") java.time.LocalDateTime startDate);
    
    /**
     * Find head-to-head records by tournament
     * @param tournamentName tournament name
     * @return list of head-to-head records in tournament
     */
    List<HeadToHead> findByLastMatchTournamentIgnoreCaseOrderByLastMatchDateDesc(String tournamentName);
    
    /**
     * Find head-to-head records by winner
     * @param winner winner of last match ("player1" or "player2")
     * @return list of head-to-head records with that winner
     */
    List<HeadToHead> findByLastMatchWinnerOrderByLastMatchDateDesc(String winner);
    
    /**
     * Find head-to-head records with close records (similar win rates)
     * @param maxWinRateDifference maximum difference in win rates
     * @return list of head-to-head records with close records
     */
    @Query("SELECT h FROM HeadToHead h WHERE ABS(h.player1Wins * 1.0 / h.totalMatches - h.player2Wins * 1.0 / h.totalMatches) <= :maxDifference AND h.totalMatches > 0 ORDER BY h.totalMatches DESC")
    List<HeadToHead> findCloseHeadToHeadRecords(@Param("maxDifference") Double maxWinRateDifference);
    
    /**
     * Find dominant head-to-head records (one player clearly better)
     * @param minWinRateDifference minimum difference in win rates
     * @return list of dominant head-to-head records
     */
    @Query("SELECT h FROM HeadToHead h WHERE ABS(h.player1Wins * 1.0 / h.totalMatches - h.player2Wins * 1.0 / h.totalMatches) >= :minDifference AND h.totalMatches > 0 ORDER BY ABS(h.player1Wins * 1.0 / h.totalMatches - h.player2Wins * 1.0 / h.totalMatches) DESC")
    List<HeadToHead> findDominantHeadToHeadRecords(@Param("minDifference") Double minWinRateDifference);
}