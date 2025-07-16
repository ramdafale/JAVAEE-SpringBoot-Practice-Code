package com.tennis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main Spring Boot Application class for Tennis Match Prediction
 * This application provides real-time tennis match predictions including:
 * - Match winner prediction
 * - Current game winner prediction  
 * - Current set winner prediction
 * 
 * @author Tennis Prediction Team
 * @version 1.0.0
 */
@SpringBootApplication
@EnableScheduling // Enable scheduling for periodic data updates
public class TennisPredictionApplication {

    public static void main(String[] args) {
        SpringApplication.run(TennisPredictionApplication.class, args);
        System.out.println("🎾 Tennis Prediction Application Started Successfully!");
        System.out.println("📊 Access the application at: http://localhost:8080/tennis-prediction");
        System.out.println("🗄️  H2 Database Console: http://localhost:8080/tennis-prediction/h2-console");
    }
}