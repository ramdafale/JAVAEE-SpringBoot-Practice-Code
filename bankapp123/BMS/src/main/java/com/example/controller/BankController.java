package com.example.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Bank;
import com.example.service.BankServiceImpl;

@RestController
public class BankController {

	@Autowired
	private BankServiceImpl bankserviceImpl;

	@PostMapping("/addbank")
	int add(@RequestBody Bank bank) {

		return (int) bankserviceImpl.addBank(bank);
	}

	@GetMapping("/getbankdetails/{Id}")
	Bank viewBankDetails(@PathVariable  BigDecimal Id) {
		
		Bank bank1=  bankserviceImpl.getBankDetails(Id);
		System.out.println(">>>>>>>>>"+bank1);
		

		return bank1;  

	}

}
