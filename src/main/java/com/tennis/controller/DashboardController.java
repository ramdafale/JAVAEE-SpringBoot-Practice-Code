package com.tennis.controller;

import com.tennis.entity.*;
import com.tennis.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Dashboard Controller for Tennis Prediction Application
 * Handles web dashboard views and pages
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class DashboardController {
    
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final MatchPredictionRepository predictionRepository;
    
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
    
    /**
     * Matches page
     */
    @GetMapping("/matches")
    public String matches(Model model) {
        log.info("Loading matches page");
        
        List<Match> allMatches = matchRepository.findAll();
        List<Match> liveMatches = matchRepository.findByMatchStatus("Live");
        List<Match> completedMatches = matchRepository.findByMatchStatus("Completed");
        
        model.addAttribute("allMatches", allMatches);
        model.addAttribute("liveMatches", liveMatches);
        model.addAttribute("completedMatches", completedMatches);
        
        return "matches";
    }
    
    /**
     * Players page
     */
    @GetMapping("/players")
    public String players(Model model) {
        log.info("Loading players page");
        
        List<Player> allPlayers = playerRepository.findAll();
        List<Player> topPlayers = playerRepository.findTop10ByOrderByCurrentRankingAsc();
        
        model.addAttribute("allPlayers", allPlayers);
        model.addAttribute("topPlayers", topPlayers);
        
        return "players";
    }
    
    /**
     * Predictions page
     */
    @GetMapping("/predictions")
    public String predictions(Model model) {
        log.info("Loading predictions page");
        
        List<MatchPrediction> allPredictions = predictionRepository.findAll();
        List<MatchPrediction> recentPredictions = predictionRepository.findRecentPredictions(
            LocalDateTime.now().minusHours(24));
        
        Double predictionAccuracy = predictionRepository.calculatePredictionAccuracy();
        
        model.addAttribute("allPredictions", allPredictions);
        model.addAttribute("recentPredictions", recentPredictions);
        model.addAttribute("predictionAccuracy", predictionAccuracy != null ? predictionAccuracy : 0.0);
        
        return "predictions";
    }
}