package com.example.bank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.Account;
import com.example.bank.model.Bank;
import com.example.bank.model.Customer;
import com.example.bank.request.AccountRequest;
import com.example.bank.service.IAccountService;
import com.example.bank.service.IBankService;
import com.example.bank.service.ICustomerService;

@RestController
public class AccountController {
	
	@Autowired 
	IAccountService accountService;
	@Autowired
	ICustomerService customerService;
	@Autowired 
	IBankService bankService;
	
	@PostMapping("/accountCreate")
	public ResponseEntity<?> createAccount(@RequestBody final AccountRequest accountRe) {
		Account response=null;
		System.out.println(">>>before"+accountRe.getBankId());
		Optional<Bank> bank = bankService.getBankDetailsByID(accountRe.getBankId());
		System.out.println(">>>after"+accountRe.getBankId());
		if (bank.isPresent()) {
		 Customer cust=customerService.getCustomerDetails(accountRe.getCustomerId());
		 Account acc=accountRe.getAccount();
		 acc.setBank(bank.get());
		 acc.setCustomer(cust);
			response = accountService.createAccount(acc);
			System.out.println(response);
				
		 System.out.println("oustsdsa"+response);
		 return new ResponseEntity<Account>(response, HttpStatus.CREATED);
 
		}else {
			return new ResponseEntity<String>("no such bank exsit", HttpStatus.OK);
		}

	}

	
	
	
	@GetMapping("/getCustomerDetails/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
		Customer cust= customerService.getCustomerDetails(id);
		System.out.println(">>>>>>>>>>>>>>" + cust);
		
		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	}
	

}
