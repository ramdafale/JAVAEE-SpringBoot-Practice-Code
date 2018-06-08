package com.example.sampleSend;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SampleSendApplication {

	public final static String MESSAGE_QUEUE = "sfg-message-queue";
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-boot-exchange");
	}

	public static void main(String[] args) {
		SpringApplication.run(SampleSendApplication.class, args);
	}
}
