package com.tennis.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tennis.entity.Match;
import com.tennis.entity.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Mock service to simulate FlashScore API responses for testing
 * This service generates realistic tennis match data
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MockFlashScoreService {
    
    private final ObjectMapper objectMapper;
    private final Random random = new Random();
    
    /**
     * Generate mock live tennis matches
     */
    public List<Match> generateMockLiveMatches() {
        List<Match> matches = new ArrayList<>();
        
        // Generate 3-5 live matches
        int numMatches = random.nextInt(3) + 3;
        
        for (int i = 0; i < numMatches; i++) {
            Match match = generateMockMatch(i + 1);
            matches.add(match);
        }
        
        log.info("Generated {} mock live matches", matches.size());
        return matches;
    }
    
    /**
     * Generate a mock match with realistic data
     */
    private Match generateMockMatch(int matchId) {
        Match match = new Match();
        
        // Basic match info
        match.setId((long) matchId);
        match.setTournamentName(getRandomTournament());
        match.setMatchType("Best of 3");
        match.setSurface(getRandomSurface());
        match.setMatchStatus("Live");
        match.setStartTime(LocalDateTime.now().minusMinutes(random.nextInt(120) + 30));
        match.setCurrentServer(random.nextBoolean() ? "player1" : "player2");
        
        // Generate players
        Player player1 = generateMockPlayer(matchId * 2 - 1, true);
        Player player2 = generateMockPlayer(matchId * 2, false);
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        
        // Generate realistic scores
        generateMockScore(match);
        
        // Generate live statistics
        generateMockStatistics(match);
        
        match.setCreatedAt(LocalDateTime.now());
        match.setUpdatedAt(LocalDateTime.now());
        
        return match;
    }
    
    /**
     * Generate a mock player with realistic statistics
     */
    private Player generateMockPlayer(int playerId, boolean isPlayer1) {
        Player player = new Player();
        
        player.setId((long) playerId);
        player.setName(getRandomPlayerName(playerId));
        player.setCountry(getRandomCountry());
        player.setCurrentRanking(random.nextInt(100) + 1);
        player.setCareerHighRanking(player.getCurrentRanking() - random.nextInt(20));
        player.setAge(random.nextInt(15) + 20); // 20-35 years old
        player.setHeightCm(random.nextInt(20) + 175); // 175-195 cm
        player.setWeightKg(random.nextInt(20) + 70); // 70-90 kg
        player.setPlayingStyle(getRandomPlayingStyle());
        player.setPreferredHand(random.nextBoolean() ? "Right" : "Left");
        
        // Surface performance (realistic win rates)
        player.setHardCourtWinRate(0.5 + random.nextDouble() * 0.4); // 50-90%
        player.setClayCourtWinRate(0.4 + random.nextDouble() * 0.5); // 40-90%
        player.setGrassCourtWinRate(0.45 + random.nextDouble() * 0.45); // 45-90%
        
        // Service statistics
        player.setFirstServePercentage(0.55 + random.nextDouble() * 0.25); // 55-80%
        player.setFirstServeWinRate(0.65 + random.nextDouble() * 0.25); // 65-90%
        player.setSecondServeWinRate(0.45 + random.nextDouble() * 0.25); // 45-70%
        player.setAcesPerMatch(3.0 + random.nextDouble() * 8.0); // 3-11 aces
        player.setDoubleFaultsPerMatch(1.5 + random.nextDouble() * 3.0); // 1.5-4.5
        
        // Return statistics
        player.setFirstServeReturnWinRate(0.25 + random.nextDouble() * 0.25); // 25-50%
        player.setSecondServeReturnWinRate(0.45 + random.nextDouble() * 0.25); // 45-70%
        player.setBreakPointsConvertedPercentage(0.35 + random.nextDouble() * 0.25); // 35-60%
        
        // Recent form
        player.setRecentFormWinRate(0.4 + random.nextDouble() * 0.5); // 40-90%
        player.setMatchesPlayedThisYear(random.nextInt(50) + 20); // 20-70 matches
        player.setWinsThisYear(random.nextInt(player.getMatchesPlayedThisYear()) + 10);
        
        player.setCreatedAt(LocalDateTime.now());
        player.setUpdatedAt(LocalDateTime.now());
        
        return player;
    }
    
    /**
     * Generate realistic match score
     */
    private void generateMockScore(Match match) {
        // Generate set scores (1-2 sets completed)
        int completedSets = random.nextInt(2) + 1;
        int player1Sets = 0, player2Sets = 0;
        
        for (int i = 0; i < completedSets; i++) {
            if (random.nextBoolean()) {
                player1Sets++;
            } else {
                player2Sets++;
            }
        }
        
        match.setPlayer1SetsWon(player1Sets);
        match.setPlayer2SetsWon(player2Sets);
        match.setCurrentSet(completedSets + 1);
        
        // Current set games
        int player1Games = random.nextInt(7);
        int player2Games = random.nextInt(7);
        
        // Ensure at least one player has 6 games to win the set
        if (player1Games < 6 && player2Games < 6) {
            if (random.nextBoolean()) {
                player1Games = 6;
            } else {
                player2Games = 6;
            }
        }
        
        match.setPlayer1GamesCurrentSet(player1Games);
        match.setPlayer2GamesCurrentSet(player2Games);
        
        // Current game points (0-4 points each)
        match.setPlayer1PointsCurrentGame(random.nextInt(5));
        match.setPlayer2PointsCurrentGame(random.nextInt(5));
    }
    
    /**
     * Generate realistic match statistics
     */
    private void generateMockStatistics(Match match) {
        // Player 1 stats
        match.setPlayer1Aces(random.nextInt(8) + 2);
        match.setPlayer1DoubleFaults(random.nextInt(4) + 1);
        match.setPlayer1FirstServePercentage(0.55 + random.nextDouble() * 0.25);
        match.setPlayer1BreakPointsWon(random.nextInt(4) + 1);
        match.setPlayer1BreakPointsOpportunities(match.getPlayer1BreakPointsWon() + random.nextInt(3));
        match.setPlayer1TotalPointsWon(random.nextInt(50) + 30);
        
        // Player 2 stats
        match.setPlayer2Aces(random.nextInt(8) + 2);
        match.setPlayer2DoubleFaults(random.nextInt(4) + 1);
        match.setPlayer2FirstServePercentage(0.55 + random.nextDouble() * 0.25);
        match.setPlayer2BreakPointsWon(random.nextInt(4) + 1);
        match.setPlayer2BreakPointsOpportunities(match.getPlayer2BreakPointsWon() + random.nextInt(3));
        match.setPlayer2TotalPointsWon(random.nextInt(50) + 30);
        
        // Total points
        match.setTotalPointsPlayed(match.getPlayer1TotalPointsWon() + match.getPlayer2TotalPointsWon());
    }
    
    /**
     * Get random tournament name
     */
    private String getRandomTournament() {
        String[] tournaments = {
            "Australian Open", "French Open", "Wimbledon", "US Open",
            "ATP Finals", "Miami Open", "Indian Wells", "Monte Carlo Masters",
            "Madrid Open", "Rome Masters", "Canadian Open", "Cincinnati Masters"
        };
        return tournaments[random.nextInt(tournaments.length)];
    }
    
    /**
     * Get random surface
     */
    private String getRandomSurface() {
        String[] surfaces = {"Hard", "Clay", "Grass"};
        return surfaces[random.nextInt(surfaces.length)];
    }
    
    /**
     * Get random player name
     */
    private String getRandomPlayerName(int playerId) {
        String[] firstNames = {
            "Novak", "Carlos", "Daniil", "Jannik", "Andrey", "Stefanos", "Alexander", "Holger",
            "Hubert", "Taylor", "Casper", "Felix", "Denis", "Karen", "Borna", "Matteo"
        };
        String[] lastNames = {
            "Djokovic", "Alcaraz", "Medvedev", "Sinner", "Rublev", "Tsitsipas", "Zverev", "Rune",
            "Hurkacz", "Fritz", "Ruud", "Auger-Aliassime", "Shapovalov", "Khachanov", "Coric", "Berrettini"
        };
        return firstNames[playerId % firstNames.length] + " " + lastNames[playerId % lastNames.length];
    }
    
    /**
     * Get random country
     */
    private String getRandomCountry() {
        String[] countries = {
            "Serbia", "Spain", "Russia", "Italy", "Greece", "Germany", "Denmark", "Poland",
            "USA", "Norway", "Canada", "Croatia", "France", "Switzerland", "Australia", "Argentina"
        };
        return countries[random.nextInt(countries.length)];
    }
    
    /**
     * Get random playing style
     */
    private String getRandomPlayingStyle() {
        String[] styles = {
            "Aggressive Baseline", "Defensive Baseline", "Serve and Volley", "All-Court",
            "Counter-Puncher", "Big Server", "All-Rounder"
        };
        return styles[random.nextInt(styles.length)];
    }
    
    /**
     * Generate mock API response JSON
     */
    public String generateMockApiResponse() {
        try {
            ObjectNode rootNode = objectMapper.createObjectNode();
            ArrayNode matchesNode = objectMapper.createArrayNode();
            
            List<Match> matches = generateMockLiveMatches();
            
            for (Match match : matches) {
                ObjectNode matchNode = objectMapper.createObjectNode();
                matchNode.put("id", match.getId());
                matchNode.put("status", match.getMatchStatus());
                matchNode.put("surface", match.getSurface());
                matchNode.put("matchType", match.getMatchType());
                
                // Tournament info
                ObjectNode tournamentNode = objectMapper.createObjectNode();
                tournamentNode.put("name", match.getTournamentName());
                matchNode.set("tournament", tournamentNode);
                
                // Player info
                ObjectNode player1Node = createPlayerNode(match.getPlayer1());
                ObjectNode player2Node = createPlayerNode(match.getPlayer2());
                matchNode.set("player1", player1Node);
                matchNode.set("player2", player2Node);
                
                // Score info
                ObjectNode scoreNode = createScoreNode(match);
                matchNode.set("score", scoreNode);
                
                // Statistics
                ObjectNode statsNode = createStatisticsNode(match);
                matchNode.set("statistics", statsNode);
                
                matchesNode.add(matchNode);
            }
            
            rootNode.set("matches", matchesNode);
            return objectMapper.writeValueAsString(rootNode);
            
        } catch (Exception e) {
            log.error("Error generating mock API response: {}", e.getMessage());
            return "{}";
        }
    }
    
    /**
     * Create player JSON node
     */
    private ObjectNode createPlayerNode(Player player) {
        ObjectNode playerNode = objectMapper.createObjectNode();
        playerNode.put("id", player.getId());
        playerNode.put("name", player.getName());
        playerNode.put("country", player.getCountry());
        playerNode.put("ranking", player.getCurrentRanking());
        playerNode.put("age", player.getAge());
        playerNode.put("height", player.getHeightCm());
        playerNode.put("weight", player.getWeightKg());
        playerNode.put("style", player.getPlayingStyle());
        playerNode.put("hand", player.getPreferredHand());
        
        // Surfaces
        ObjectNode surfacesNode = objectMapper.createObjectNode();
        ObjectNode hardNode = objectMapper.createObjectNode();
        hardNode.put("winRate", player.getHardCourtWinRate());
        surfacesNode.set("hard", hardNode);
        
        ObjectNode clayNode = objectMapper.createObjectNode();
        clayNode.put("winRate", player.getClayCourtWinRate());
        surfacesNode.set("clay", clayNode);
        
        ObjectNode grassNode = objectMapper.createObjectNode();
        grassNode.put("winRate", player.getGrassCourtWinRate());
        surfacesNode.set("grass", grassNode);
        
        playerNode.set("surfaces", surfacesNode);
        
        // Service stats
        ObjectNode serviceNode = objectMapper.createObjectNode();
        serviceNode.put("firstServePercentage", player.getFirstServePercentage());
        serviceNode.put("firstServeWinRate", player.getFirstServeWinRate());
        serviceNode.put("secondServeWinRate", player.getSecondServeWinRate());
        serviceNode.put("acesPerMatch", player.getAcesPerMatch());
        serviceNode.put("doubleFaultsPerMatch", player.getDoubleFaultsPerMatch());
        playerNode.set("service", serviceNode);
        
        // Return stats
        ObjectNode returnNode = objectMapper.createObjectNode();
        returnNode.put("firstServeReturnWinRate", player.getFirstServeReturnWinRate());
        returnNode.put("secondServeReturnWinRate", player.getSecondServeReturnWinRate());
        returnNode.put("breakPointsConverted", player.getBreakPointsConvertedPercentage());
        playerNode.set("return", returnNode);
        
        // Recent form
        ObjectNode formNode = objectMapper.createObjectNode();
        formNode.put("winRate", player.getRecentFormWinRate());
        formNode.put("matchesPlayed", player.getMatchesPlayedThisYear());
        formNode.put("wins", player.getWinsThisYear());
        playerNode.set("recentForm", formNode);
        
        return playerNode;
    }
    
    /**
     * Create score JSON node
     */
    private ObjectNode createScoreNode(Match match) {
        ObjectNode scoreNode = objectMapper.createObjectNode();
        
        // Sets
        ObjectNode setsNode = objectMapper.createObjectNode();
        setsNode.put("player1", match.getPlayer1SetsWon());
        setsNode.put("player2", match.getPlayer2SetsWon());
        scoreNode.set("sets", setsNode);
        
        // Current set
        ObjectNode currentSetNode = objectMapper.createObjectNode();
        currentSetNode.put("setNumber", match.getCurrentSet());
        currentSetNode.put("player1Games", match.getPlayer1GamesCurrentSet());
        currentSetNode.put("player2Games", match.getPlayer2GamesCurrentSet());
        scoreNode.set("currentSet", currentSetNode);
        
        // Current game
        ObjectNode currentGameNode = objectMapper.createObjectNode();
        currentGameNode.put("player1Points", match.getPlayer1PointsCurrentGame());
        currentGameNode.put("player2Points", match.getPlayer2PointsCurrentGame());
        scoreNode.set("currentGame", currentGameNode);
        
        scoreNode.put("server", match.getCurrentServer());
        
        return scoreNode;
    }
    
    /**
     * Create statistics JSON node
     */
    private ObjectNode createStatisticsNode(Match match) {
        ObjectNode statsNode = objectMapper.createObjectNode();
        
        // Player 1 stats
        ObjectNode player1Stats = objectMapper.createObjectNode();
        player1Stats.put("aces", match.getPlayer1Aces());
        player1Stats.put("doubleFaults", match.getPlayer1DoubleFaults());
        player1Stats.put("firstServePercentage", match.getPlayer1FirstServePercentage());
        player1Stats.put("breakPointsWon", match.getPlayer1BreakPointsWon());
        player1Stats.put("breakPointsOpportunities", match.getPlayer1BreakPointsOpportunities());
        player1Stats.put("totalPointsWon", match.getPlayer1TotalPointsWon());
        statsNode.set("player1", player1Stats);
        
        // Player 2 stats
        ObjectNode player2Stats = objectMapper.createObjectNode();
        player2Stats.put("aces", match.getPlayer2Aces());
        player2Stats.put("doubleFaults", match.getPlayer2DoubleFaults());
        player2Stats.put("firstServePercentage", match.getPlayer2FirstServePercentage());
        player2Stats.put("breakPointsWon", match.getPlayer2BreakPointsWon());
        player2Stats.put("breakPointsOpportunities", match.getPlayer2BreakPointsOpportunities());
        player2Stats.put("totalPointsWon", match.getPlayer2TotalPointsWon());
        statsNode.set("player2", player2Stats);
        
        statsNode.put("totalPointsPlayed", match.getTotalPointsPlayed());
        
        return statsNode;
    }
}