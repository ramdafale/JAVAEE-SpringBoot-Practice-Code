package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

	@ComponentScan(basePackages="com.cg")
	@SpringBootApplication
	public class ConsumerMain {
		
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			SpringApplication.run(ConsumerMain.class, args);
		}


}
