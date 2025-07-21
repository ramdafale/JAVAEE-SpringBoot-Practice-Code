package com.tennis.service;

import com.tennis.entity.*;
import com.tennis.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Service to initialize the database with real data from API
 * NO MORE MOCK DATA - Only real tennis data from external API
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DataInitializationService implements CommandLineRunner {
    
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final HeadToHeadRepository headToHeadRepository;
    private final MatchPredictionRepository predictionRepository;
    private final FlashScoreApiService apiService;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("Initializing database with REAL data from API...");
        
        // Clear any existing data
        clearDatabase();
        
        // Fetch real live matches from API
        fetchRealLiveData();
        
        log.info("Database initialization completed with REAL data only!");
    }
    
    /**
     * Clear existing data to ensure fresh start
     */
    private void clearDatabase() {
        log.info("Clearing existing mock data...");
        predictionRepository.deleteAll();
        matchRepository.deleteAll();
        headToHeadRepository.deleteAll();
        playerRepository.deleteAll();
        log.info("Database cleared successfully");
    }
    
    /**
     * Fetch real live data from API
     */
    private void fetchRealLiveData() {
        log.info("Fetching real live tennis matches from API...");
        
        try {
            // Fetch live matches from external API
            List<Match> liveMatches = apiService.fetchLiveTennisMatches();
            
            if (liveMatches.isEmpty()) {
                log.warn("No live matches found from API. This might be normal if no matches are currently live.");
                log.info("Application will continue to check for live matches every 30 seconds...");
            } else {
                log.info("Successfully fetched {} live matches from external API", liveMatches.size());
                
                // Save matches and players to database
                for (Match match : liveMatches) {
                    // Save players first if they don't exist
                                         if (match.getPlayer1() != null) {
                         Optional<Player> existingPlayer1 = playerRepository.findByNameIgnoreCase(match.getPlayer1().getName());
                         if (existingPlayer1.isEmpty()) {
                             playerRepository.save(match.getPlayer1());
                             log.info("Saved new player: {}", match.getPlayer1().getName());
                         } else {
                             match.setPlayer1(existingPlayer1.get());
                         }
                     }
                     
                     if (match.getPlayer2() != null) {
                         Optional<Player> existingPlayer2 = playerRepository.findByNameIgnoreCase(match.getPlayer2().getName());
                         if (existingPlayer2.isEmpty()) {
                             playerRepository.save(match.getPlayer2());
                             log.info("Saved new player: {}", match.getPlayer2().getName());
                         } else {
                             match.setPlayer2(existingPlayer2.get());
                         }
                     }
                    
                    // Save the match
                    matchRepository.save(match);
                    log.info("Saved live match: {} vs {}", 
                            match.getPlayer1() != null ? match.getPlayer1().getName() : "Unknown",
                            match.getPlayer2() != null ? match.getPlayer2().getName() : "Unknown");
                }
            }
            
        } catch (Exception e) {
            log.error("Failed to fetch real data from API: {}", e.getMessage(), e);
            log.info("Application will continue and retry fetching data via scheduled sync...");
        }
    }
}