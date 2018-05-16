package com.example;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.dao.BankRepository;
import com.example.dao.CustomerRepository;
import com.example.model.Bank;
import com.example.model.Customer;

@SpringBootApplication
public class BmsApplication implements ApplicationRunner{
	
	

	@Autowired
	public BmsApplication(CustomerRepository customerRepository, BankRepository bankRepository) {
		
	}

	public static void main(String[] args) {
		SpringApplication.run(BmsApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
	
		
	}
}
