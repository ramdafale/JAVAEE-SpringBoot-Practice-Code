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
 * Service to integrate with API-Tennis from RapidAPI for live tennis data
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FlashScoreApiService {
    
    @Value("${tennis.api.base-url}")
    private String apiBaseUrl;
    
    @Value("${tennis.api.key}")
    private String apiKey;
    
    @Value("${tennis.api.host}")
    private String apiHost;
    
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    
    /**
     * Create headers with RapidAPI authentication
     */
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);
        headers.set("Content-Type", "application/json");
        return headers;
    }
    
    /**
     * Fetch live tennis matches from API-Tennis
     */
    public List<Match> fetchLiveTennisMatches() {
        try {
            log.info("Fetching live tennis matches from API-Tennis");
            
            // API-Tennis endpoint for live matches
            String url = "https://" + apiBaseUrl + "/live";
            
            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Successfully fetched live matches from API-Tennis");
                return parseLiveMatches(response.getBody());
            } else {
                log.error("Failed to fetch live matches. Status: {}", response.getStatusCode());
                return new ArrayList<>();
            }
            
        } catch (Exception e) {
            log.error("Error fetching live tennis matches: {}", e.getMessage(), e);
            // Fallback to empty list instead of mock data
            return new ArrayList<>();
        }
    }
    
    /**
     * Fetch match details by match ID
     */
    public Optional<Match> fetchMatchDetails(String matchId) {
        try {
            log.info("Fetching match details for ID: {}", matchId);
            
            String url = "https://" + apiBaseUrl + "/match/" + matchId;
            
            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            
            if (response.getStatusCode().is2xxSuccessful()) {
                return Optional.of(parseMatchDetails(response.getBody()));
            } else {
                log.warn("Match details not found for ID: {}", matchId);
                return Optional.empty();
            }
            
        } catch (Exception e) {
            log.error("Error fetching match details for ID {}: {}", matchId, e.getMessage(), e);
            return Optional.empty();
        }
    }
    
    /**
     * Fetch player statistics
     */
    public Optional<Player> fetchPlayerStats(String playerId) {
        try {
            log.info("Fetching player stats for ID: {}", playerId);
            
            String url = "https://" + apiBaseUrl + "/player/" + playerId;
            
            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            
            if (response.getStatusCode().is2xxSuccessful()) {
                return Optional.of(parsePlayerStats(response.getBody()));
            } else {
                log.warn("Player stats not found for ID: {}", playerId);
                return Optional.empty();
            }
            
        } catch (Exception e) {
            log.error("Error fetching player stats for ID {}: {}", playerId, e.getMessage(), e);
            return Optional.empty();
        }
    }
    
    /**
     * Parse live matches from API-Tennis response
     */
    private List<Match> parseLiveMatches(String jsonResponse) {
        List<Match> matches = new ArrayList<>();
        
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode dataNode = rootNode.get("data");
            
            if (dataNode != null && dataNode.isArray()) {
                for (JsonNode matchNode : dataNode) {
                    try {
                        Match match = parseMatchFromApiTennis(matchNode);
                        if (match != null) {
                            matches.add(match);
                        }
                    } catch (Exception e) {
                        log.warn("Failed to parse individual match: {}", e.getMessage());
                    }
                }
            }
            
            log.info("Successfully parsed {} live matches", matches.size());
            
        } catch (Exception e) {
            log.error("Error parsing live matches JSON: {}", e.getMessage(), e);
        }
        
        return matches;
    }
    
    /**
     * Parse match data from API-Tennis format
     */
    private Match parseMatchFromApiTennis(JsonNode matchNode) {
        try {
            Match match = new Match();
            
            // Extract basic match information
            String matchId = matchNode.has("id") ? matchNode.get("id").asText() : 
                           String.valueOf(System.currentTimeMillis());
            match.setId((long) Math.abs(matchId.hashCode()));
            
            // Tournament information
            if (matchNode.has("tournament")) {
                JsonNode tournament = matchNode.get("tournament");
                match.setTournamentName(tournament.has("name") ? tournament.get("name").asText() : "Unknown Tournament");
            }
            
            // Match status and type
            match.setMatchStatus(matchNode.has("status") ? matchNode.get("status").asText() : "SCHEDULED");
            match.setMatchType(matchNode.has("type") ? matchNode.get("type").asText() : "SINGLES");
            
            // Surface information
            if (matchNode.has("surface")) {
                match.setSurface(matchNode.get("surface").asText());
            } else {
                match.setSurface("HARD"); // Default surface
            }
            
            // Timestamps
            match.setStartTime(LocalDateTime.now());
            match.setCreatedAt(LocalDateTime.now());
            match.setUpdatedAt(LocalDateTime.now());
            
            // Parse players
            if (matchNode.has("competitors")) {
                JsonNode competitors = matchNode.get("competitors");
                if (competitors.isArray() && competitors.size() >= 2) {
                    
                    // Player 1
                    JsonNode player1Node = competitors.get(0);
                    Player player1 = parsePlayerFromApiTennis(player1Node);
                    match.setPlayer1Id(player1.getId());
                    
                    // Player 2  
                    JsonNode player2Node = competitors.get(1);
                    Player player2 = parsePlayerFromApiTennis(player2Node);
                    match.setPlayer2Id(player2.getId());
                }
            }
            
            // Parse scores if available
            if (matchNode.has("score")) {
                parseApiTennisScores(match, matchNode.get("score"));
            }
            
            // Set default values for live statistics
            match.setCurrentSet(1);
            match.setPlayer1SetsWon(0);
            match.setPlayer2SetsWon(0);
            match.setPlayer1GamesCurrentSet(0);
            match.setPlayer2GamesCurrentSet(0);
            match.setPlayer1PointsCurrentGame(0);
            match.setPlayer2PointsCurrentGame(0);
            match.setTotalPointsPlayed(0);
            
            return match;
            
        } catch (Exception e) {
            log.error("Error parsing match from API-Tennis: {}", e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * Parse player data from API-Tennis format
     */
    private Player parsePlayerFromApiTennis(JsonNode playerNode) {
        Player player = new Player();
        
        try {
            // Basic player information
            String playerId = playerNode.has("id") ? playerNode.get("id").asText() : 
                            String.valueOf(System.currentTimeMillis());
            player.setId((long) Math.abs(playerId.hashCode()));
            
            player.setName(playerNode.has("name") ? playerNode.get("name").asText() : "Unknown Player");
            player.setCountry(playerNode.has("country") ? playerNode.get("country").asText() : "Unknown");
            
            // Rankings and statistics
            if (playerNode.has("ranking")) {
                player.setCurrentRanking(playerNode.get("ranking").asInt());
            } else {
                player.setCurrentRanking(999); // Default ranking
            }
            
            // Set default values for various statistics
            player.setAge(25);
            player.setPreferredHand("Right");
            player.setPlayingStyle("Baseline");
            player.setHeightCm(180);
            player.setWeightKg(75);
            
            // Tennis-specific statistics with defaults
            player.setFirstServePercentage(65.0f);
            player.setFirstServeWinRate(75.0f);
            player.setSecondServeWinRate(55.0f);
            player.setFirstServeReturnWinRate(35.0f);
            player.setSecondServeReturnWinRate(45.0f);
            player.setBreakPointsConvertedPercentage(40.0f);
            player.setAcesPerMatch(8.0f);
            player.setDoubleFaultsPerMatch(3.0f);
            
            // Surface win rates
            player.setHardCourtWinRate(65.0f);
            player.setClayCourtWinRate(60.0f);
            player.setGrassCourtWinRate(70.0f);
            
            // Recent form and yearly stats
            player.setRecentFormWinRate(70.0f);
            player.setMatchesPlayedThisYear(25);
            player.setWinsThisYear(18);
            
            // Career stats
            player.setCareerHighRanking(player.getCurrentRanking());
            
            // Timestamps
            player.setCreatedAt(LocalDateTime.now());
            player.setUpdatedAt(LocalDateTime.now());
            
        } catch (Exception e) {
            log.error("Error parsing player from API-Tennis: {}", e.getMessage(), e);
        }
        
        return player;
    }
    
    /**
     * Parse scores from API-Tennis format
     */
    private void parseApiTennisScores(Match match, JsonNode scoreNode) {
        try {
            if (scoreNode.has("sets")) {
                JsonNode sets = scoreNode.get("sets");
                if (sets.isArray()) {
                    StringBuilder setScores = new StringBuilder();
                    int currentSetNumber = 1;
                    
                    for (JsonNode setNode : sets) {
                        if (setNode.has("player1") && setNode.has("player2")) {
                            int p1Games = setNode.get("player1").asInt();
                            int p2Games = setNode.get("player2").asInt();
                            
                            if (setScores.length() > 0) {
                                setScores.append(",");
                            }
                            setScores.append(p1Games).append("-").append(p2Games);
                            
                            // Determine set winner
                            if (p1Games > p2Games && (p1Games >= 6 && p1Games - p2Games >= 2) || p1Games == 7) {
                                match.setPlayer1SetsWon(match.getPlayer1SetsWon() + 1);
                            } else if (p2Games > p1Games && (p2Games >= 6 && p2Games - p1Games >= 2) || p2Games == 7) {
                                match.setPlayer2SetsWon(match.getPlayer2SetsWon() + 1);
                            }
                            
                            currentSetNumber++;
                        }
                    }
                    
                    match.setSetScores(setScores.toString());
                    match.setCurrentSet(currentSetNumber);
                }
            }
            
            // Parse current game score if available
            if (scoreNode.has("current_game")) {
                JsonNode currentGame = scoreNode.get("current_game");
                if (currentGame.has("player1")) {
                    match.setPlayer1PointsCurrentGame(convertTennisScore(currentGame.get("player1").asText()));
                }
                if (currentGame.has("player2")) {
                    match.setPlayer2PointsCurrentGame(convertTennisScore(currentGame.get("player2").asText()));
                }
            }
            
        } catch (Exception e) {
            log.error("Error parsing scores from API-Tennis: {}", e.getMessage(), e);
        }
    }
    
    /**
     * Convert tennis score notation to numeric value
     */
    private Integer convertTennisScore(String score) {
        if (score == null) return 0;
        
        switch (score.toLowerCase()) {
            case "0": case "love": return 0;
            case "15": return 1;
            case "30": return 2;
            case "40": return 3;
            case "ad": case "advantage": return 4;
            default: 
                try {
                    return Integer.parseInt(score);
                } catch (NumberFormatException e) {
                    return 0;
                }
        }
    }
    
    /**
     * Parse match details from detailed API response
     */
    private Match parseMatchDetails(String jsonResponse) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode dataNode = rootNode.get("data");
            
            if (dataNode != null) {
                return parseMatchFromApiTennis(dataNode);
            }
            
        } catch (Exception e) {
            log.error("Error parsing match details: {}", e.getMessage(), e);
        }
        
        return new Match();
    }
    
    /**
     * Parse player statistics from API response
     */
    private Player parsePlayerStats(String jsonResponse) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode dataNode = rootNode.get("data");
            
            if (dataNode != null) {
                return parsePlayerFromApiTennis(dataNode);
            }
            
        } catch (Exception e) {
            log.error("Error parsing player stats: {}", e.getMessage(), e);
        }
        
        return new Player();
    }
}