# 🎾 Tennis Match Prediction Application

A comprehensive Spring Boot application that provides real-time tennis match predictions using AI algorithms. The application analyzes player statistics, head-to-head records, surface performance, and live match data to predict match winners, current game winners, and current set winners.

## 🚀 Features

### Core Prediction Capabilities
- **Match Winner Prediction**: Predicts the overall winner of a tennis match
- **Current Game Winner Prediction**: Predicts who will win the current game
- **Current Set Winner Prediction**: Predicts who will win the current set

### Advanced Analytics
- **Player Statistics**: Comprehensive player profiles including rankings, surface performance, and recent form
- **Head-to-Head Records**: Historical match data between players
- **Live Match Data**: Real-time statistics and score tracking
- **Surface Analysis**: Performance analysis on different court surfaces (Hard, Clay, Grass)

### Technical Features
- **RESTful API**: Complete API for all prediction and data operations
- **Real-time Updates**: Auto-refreshing dashboard with live match data
- **Modern UI**: Beautiful, responsive web interface
- **H2 Database**: In-memory database with sample data
- **Prediction Accuracy Tracking**: Monitor prediction success rates

## 🛠️ Technology Stack

- **Backend**: Spring Boot 3.2.0
- **Database**: H2 (In-memory)
- **Frontend**: Thymeleaf, Bootstrap 5, JavaScript
- **Build Tool**: Maven
- **Java Version**: 17

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Modern web browser

## 🚀 Quick Start

### 1. Clone and Navigate
```bash
cd tennis-prediction-app
```

### 2. Build the Application
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

### 4. Access the Application
Open your browser and navigate to:
- **Main Dashboard**: http://localhost:8080/tennis-prediction/
- **H2 Database Console**: http://localhost:8080/tennis-prediction/h2-console
  - JDBC URL: `jdbc:h2:mem:tennisdb`
  - Username: `sa`
  - Password: `password`

## 📊 Sample Data

The application comes pre-loaded with:

### Top 10 ATP Players (2024 Rankings)
1. Novak Djokovic (Serbia)
2. Carlos Alcaraz (Spain)
3. Daniil Medvedev (Russia)
4. Jannik Sinner (Italy)
5. Andrey Rublev (Russia)
6. Stefanos Tsitsipas (Greece)
7. Alexander Zverev (Germany)
8. Holger Rune (Denmark)
9. Hubert Hurkacz (Poland)
10. Taylor Fritz (USA)

### Sample Matches
- Live matches with real-time statistics
- Completed matches with full results
- Scheduled matches for future predictions

### Head-to-Head Records
- Historical match data between all players
- Surface-specific performance records
- Recent match outcomes

## 🔧 API Endpoints

### Predictions
- `POST /api/predictions/match-winner/{matchId}` - Predict match winner
- `POST /api/predictions/game-winner/{matchId}` - Predict current game winner
- `POST /api/predictions/set-winner/{matchId}` - Predict current set winner
- `GET /api/predictions/match/{matchId}` - Get all predictions for a match
- `GET /api/predictions/latest/{matchId}/{predictionType}` - Get latest prediction
- `GET /api/predictions/accuracy` - Get prediction accuracy statistics

### Matches
- `GET /api/matches` - Get all matches
- `GET /api/matches/live` - Get live matches
- `GET /api/matches/{matchId}` - Get match by ID
- `GET /api/matches/tournament/{tournamentName}` - Get matches by tournament
- `GET /api/matches/surface/{surface}` - Get matches by surface
- `POST /api/matches` - Create new match
- `PUT /api/matches/{matchId}` - Update match

### Players
- `GET /api/players` - Get all players
- `GET /api/players/{playerId}` - Get player by ID
- `GET /api/players/name/{playerName}` - Get player by name
- `GET /api/players/top` - Get top ranked players
- `GET /api/players/country/{country}` - Get players by country
- `GET /api/players/surface/{surface}` - Get players by surface performance
- `GET /api/players/search/{namePattern}` - Search players by name
- `POST /api/players` - Create new player
- `PUT /api/players/{playerId}` - Update player

### Head-to-Head
- `GET /api/head-to-head/{player1Id}/{player2Id}` - Get head-to-head record
- `GET /api/head-to-head/player/{playerId}` - Get player's head-to-head records

### Statistics
- `GET /api/statistics` - Get application statistics

## 🎯 Prediction Algorithm

The prediction system uses a weighted algorithm that considers:

### Factors and Weights
- **Player Rankings** (15%): Current ATP rankings
- **Head-to-Head Records** (20%): Historical performance against opponent
- **Surface Performance** (15%): Win rates on specific surfaces
- **Recent Form** (15%): Recent match performance
- **Live Statistics** (25%): Current match statistics
- **Match Momentum** (10%): Current match flow and score

### Prediction Types
1. **Match Winner**: Overall match outcome prediction
2. **Game Winner**: Current game prediction (focuses on serving and immediate momentum)
3. **Set Winner**: Current set prediction (considers set score and match momentum)

## 🎨 User Interface

### Dashboard Features
- **Real-time Statistics**: Live match count, prediction accuracy, player rankings
- **Live Matches**: Current matches with real-time scores and statistics
- **Top Players**: Ranked list of top players with performance metrics
- **Recent Predictions**: Latest predictions with confidence scores and reasoning
- **Auto-refresh**: Dashboard updates every 30 seconds

### Interactive Elements
- **Prediction Buttons**: One-click predictions for match, game, and set winners
- **Confidence Indicators**: Visual confidence levels for predictions
- **Detailed Reasoning**: Explanation of prediction factors
- **Responsive Design**: Works on desktop, tablet, and mobile devices

## 🔍 Database Schema

### Entities
- **Player**: Comprehensive player profiles with statistics
- **Match**: Live match data and scores
- **HeadToHead**: Historical match records between players
- **MatchPrediction**: Prediction results with confidence scores

### Key Relationships
- Players have multiple matches (as player1 or player2)
- Matches have multiple predictions (different types)
- Players have head-to-head records with other players

## 🚀 Deployment

### Local Development
```bash
# Run with Maven
mvn spring-boot:run

# Or build and run JAR
mvn clean package
java -jar target/tennis-prediction-app-1.0.0.jar
```

### Production Deployment
1. Build the application: `mvn clean package`
2. Deploy the JAR file to your server
3. Configure database connection (switch from H2 to production database)
4. Set up external API keys for live data feeds

## 🔧 Configuration

### Application Properties
```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/tennis-prediction

# Database Configuration
spring.datasource.url=jdbc:h2:mem:tennisdb
spring.datasource.username=sa
spring.datasource.password=password

# Prediction Settings
tennis.prediction.model.threshold=0.6
tennis.prediction.update-interval=30000
```

## 📈 Future Enhancements

### Planned Features
- **Machine Learning Integration**: Advanced ML models for better predictions
- **External API Integration**: Real-time data from FlashScore, ATP, etc.
- **User Authentication**: User accounts and personalized predictions
- **Mobile App**: Native mobile application
- **Advanced Analytics**: Detailed statistical analysis and visualizations
- **Tournament Brackets**: Tournament prediction and bracket management

### Technical Improvements
- **Microservices Architecture**: Split into separate services
- **Real-time WebSocket**: Live updates via WebSocket
- **Caching Layer**: Redis for improved performance
- **Containerization**: Docker support for easy deployment

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Check the API documentation
- Review the sample data and configurations

## 🎯 Quick Test

1. Start the application
2. Navigate to http://localhost:8080/tennis-prediction/
3. View the live matches
4. Click "Predict Winner" on any live match
5. See the prediction with confidence score and reasoning

The application is ready to use immediately with sample data and working predictions!
