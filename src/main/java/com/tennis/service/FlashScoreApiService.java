package com.tennis.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tennis.entity.Match;
import com.tennis.entity.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service to integrate with FlashScore API for live tennis data
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FlashScoreApiService {
    
    @Value("${tennis.api.base-url}")
    private String apiBaseUrl;
    
    @Value("${tennis.api.key}")
    private String apiKey;
    
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final MockFlashScoreService mockService;
    
    /**
     * Fetch live tennis matches from FlashScore API
     */
    public List<Match> fetchLiveTennisMatches() {
        try {
            log.info("Fetching live tennis matches from FlashScore API");
            
            // For now, use mock data since FlashScore API might not be available
            // In production, this would call the actual API
            log.info("Using mock data for testing purposes");
            return mockService.generateMockLiveMatches();
            
            /* Uncomment this for actual API integration:
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", apiKey);
            headers.set("X-RapidAPI-Host", apiBaseUrl);
            
            // FlashScore API endpoint for live tennis matches
            String url = "https://" + apiBaseUrl + "/tennis/live";
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            
            if (response.getStatusCode().is2xxSuccessful()) {
                return parseLiveMatches(response.getBody());
            } else {
                log.error("Failed to fetch live matches. Status: {}", response.getStatusCode());
                return new ArrayList<>();
            }
            */
            
        } catch (Exception e) {
            log.error("Error fetching live tennis matches: {}", e.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * Fetch match details by match ID
     */
    public Optional<Match> fetchMatchDetails(String matchId) {
        try {
            log.info("Fetching match details for ID: {}", matchId);
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", apiKey);
            headers.set("X-RapidAPI-Host", apiBaseUrl);
            
            String url = "https://" + apiBaseUrl + "/tennis/match/" + matchId;
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            
            if (response.getStatusCode().is2xxSuccessful()) {
                return parseMatchDetails(response.getBody());
            } else {
                log.error("Failed to fetch match details. Status: {}", response.getStatusCode());
                return Optional.empty();
            }
            
        } catch (Exception e) {
            log.error("Error fetching match details: {}", e.getMessage());
            return Optional.empty();
        }
    }
    
    /**
     * Fetch player statistics
     */
    public Optional<Player> fetchPlayerStats(String playerId) {
        try {
            log.info("Fetching player stats for ID: {}", playerId);
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", apiKey);
            headers.set("X-RapidAPI-Host", apiBaseUrl);
            
            String url = "https://" + apiBaseUrl + "/tennis/player/" + playerId;
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            
            if (response.getStatusCode().is2xxSuccessful()) {
                return parsePlayerStats(response.getBody());
            } else {
                log.error("Failed to fetch player stats. Status: {}", response.getStatusCode());
                return Optional.empty();
            }
            
        } catch (Exception e) {
            log.error("Error fetching player stats: {}", e.getMessage());
            return Optional.empty();
        }
    }
    
    /**
     * Parse live matches from API response
     */
    private List<Match> parseLiveMatches(String responseBody) {
        List<Match> matches = new ArrayList<>();
        
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            
            if (rootNode.has("matches")) {
                JsonNode matchesNode = rootNode.get("matches");
                
                for (JsonNode matchNode : matchesNode) {
                    Match match = parseMatchFromJson(matchNode);
                    if (match != null) {
                        matches.add(match);
                    }
                }
            }
            
            log.info("Parsed {} live matches from API", matches.size());
            
        } catch (Exception e) {
            log.error("Error parsing live matches: {}", e.getMessage());
        }
        
        return matches;
    }
    
    /**
     * Parse match details from API response
     */
    private Optional<Match> parseMatchDetails(String responseBody) {
        try {
            JsonNode matchNode = objectMapper.readTree(responseBody);
            Match match = parseMatchFromJson(matchNode);
            return Optional.ofNullable(match);
            
        } catch (Exception e) {
            log.error("Error parsing match details: {}", e.getMessage());
            return Optional.empty();
        }
    }
    
    /**
     * Parse player statistics from API response
     */
    private Optional<Player> parsePlayerStats(String responseBody) {
        try {
            JsonNode playerNode = objectMapper.readTree(responseBody);
            Player player = parsePlayerFromJson(playerNode);
            return Optional.ofNullable(player);
            
        } catch (Exception e) {
            log.error("Error parsing player stats: {}", e.getMessage());
            return Optional.empty();
        }
    }
    
    /**
     * Parse match data from JSON
     */
    private Match parseMatchFromJson(JsonNode matchNode) {
        try {
            Match match = new Match();
            
            // Basic match info
            match.setId(Long.parseLong(matchNode.get("id").asText()));
            match.setTournamentName(matchNode.path("tournament").path("name").asText("Unknown Tournament"));
            match.setMatchType(matchNode.path("matchType").asText("Best of 3"));
            match.setSurface(matchNode.path("surface").asText("Hard"));
            match.setMatchStatus(matchNode.path("status").asText("Scheduled"));
            
            // Parse players
            if (matchNode.has("player1") && matchNode.has("player2")) {
                Player player1 = parsePlayerFromJson(matchNode.get("player1"));
                Player player2 = parsePlayerFromJson(matchNode.get("player2"));
                match.setPlayer1(player1);
                match.setPlayer2(player2);
            }
            
            // Parse scores
            if (matchNode.has("score")) {
                JsonNode scoreNode = matchNode.get("score");
                parseMatchScore(match, scoreNode);
            }
            
            // Parse live statistics
            if (matchNode.has("statistics")) {
                JsonNode statsNode = matchNode.get("statistics");
                parseMatchStatistics(match, statsNode);
            }
            
            // Set timestamps
            match.setCreatedAt(LocalDateTime.now());
            match.setUpdatedAt(LocalDateTime.now());
            
            return match;
            
        } catch (Exception e) {
            log.error("Error parsing match JSON: {}", e.getMessage());
            return null;
        }
    }
    
    /**
     * Parse player data from JSON
     */
    private Player parsePlayerFromJson(JsonNode playerNode) {
        try {
            Player player = new Player();
            
            player.setId(Long.parseLong(playerNode.get("id").asText()));
            player.setName(playerNode.get("name").asText());
            player.setCountry(playerNode.path("country").asText("Unknown"));
            player.setCurrentRanking(playerNode.path("ranking").asInt(999));
            player.setAge(playerNode.path("age").asInt(25));
            player.setHeightCm(playerNode.path("height").asInt(180));
            player.setWeightKg(playerNode.path("weight").asInt(75));
            player.setPlayingStyle(playerNode.path("style").asText("Aggressive Baseline"));
            player.setPreferredHand(playerNode.path("hand").asText("Right"));
            
            // Parse surface win rates
            if (playerNode.has("surfaces")) {
                JsonNode surfacesNode = playerNode.get("surfaces");
                player.setHardCourtWinRate(surfacesNode.path("hard").path("winRate").asDouble(0.5));
                player.setClayCourtWinRate(surfacesNode.path("clay").path("winRate").asDouble(0.5));
                player.setGrassCourtWinRate(surfacesNode.path("grass").path("winRate").asDouble(0.5));
            }
            
            // Parse service statistics
            if (playerNode.has("service")) {
                JsonNode serviceNode = playerNode.get("service");
                player.setFirstServePercentage(serviceNode.path("firstServePercentage").asDouble(0.6));
                player.setFirstServeWinRate(serviceNode.path("firstServeWinRate").asDouble(0.7));
                player.setSecondServeWinRate(serviceNode.path("secondServeWinRate").asDouble(0.5));
                player.setAcesPerMatch(serviceNode.path("acesPerMatch").asDouble(5.0));
                player.setDoubleFaultsPerMatch(serviceNode.path("doubleFaultsPerMatch").asDouble(2.5));
            }
            
            // Parse return statistics
            if (playerNode.has("return")) {
                JsonNode returnNode = playerNode.get("return");
                player.setFirstServeReturnWinRate(returnNode.path("firstServeReturnWinRate").asDouble(0.3));
                player.setSecondServeReturnWinRate(returnNode.path("secondServeReturnWinRate").asDouble(0.5));
                player.setBreakPointsConvertedPercentage(returnNode.path("breakPointsConverted").asDouble(0.4));
            }
            
            // Parse recent form
            if (playerNode.has("recentForm")) {
                JsonNode formNode = playerNode.get("recentForm");
                player.setRecentFormWinRate(formNode.path("winRate").asDouble(0.5));
                player.setMatchesPlayedThisYear(formNode.path("matchesPlayed").asInt(20));
                player.setWinsThisYear(formNode.path("wins").asInt(10));
            }
            
            player.setCreatedAt(LocalDateTime.now());
            player.setUpdatedAt(LocalDateTime.now());
            
            return player;
            
        } catch (Exception e) {
            log.error("Error parsing player JSON: {}", e.getMessage());
            return null;
        }
    }
    
    /**
     * Parse match score from JSON
     */
    private void parseMatchScore(Match match, JsonNode scoreNode) {
        try {
            // Parse sets
            if (scoreNode.has("sets")) {
                JsonNode setsNode = scoreNode.get("sets");
                match.setPlayer1SetsWon(setsNode.path("player1").asInt(0));
                match.setPlayer2SetsWon(setsNode.path("player2").asInt(0));
            }
            
            // Parse current set
            if (scoreNode.has("currentSet")) {
                JsonNode currentSetNode = scoreNode.get("currentSet");
                match.setCurrentSet(currentSetNode.path("setNumber").asInt(1));
                match.setPlayer1GamesCurrentSet(currentSetNode.path("player1Games").asInt(0));
                match.setPlayer2GamesCurrentSet(currentSetNode.path("player2Games").asInt(0));
            }
            
            // Parse current game
            if (scoreNode.has("currentGame")) {
                JsonNode currentGameNode = scoreNode.get("currentGame");
                match.setPlayer1PointsCurrentGame(currentGameNode.path("player1Points").asInt(0));
                match.setPlayer2PointsCurrentGame(currentGameNode.path("player2Points").asInt(0));
            }
            
            // Parse server
            if (scoreNode.has("server")) {
                match.setCurrentServer(scoreNode.get("server").asText("player1"));
            }
            
        } catch (Exception e) {
            log.error("Error parsing match score: {}", e.getMessage());
        }
    }
    
    /**
     * Parse match statistics from JSON
     */
    private void parseMatchStatistics(Match match, JsonNode statsNode) {
        try {
            // Player 1 stats
            if (statsNode.has("player1")) {
                JsonNode player1Stats = statsNode.get("player1");
                match.setPlayer1Aces(player1Stats.path("aces").asInt(0));
                match.setPlayer1DoubleFaults(player1Stats.path("doubleFaults").asInt(0));
                match.setPlayer1FirstServePercentage(player1Stats.path("firstServePercentage").asDouble(0.0));
                match.setPlayer1BreakPointsWon(player1Stats.path("breakPointsWon").asInt(0));
                match.setPlayer1BreakPointsOpportunities(player1Stats.path("breakPointsOpportunities").asInt(0));
                match.setPlayer1TotalPointsWon(player1Stats.path("totalPointsWon").asInt(0));
            }
            
            // Player 2 stats
            if (statsNode.has("player2")) {
                JsonNode player2Stats = statsNode.get("player2");
                match.setPlayer2Aces(player2Stats.path("aces").asInt(0));
                match.setPlayer2DoubleFaults(player2Stats.path("doubleFaults").asInt(0));
                match.setPlayer2FirstServePercentage(player2Stats.path("firstServePercentage").asDouble(0.0));
                match.setPlayer2BreakPointsWon(player2Stats.path("breakPointsWon").asInt(0));
                match.setPlayer2BreakPointsOpportunities(player2Stats.path("breakPointsOpportunities").asInt(0));
                match.setPlayer2TotalPointsWon(player2Stats.path("totalPointsWon").asInt(0));
            }
            
            // Total points played
            if (statsNode.has("totalPointsPlayed")) {
                match.setTotalPointsPlayed(statsNode.get("totalPointsPlayed").asInt(0));
            }
            
        } catch (Exception e) {
            log.error("Error parsing match statistics: {}", e.getMessage());
        }
    }
}