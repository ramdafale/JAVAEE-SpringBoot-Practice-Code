package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Bank;
import com.example.model.Customer;
import com.example.service.BankServiceImpl;
import com.example.service.ICustomerService;

@RestController
public class BankController {

	@Autowired
    private BankServiceImpl bankserviceImpl;

	
	

	@RequestMapping(value = "/create/bank")
	int add(@RequestBody Bank bank) {
		return (int) bankserviceImpl.getBankDetails(bank);
}
	
	
	@RequestMapping(value="/getbankdetails")
	Object viewBankDetails(@RequestBody Bank bank)
	{
		return bank;
		
	}
	
}
