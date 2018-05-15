/**
 * 
 */
package com.example.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.model.Bank;
import com.example.service.BankServiceImpl;

/**
 * @author trainee
 *
 */
@Controller
public class BankController {

	
	
	@Autowired
	BankServiceImpl bankServiceImpl;
	
	
	
	
	@PostMapping("/createbank")
	public ResponseEntity<Bank> callCreateBank(  @RequestBody  BigDecimal amount)
	
	
	{
		System.out.println(amount);
		Bank bank= new Bank(amount);
		
		Bank bank1 =  bankServiceImpl.createBank(bank);
		
		System.out.println(bank1);
		
		
		return new ResponseEntity<Bank>(bank1, HttpStatus.OK);
		
		
	}
	
	
	
}
