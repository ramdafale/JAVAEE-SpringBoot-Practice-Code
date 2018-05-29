package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class BankApplicationMain {
	
	
	
	
	
	

	public static void main(String[] args) {
		SpringApplication.run(BankApplicationMain.class, args);
	}
	
	
	
	
	

  /*  @RequestMapping("/greeting")
    public String greeting() {
        return "Hello from EurekaClient bank application!";
    }*/
}
