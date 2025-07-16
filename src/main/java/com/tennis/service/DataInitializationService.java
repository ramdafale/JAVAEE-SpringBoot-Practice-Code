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

/**
 * Service to initialize the database with sample data
 * Populates players, matches, and head-to-head records for testing
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DataInitializationService implements CommandLineRunner {
    
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final HeadToHeadRepository headToHeadRepository;
    private final MatchPredictionRepository predictionRepository;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("Initializing database with sample data...");
        
        // Initialize players
        initializePlayers();
        
        // Initialize head-to-head records
        initializeHeadToHeadRecords();
        
        // Initialize matches
        initializeMatches();
        
        log.info("Database initialization completed successfully!");
    }
    
    /**
     * Initialize sample players
     */
    private void initializePlayers() {
        log.info("Creating sample players...");
        
        List<Player> players = Arrays.asList(
            // Top 10 ATP Players (as of 2024)
            createPlayer("Novak Djokovic", "Serbia", 1, 1, 36, 188, 77, 
                        "Aggressive Baseline", "Right", 0.85, 0.78, 0.82, 
                        0.68, 0.78, 0.62, 8.5, 2.1, 0.32, 0.58, 0.42, 0.88, 25, 22),
            
            createPlayer("Carlos Alcaraz", "Spain", 2, 1, 20, 183, 80, 
                        "Aggressive Baseline", "Right", 0.82, 0.75, 0.79, 
                        0.65, 0.76, 0.60, 7.2, 2.8, 0.35, 0.62, 0.45, 0.85, 28, 24),
            
            createPlayer("Daniil Medvedev", "Russia", 3, 1, 27, 198, 83, 
                        "Defensive Baseline", "Right", 0.78, 0.72, 0.75, 
                        0.62, 0.74, 0.58, 6.8, 2.5, 0.38, 0.65, 0.48, 0.82, 30, 25),
            
            createPlayer("Jannik Sinner", "Italy", 4, 3, 22, 188, 76, 
                        "Aggressive Baseline", "Right", 0.80, 0.73, 0.77, 
                        0.64, 0.75, 0.59, 7.5, 2.3, 0.36, 0.61, 0.44, 0.87, 32, 28),
            
            createPlayer("Andrey Rublev", "Russia", 5, 5, 26, 188, 75, 
                        "Aggressive Baseline", "Right", 0.76, 0.70, 0.73, 
                        0.60, 0.72, 0.56, 6.2, 2.7, 0.40, 0.68, 0.50, 0.79, 35, 30),
            
            createPlayer("Stefanos Tsitsipas", "Greece", 6, 3, 25, 193, 89, 
                        "All-Court", "Right", 0.79, 0.74, 0.76, 
                        0.63, 0.73, 0.57, 7.0, 2.4, 0.37, 0.63, 0.46, 0.81, 38, 32),
            
            createPlayer("Alexander Zverev", "Germany", 7, 2, 26, 198, 90, 
                        "Aggressive Baseline", "Right", 0.77, 0.71, 0.74, 
                        0.61, 0.71, 0.55, 6.5, 2.6, 0.39, 0.66, 0.49, 0.80, 40, 35),
            
            createPlayer("Holger Rune", "Denmark", 8, 4, 20, 188, 77, 
                        "Aggressive Baseline", "Right", 0.81, 0.76, 0.78, 
                        0.66, 0.77, 0.61, 7.8, 2.2, 0.34, 0.59, 0.43, 0.84, 42, 38),
            
            createPlayer("Hubert Hurkacz", "Poland", 9, 9, 26, 196, 82, 
                        "Serve and Volley", "Right", 0.74, 0.68, 0.72, 
                        0.58, 0.70, 0.54, 8.2, 2.0, 0.33, 0.57, 0.41, 0.78, 45, 40),
            
            createPlayer("Taylor Fritz", "USA", 10, 8, 25, 196, 86, 
                        "Aggressive Baseline", "Right", 0.75, 0.69, 0.73, 
                        0.59, 0.71, 0.55, 6.8, 2.4, 0.38, 0.64, 0.47, 0.77, 48, 42)
        );
        
        playerRepository.saveAll(players);
        log.info("Created {} sample players", players.size());
    }
    
    /**
     * Create a player with all attributes
     */
    private Player createPlayer(String name, String country, Integer currentRanking, Integer careerHighRanking,
                              Integer age, Integer heightCm, Integer weightKg, String playingStyle, String preferredHand,
                              Double hardCourtWinRate, Double clayCourtWinRate, Double grassCourtWinRate,
                              Double firstServePercentage, Double firstServeWinRate, Double secondServeWinRate,
                              Double acesPerMatch, Double doubleFaultsPerMatch, Double firstServeReturnWinRate,
                              Double secondServeReturnWinRate, Double breakPointsConvertedPercentage,
                              Double recentFormWinRate, Integer matchesPlayedThisYear, Integer winsThisYear) {
        
        Player player = new Player();
        player.setName(name);
        player.setCountry(country);
        player.setCurrentRanking(currentRanking);
        player.setCareerHighRanking(careerHighRanking);
        player.setAge(age);
        player.setHeightCm(heightCm);
        player.setWeightKg(weightKg);
        player.setPlayingStyle(playingStyle);
        player.setPreferredHand(preferredHand);
        player.setHardCourtWinRate(hardCourtWinRate);
        player.setClayCourtWinRate(clayCourtWinRate);
        player.setGrassCourtWinRate(grassCourtWinRate);
        player.setFirstServePercentage(firstServePercentage);
        player.setFirstServeWinRate(firstServeWinRate);
        player.setSecondServeWinRate(secondServeWinRate);
        player.setAcesPerMatch(acesPerMatch);
        player.setDoubleFaultsPerMatch(doubleFaultsPerMatch);
        player.setFirstServeReturnWinRate(firstServeReturnWinRate);
        player.setSecondServeReturnWinRate(secondServeReturnWinRate);
        player.setBreakPointsConvertedPercentage(breakPointsConvertedPercentage);
        player.setRecentFormWinRate(recentFormWinRate);
        player.setMatchesPlayedThisYear(matchesPlayedThisYear);
        player.setWinsThisYear(winsThisYear);
        
        return player;
    }
    
    /**
     * Initialize head-to-head records
     */
    private void initializeHeadToHeadRecords() {
        log.info("Creating sample head-to-head records...");
        
        List<Player> players = playerRepository.findAll();
        
        // Create some sample head-to-head records
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = i + 1; j < players.size(); j++) {
                Player player1 = players.get(i);
                Player player2 = players.get(j);
                
                HeadToHead h2h = new HeadToHead();
                h2h.setPlayer1(player1);
                h2h.setPlayer2(player2);
                
                // Generate realistic head-to-head data
                int totalMatches = (int) (Math.random() * 10) + 1;
                int player1Wins = (int) (Math.random() * totalMatches);
                int player2Wins = totalMatches - player1Wins;
                
                h2h.setTotalMatches(totalMatches);
                h2h.setPlayer1Wins(player1Wins);
                h2h.setPlayer2Wins(player2Wins);
                
                // Surface-specific records
                h2h.setHardCourtMatches((int) (totalMatches * 0.4));
                h2h.setHardCourtPlayer1Wins((int) (h2h.getHardCourtMatches() * Math.random()));
                h2h.setClayCourtMatches((int) (totalMatches * 0.35));
                h2h.setClayCourtPlayer1Wins((int) (h2h.getClayCourtMatches() * Math.random()));
                h2h.setGrassCourtMatches((int) (totalMatches * 0.25));
                h2h.setGrassCourtPlayer1Wins((int) (h2h.getGrassCourtMatches() * Math.random()));
                
                h2h.setLastMatchDate(LocalDateTime.now().minusDays((int) (Math.random() * 365)));
                h2h.setLastMatchTournament("Australian Open");
                h2h.setLastMatchSurface("Hard");
                h2h.setLastMatchWinner(Math.random() > 0.5 ? "player1" : "player2");
                
                headToHeadRepository.save(h2h);
            }
        }
        
        log.info("Created sample head-to-head records");
    }
    
    /**
     * Initialize sample matches
     */
    private void initializeMatches() {
        log.info("Creating sample matches...");
        
        List<Player> players = playerRepository.findAll();
        
        // Create some live and completed matches
        List<Match> matches = Arrays.asList(
            // Live match
            createMatch(players.get(0), players.get(1), "Australian Open", "Best of 5", "Hard", "Live",
                       LocalDateTime.now().minusHours(2), null, 1, 0, 1, 4, 3, 30, 40, "player1",
                       8, 5, 2, 1, 0.72, 0.68, 2, 1, 3, 2, 45, 42, 87),
            
            // Completed match
            createMatch(players.get(2), players.get(3), "Wimbledon", "Best of 5", "Grass", "Completed",
                       LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(1).plusHours(3),
                       3, 1, 3, 6, 4, 40, 30, "player1", 12, 8, 1, 2, 0.75, 0.70, 4, 2, 6, 3, 78, 65, 143),
            
            // Scheduled match
            createMatch(players.get(4), players.get(5), "French Open", "Best of 5", "Clay", "Scheduled",
                       LocalDateTime.now().plusHours(2), null, 0, 0, 1, 0, 0, 0, 0, "player1",
                       0, 0, 0, 0, 0.0, 0.0, 0, 0, 0, 0, 0, 0, 0),
            
            // Live match with close score
            createMatch(players.get(6), players.get(7), "US Open", "Best of 5", "Hard", "Live",
                       LocalDateTime.now().minusHours(1), null, 1, 1, 2, 5, 5, 40, 40, "player2",
                       6, 7, 1, 1, 0.68, 0.71, 1, 1, 2, 2, 52, 51, 103)
        );
        
        matchRepository.saveAll(matches);
        log.info("Created {} sample matches", matches.size());
    }
    
    /**
     * Create a match with all attributes
     */
    private Match createMatch(Player player1, Player player2, String tournamentName, String matchType,
                            String surface, String matchStatus, LocalDateTime startTime, LocalDateTime endTime,
                            Integer player1SetsWon, Integer player2SetsWon, Integer currentSet,
                            Integer player1GamesCurrentSet, Integer player2GamesCurrentSet,
                            Integer player1PointsCurrentGame, Integer player2PointsCurrentGame, String currentServer,
                            Integer player1Aces, Integer player2Aces, Integer player1DoubleFaults, Integer player2DoubleFaults,
                            Double player1FirstServePercentage, Double player2FirstServePercentage,
                            Integer player1BreakPointsWon, Integer player2BreakPointsWon,
                            Integer player1BreakPointsOpportunities, Integer player2BreakPointsOpportunities,
                            Integer player1TotalPointsWon, Integer player2TotalPointsWon, Integer totalPointsPlayed) {
        
        Match match = new Match();
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        match.setTournamentName(tournamentName);
        match.setMatchType(matchType);
        match.setSurface(surface);
        match.setMatchStatus(matchStatus);
        match.setStartTime(startTime);
        match.setEndTime(endTime);
        match.setPlayer1SetsWon(player1SetsWon);
        match.setPlayer2SetsWon(player2SetsWon);
        match.setCurrentSet(currentSet);
        match.setPlayer1GamesCurrentSet(player1GamesCurrentSet);
        match.setPlayer2GamesCurrentSet(player2GamesCurrentSet);
        match.setPlayer1PointsCurrentGame(player1PointsCurrentGame);
        match.setPlayer2PointsCurrentGame(player2PointsCurrentGame);
        match.setCurrentServer(currentServer);
        match.setPlayer1Aces(player1Aces);
        match.setPlayer2Aces(player2Aces);
        match.setPlayer1DoubleFaults(player1DoubleFaults);
        match.setPlayer2DoubleFaults(player2DoubleFaults);
        match.setPlayer1FirstServePercentage(player1FirstServePercentage);
        match.setPlayer2FirstServePercentage(player2FirstServePercentage);
        match.setPlayer1BreakPointsWon(player1BreakPointsWon);
        match.setPlayer2BreakPointsWon(player2BreakPointsWon);
        match.setPlayer1BreakPointsOpportunities(player1BreakPointsOpportunities);
        match.setPlayer2BreakPointsOpportunities(player2BreakPointsOpportunities);
        match.setPlayer1TotalPointsWon(player1TotalPointsWon);
        match.setPlayer2TotalPointsWon(player2TotalPointsWon);
        match.setTotalPointsPlayed(totalPointsPlayed);
        
        return match;
    }
}