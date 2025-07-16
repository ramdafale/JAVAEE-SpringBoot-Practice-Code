package com.tennis.service;

import com.tennis.entity.Match;
import com.tennis.entity.Player;
import com.tennis.repository.MatchRepository;
import com.tennis.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service to sync live tennis data from FlashScore API with local database
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LiveDataSyncService {
    
    private final FlashScoreApiService flashScoreApiService;
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    
    /**
     * Sync live matches from FlashScore API
     * Runs every 30 seconds
     */
    @Scheduled(fixedRate = 30000)
    public void syncLiveMatches() {
        try {
            log.info("Starting live matches sync...");
            
            List<Match> liveMatches = flashScoreApiService.fetchLiveTennisMatches();
            
            for (Match apiMatch : liveMatches) {
                syncMatch(apiMatch);
            }
            
            log.info("Live matches sync completed. Processed {} matches", liveMatches.size());
            
        } catch (Exception e) {
            log.error("Error during live matches sync: {}", e.getMessage());
        }
    }
    
    /**
     * Sync a single match from API
     */
    private void syncMatch(Match apiMatch) {
        try {
            // Check if match already exists
            Optional<Match> existingMatch = matchRepository.findById(apiMatch.getId());
            
            if (existingMatch.isPresent()) {
                // Update existing match
                Match existing = existingMatch.get();
                updateMatchData(existing, apiMatch);
                matchRepository.save(existing);
                log.debug("Updated match: {}", apiMatch.getId());
            } else {
                // Create new match
                // First sync players
                syncPlayer(apiMatch.getPlayer1());
                syncPlayer(apiMatch.getPlayer2());
                
                // Save the match
                matchRepository.save(apiMatch);
                log.debug("Created new match: {}", apiMatch.getId());
            }
            
        } catch (Exception e) {
            log.error("Error syncing match {}: {}", apiMatch.getId(), e.getMessage());
        }
    }
    
    /**
     * Sync player data
     */
    private void syncPlayer(Player apiPlayer) {
        try {
            if (apiPlayer == null) return;
            
            Optional<Player> existingPlayer = playerRepository.findById(apiPlayer.getId());
            
            if (existingPlayer.isPresent()) {
                // Update existing player
                Player existing = existingPlayer.get();
                updatePlayerData(existing, apiPlayer);
                playerRepository.save(existing);
            } else {
                // Create new player
                playerRepository.save(apiPlayer);
            }
            
        } catch (Exception e) {
            log.error("Error syncing player {}: {}", apiPlayer.getId(), e.getMessage());
        }
    }
    
    /**
     * Update existing match with new data from API
     */
    private void updateMatchData(Match existing, Match apiMatch) {
        // Update live statistics
        existing.setPlayer1SetsWon(apiMatch.getPlayer1SetsWon());
        existing.setPlayer2SetsWon(apiMatch.getPlayer2SetsWon());
        existing.setCurrentSet(apiMatch.getCurrentSet());
        existing.setPlayer1GamesCurrentSet(apiMatch.getPlayer1GamesCurrentSet());
        existing.setPlayer2GamesCurrentSet(apiMatch.getPlayer2GamesCurrentSet());
        existing.setPlayer1PointsCurrentGame(apiMatch.getPlayer1PointsCurrentGame());
        existing.setPlayer2PointsCurrentGame(apiMatch.getPlayer2PointsCurrentGame());
        existing.setCurrentServer(apiMatch.getCurrentServer());
        
        // Update live statistics
        existing.setPlayer1Aces(apiMatch.getPlayer1Aces());
        existing.setPlayer2Aces(apiMatch.getPlayer2Aces());
        existing.setPlayer1DoubleFaults(apiMatch.getPlayer1DoubleFaults());
        existing.setPlayer2DoubleFaults(apiMatch.getPlayer2DoubleFaults());
        existing.setPlayer1FirstServePercentage(apiMatch.getPlayer1FirstServePercentage());
        existing.setPlayer2FirstServePercentage(apiMatch.getPlayer2FirstServePercentage());
        existing.setPlayer1BreakPointsWon(apiMatch.getPlayer1BreakPointsWon());
        existing.setPlayer2BreakPointsWon(apiMatch.getPlayer2BreakPointsWon());
        existing.setPlayer1BreakPointsOpportunities(apiMatch.getPlayer1BreakPointsOpportunities());
        existing.setPlayer2BreakPointsOpportunities(apiMatch.getPlayer2BreakPointsOpportunities());
        existing.setPlayer1TotalPointsWon(apiMatch.getPlayer1TotalPointsWon());
        existing.setPlayer2TotalPointsWon(apiMatch.getPlayer2TotalPointsWon());
        existing.setTotalPointsPlayed(apiMatch.getTotalPointsPlayed());
        
        // Update match status
        existing.setMatchStatus(apiMatch.getMatchStatus());
        existing.setUpdatedAt(LocalDateTime.now());
    }
    
    /**
     * Update existing player with new data from API
     */
    private void updatePlayerData(Player existing, Player apiPlayer) {
        // Update rankings and basic info
        existing.setCurrentRanking(apiPlayer.getCurrentRanking());
        existing.setCareerHighRanking(apiPlayer.getCareerHighRanking());
        existing.setAge(apiPlayer.getAge());
        
        // Update surface performance
        existing.setHardCourtWinRate(apiPlayer.getHardCourtWinRate());
        existing.setClayCourtWinRate(apiPlayer.getClayCourtWinRate());
        existing.setGrassCourtWinRate(apiPlayer.getGrassCourtWinRate());
        
        // Update service statistics
        existing.setFirstServePercentage(apiPlayer.getFirstServePercentage());
        existing.setFirstServeWinRate(apiPlayer.getFirstServeWinRate());
        existing.setSecondServeWinRate(apiPlayer.getSecondServeWinRate());
        existing.setAcesPerMatch(apiPlayer.getAcesPerMatch());
        existing.setDoubleFaultsPerMatch(apiPlayer.getDoubleFaultsPerMatch());
        
        // Update return statistics
        existing.setFirstServeReturnWinRate(apiPlayer.getFirstServeReturnWinRate());
        existing.setSecondServeReturnWinRate(apiPlayer.getSecondServeReturnWinRate());
        existing.setBreakPointsConvertedPercentage(apiPlayer.getBreakPointsConvertedPercentage());
        
        // Update recent form
        existing.setRecentFormWinRate(apiPlayer.getRecentFormWinRate());
        existing.setMatchesPlayedThisYear(apiPlayer.getMatchesPlayedThisYear());
        existing.setWinsThisYear(apiPlayer.getWinsThisYear());
        
        existing.setUpdatedAt(LocalDateTime.now());
    }
    
    /**
     * Manual sync trigger
     */
    public void triggerManualSync() {
        log.info("Manual sync triggered");
        syncLiveMatches();
    }
    
    /**
     * Get live matches from database
     */
    public List<Match> getLiveMatches() {
        return matchRepository.findByMatchStatus("Live");
    }
    
    /**
     * Update match status to completed
     */
    public void markMatchAsCompleted(Long matchId) {
        Optional<Match> matchOpt = matchRepository.findById(matchId);
        if (matchOpt.isPresent()) {
            Match match = matchOpt.get();
            match.setMatchStatus("Completed");
            match.setEndTime(LocalDateTime.now());
            match.setUpdatedAt(LocalDateTime.now());
            matchRepository.save(match);
            log.info("Marked match {} as completed", matchId);
        }
    }
}