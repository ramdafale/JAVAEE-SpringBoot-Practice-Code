package com.tennis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main Spring Boot application class for Tennis Match Prediction Service
 */
@SpringBootApplication
@EnableScheduling
public class TennisMatchPredictionApplication {

    public static void main(String[] args) {
        // Disable SSL certificate validation for development
        System.setProperty("com.sun.net.ssl.checkRevocation", "false");
        System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
        
        SpringApplication.run(TennisMatchPredictionApplication.class, args);
    }
}