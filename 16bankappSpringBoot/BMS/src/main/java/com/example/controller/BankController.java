package com.example.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping("/getbankdetails/{Id}")
	Optional<Bank> viewBankDetails(@PathVariable  BigDecimal Id) {
		

		return  bankserviceImpl.getBankDetails(Id);

	}

}
