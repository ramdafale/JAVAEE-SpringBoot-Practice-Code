package com.tennis.controller;

import com.tennis.entity.*;
import com.tennis.repository.*;
import com.tennis.service.PredictionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

/**
 * REST Controller for Tennis Prediction Application
 * Provides endpoints for predictions, matches, players, and dashboard
 */
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class TennisPredictionController {
    
    private final PredictionService predictionService;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final MatchPredictionRepository predictionRepository;
    private final HeadToHeadRepository headToHeadRepository;
    
    // ==================== DASHBOARD ENDPOINTS ====================
    
    /**
     * Main dashboard page
     */
    @GetMapping("/")
    public String dashboard(Model model) {
        log.info("Loading main dashboard");
        
        // Get live matches
        List<Match> liveMatches = matchRepository.findByMatchStatus("Live");
        
        // Get recent predictions
        List<MatchPrediction> recentPredictions = predictionRepository.findRecentPredictions(
            LocalDateTime.now().minusHours(24));
        
        // Get top players
        List<Player> topPlayers = playerRepository.findTop10ByOrderByCurrentRankingAsc();
        
        // Calculate prediction accuracy
        Double predictionAccuracy = predictionRepository.calculatePredictionAccuracy();
        
        model.addAttribute("liveMatches", liveMatches);
        model.addAttribute("recentPredictions", recentPredictions);
        model.addAttribute("topPlayers", topPlayers);
        model.addAttribute("predictionAccuracy", predictionAccuracy != null ? predictionAccuracy : 0.0);
        
        return "dashboard";
    }
    
    // ==================== PREDICTION ENDPOINTS ====================
    
    /**
     * Predict match winner
     */
    @PostMapping("/predictions/match-winner/{matchId}")
    @ResponseBody
    public ResponseEntity<MatchPrediction> predictMatchWinner(@PathVariable Long matchId) {
        log.info("Predicting match winner for match ID: {}", matchId);
        
        Optional<Match> matchOpt = matchRepository.findById(matchId);
        if (matchOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Match match = matchOpt.get();
        MatchPrediction prediction = predictionService.predictMatchWinner(match);
        
        return ResponseEntity.ok(prediction);
    }
    
    /**
     * Predict current game winner
     */
    @PostMapping("/predictions/game-winner/{matchId}")
    @ResponseBody
    public ResponseEntity<MatchPrediction> predictGameWinner(@PathVariable Long matchId) {
        log.info("Predicting game winner for match ID: {}", matchId);
        
        Optional<Match> matchOpt = matchRepository.findById(matchId);
        if (matchOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Match match = matchOpt.get();
        MatchPrediction prediction = predictionService.predictCurrentGameWinner(match);
        
        return ResponseEntity.ok(prediction);
    }
    
    /**
     * Predict current set winner
     */
    @PostMapping("/predictions/set-winner/{matchId}")
    @ResponseBody
    public ResponseEntity<MatchPrediction> predictSetWinner(@PathVariable Long matchId) {
        log.info("Predicting set winner for match ID: {}", matchId);
        
        Optional<Match> matchOpt = matchRepository.findById(matchId);
        if (matchOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Match match = matchOpt.get();
        MatchPrediction prediction = predictionService.predictCurrentSetWinner(match);
        
        return ResponseEntity.ok(prediction);
    }
    
    /**
     * Get all predictions for a match
     */
    @GetMapping("/predictions/match/{matchId}")
    @ResponseBody
    public ResponseEntity<List<MatchPrediction>> getMatchPredictions(@PathVariable Long matchId) {
        log.info("Getting predictions for match ID: {}", matchId);
        
        Optional<Match> matchOpt = matchRepository.findById(matchId);
        if (matchOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        List<MatchPrediction> predictions = predictionRepository.findByMatchOrderByPredictionTimestampDesc(matchOpt.get());
        return ResponseEntity.ok(predictions);
    }
    
    /**
     * Get latest prediction for a match and type
     */
    @GetMapping("/predictions/latest/{matchId}/{predictionType}")
    @ResponseBody
    public ResponseEntity<MatchPrediction> getLatestPrediction(
            @PathVariable Long matchId, 
            @PathVariable String predictionType) {
        log.info("Getting latest {} prediction for match ID: {}", predictionType, matchId);
        
        Optional<Match> matchOpt = matchRepository.findById(matchId);
        if (matchOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Optional<MatchPrediction> predictionOpt = predictionRepository
            .findFirstByMatchAndPredictionTypeOrderByPredictionTimestampDesc(matchOpt.get(), predictionType);
        
        return predictionOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Get prediction accuracy statistics
     */
    @GetMapping("/predictions/accuracy")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getPredictionAccuracy() {
        log.info("Getting prediction accuracy statistics");
        
        Double overallAccuracy = predictionRepository.calculatePredictionAccuracy();
        List<MatchPrediction> correctPredictions = predictionRepository.findByIsCorrectTrueOrderByPredictionTimestampDesc();
        List<MatchPrediction> incorrectPredictions = predictionRepository.findByIsCorrectFalseOrderByPredictionTimestampDesc();
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("overallAccuracy", overallAccuracy != null ? overallAccuracy : 0.0);
        stats.put("correctPredictions", correctPredictions.size());
        stats.put("incorrectPredictions", incorrectPredictions.size());
        stats.put("totalVerifiedPredictions", correctPredictions.size() + incorrectPredictions.size());
        
        return ResponseEntity.ok(stats);
    }
    
    // ==================== MATCH ENDPOINTS ====================
    
    /**
     * Get all matches
     */
    @GetMapping("/matches")
    @ResponseBody
    public ResponseEntity<List<Match>> getAllMatches() {
        log.info("Getting all matches");
        List<Match> matches = matchRepository.findAll();
        return ResponseEntity.ok(matches);
    }
    
    /**
     * Get live matches
     */
    @GetMapping("/matches/live")
    @ResponseBody
    public ResponseEntity<List<Match>> getLiveMatches() {
        log.info("Getting live matches");
        List<Match> liveMatches = matchRepository.findByMatchStatus("Live");
        return ResponseEntity.ok(liveMatches);
    }
    
    /**
     * Get match by ID
     */
    @GetMapping("/matches/{matchId}")
    @ResponseBody
    public ResponseEntity<Match> getMatchById(@PathVariable Long matchId) {
        log.info("Getting match by ID: {}", matchId);
        
        Optional<Match> matchOpt = matchRepository.findById(matchId);
        return matchOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Get matches by tournament
     */
    @GetMapping("/matches/tournament/{tournamentName}")
    @ResponseBody
    public ResponseEntity<List<Match>> getMatchesByTournament(@PathVariable String tournamentName) {
        log.info("Getting matches for tournament: {}", tournamentName);
        List<Match> matches = matchRepository.findByTournamentNameIgnoreCaseOrderByStartTimeDesc(tournamentName);
        return ResponseEntity.ok(matches);
    }
    
    /**
     * Get matches by surface
     */
    @GetMapping("/matches/surface/{surface}")
    @ResponseBody
    public ResponseEntity<List<Match>> getMatchesBySurface(@PathVariable String surface) {
        log.info("Getting matches on surface: {}", surface);
        List<Match> matches = matchRepository.findBySurfaceIgnoreCaseOrderByStartTimeDesc(surface);
        return ResponseEntity.ok(matches);
    }
    
    /**
     * Create new match
     */
    @PostMapping("/matches")
    @ResponseBody
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        log.info("Creating new match: {} vs {}", 
                match.getPlayer1().getName(), match.getPlayer2().getName());
        
        Match savedMatch = matchRepository.save(match);
        return ResponseEntity.ok(savedMatch);
    }
    
    /**
     * Update match
     */
    @PutMapping("/matches/{matchId}")
    @ResponseBody
    public ResponseEntity<Match> updateMatch(@PathVariable Long matchId, @RequestBody Match match) {
        log.info("Updating match ID: {}", matchId);
        
        if (!matchRepository.existsById(matchId)) {
            return ResponseEntity.notFound().build();
        }
        
        match.setId(matchId);
        Match updatedMatch = matchRepository.save(match);
        return ResponseEntity.ok(updatedMatch);
    }
    
    // ==================== PLAYER ENDPOINTS ====================
    
    /**
     * Get all players
     */
    @GetMapping("/players")
    @ResponseBody
    public ResponseEntity<List<Player>> getAllPlayers() {
        log.info("Getting all players");
        List<Player> players = playerRepository.findAll();
        return ResponseEntity.ok(players);
    }
    
    /**
     * Get player by ID
     */
    @GetMapping("/players/{playerId}")
    @ResponseBody
    public ResponseEntity<Player> getPlayerById(@PathVariable Long playerId) {
        log.info("Getting player by ID: {}", playerId);
        
        Optional<Player> playerOpt = playerRepository.findById(playerId);
        return playerOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Get player by name
     */
    @GetMapping("/players/name/{playerName}")
    @ResponseBody
    public ResponseEntity<Player> getPlayerByName(@PathVariable String playerName) {
        log.info("Getting player by name: {}", playerName);
        
        Optional<Player> playerOpt = playerRepository.findByNameIgnoreCase(playerName);
        return playerOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Get top ranked players
     */
    @GetMapping("/players/top")
    @ResponseBody
    public ResponseEntity<List<Player>> getTopPlayers() {
        log.info("Getting top ranked players");
        List<Player> topPlayers = playerRepository.findTop10ByOrderByCurrentRankingAsc();
        return ResponseEntity.ok(topPlayers);
    }
    
    /**
     * Get players by country
     */
    @GetMapping("/players/country/{country}")
    @ResponseBody
    public ResponseEntity<List<Player>> getPlayersByCountry(@PathVariable String country) {
        log.info("Getting players from country: {}", country);
        List<Player> players = playerRepository.findByCountryIgnoreCase(country);
        return ResponseEntity.ok(players);
    }
    
    /**
     * Get players by surface performance
     */
    @GetMapping("/players/surface/{surface}")
    @ResponseBody
    public ResponseEntity<List<Player>> getPlayersBySurface(@PathVariable String surface) {
        log.info("Getting players by surface performance: {}", surface);
        List<Player> players = playerRepository.findPlayersBySurfacePerformance(surface, 0.6);
        return ResponseEntity.ok(players);
    }
    
    /**
     * Search players by name
     */
    @GetMapping("/players/search/{namePattern}")
    @ResponseBody
    public ResponseEntity<List<Player>> searchPlayers(@PathVariable String namePattern) {
        log.info("Searching players with pattern: {}", namePattern);
        List<Player> players = playerRepository.findByNameContainingIgnoreCase(namePattern);
        return ResponseEntity.ok(players);
    }
    
    /**
     * Create new player
     */
    @PostMapping("/players")
    @ResponseBody
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        log.info("Creating new player: {}", player.getName());
        
        Player savedPlayer = playerRepository.save(player);
        return ResponseEntity.ok(savedPlayer);
    }
    
    /**
     * Update player
     */
    @PutMapping("/players/{playerId}")
    @ResponseBody
    public ResponseEntity<Player> updatePlayer(@PathVariable Long playerId, @RequestBody Player player) {
        log.info("Updating player ID: {}", playerId);
        
        if (!playerRepository.existsById(playerId)) {
            return ResponseEntity.notFound().build();
        }
        
        player.setId(playerId);
        Player updatedPlayer = playerRepository.save(player);
        return ResponseEntity.ok(updatedPlayer);
    }
    
    // ==================== HEAD-TO-HEAD ENDPOINTS ====================
    
    /**
     * Get head-to-head record between two players
     */
    @GetMapping("/head-to-head/{player1Id}/{player2Id}")
    @ResponseBody
    public ResponseEntity<HeadToHead> getHeadToHead(@PathVariable Long player1Id, @PathVariable Long player2Id) {
        log.info("Getting head-to-head record between players: {} and {}", player1Id, player2Id);
        
        Optional<Player> player1Opt = playerRepository.findById(player1Id);
        Optional<Player> player2Opt = playerRepository.findById(player2Id);
        
        if (player1Opt.isEmpty() || player2Opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Optional<HeadToHead> h2hOpt = headToHeadRepository
            .findByPlayer1AndPlayer2OrPlayer2AndPlayer1(player1Opt.get(), player2Opt.get(), 
                                                      player2Opt.get(), player1Opt.get());
        
        return h2hOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Get head-to-head records for a player
     */
    @GetMapping("/head-to-head/player/{playerId}")
    @ResponseBody
    public ResponseEntity<List<HeadToHead>> getPlayerHeadToHead(@PathVariable Long playerId) {
        log.info("Getting head-to-head records for player: {}", playerId);
        
        Optional<Player> playerOpt = playerRepository.findById(playerId);
        if (playerOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        List<HeadToHead> h2hRecords = headToHeadRepository
            .findByPlayer1OrPlayer2OrderByTotalMatchesDesc(playerOpt.get(), playerOpt.get());
        
        return ResponseEntity.ok(h2hRecords);
    }
    
    // ==================== STATISTICS ENDPOINTS ====================
    
    /**
     * Get application statistics
     */
    @GetMapping("/statistics")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getStatistics() {
        log.info("Getting application statistics");
        
        Map<String, Object> stats = new HashMap<>();
        
        // Player statistics
        long totalPlayers = playerRepository.count();
        List<Player> topPlayers = playerRepository.findTop10ByOrderByCurrentRankingAsc();
        
        // Match statistics
        long totalMatches = matchRepository.count();
        List<Match> liveMatches = matchRepository.findByMatchStatus("Live");
        List<Match> completedMatches = matchRepository.findByMatchStatus("Completed");
        
        // Prediction statistics
        long totalPredictions = predictionRepository.count();
        Double predictionAccuracy = predictionRepository.calculatePredictionAccuracy();
        List<MatchPrediction> recentPredictions = predictionRepository.findRecentPredictions(
            LocalDateTime.now().minusHours(24));
        
        stats.put("totalPlayers", totalPlayers);
        stats.put("topPlayers", topPlayers.size());
        stats.put("totalMatches", totalMatches);
        stats.put("liveMatches", liveMatches.size());
        stats.put("completedMatches", completedMatches.size());
        stats.put("totalPredictions", totalPredictions);
        stats.put("predictionAccuracy", predictionAccuracy != null ? predictionAccuracy : 0.0);
        stats.put("recentPredictions", recentPredictions.size());
        
        return ResponseEntity.ok(stats);
    }
}