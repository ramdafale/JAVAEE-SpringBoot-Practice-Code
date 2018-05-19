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
import com.example.bank.request.AccountDetails;
import com.example.bank.request.AccountRequest;
import com.example.bank.request.AccountWithdrawDetails;
import com.example.bank.service.IAccountService;
import com.example.bank.service.IBankService;
import com.example.bank.service.ICustomerService;



/*
 * RestController  this controller conatins all rest end points 
 */

 @RestController
public class AccountController {

	 //used for injecting an services of IAccountService
	@Autowired
	IAccountService accountService;
	
	//used for injecting an services of ICustomerService
	@Autowired
	ICustomerService customerService;
	@Autowired
	
	//used for injecting an services of IBankService
	IBankService bankService;

	
	
	
	
	
	
	/*
	 * when you hit the request from url this method will get invoke and
	 * will create a  new account inside the databse
	 */
	
	
	@PostMapping("/accountCreate")
	public ResponseEntity<?> createAccount(@RequestBody final AccountRequest accountRe) {
		Account response = null;
		System.out.println(">>>before" + accountRe.getBankId());
		Optional<Bank> bank = bankService.getBankDetailsByID(accountRe.getBankId());
		System.out.println(">>>after" + accountRe.getBankId());
		if (bank.isPresent()) {
			Customer cust = customerService.getCustomerDetails(accountRe.getCustomerId());
			Account acc = accountRe.getAccount();
			acc.setBank(bank.get());
			acc.setCustomer(cust);
			response = accountService.createAccount(acc);
			System.out.println(response);

			System.out.println("oustsdsa" + response);
			return new ResponseEntity<Account>(response, HttpStatus.CREATED);

		} else {
			return new ResponseEntity<String>("bank not found", HttpStatus.OK);
		}

	}

	
	/*
	 * this rest end point is used to get getails of perticular customer  
	 */
	
	
	@GetMapping("/getCustomerDetails/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
		Customer cust = customerService.getCustomerDetails(id);
		System.out.println(">>>>>>>>>>>>>>" + cust);

		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	}

	/*
	 * method to deposit money and refect amount in bank
	 */

	@PostMapping("/depositMoney")
	public ResponseEntity<?> depositMoneyToAccount(@RequestBody final AccountDetails accountRe) {

		accountService.depositMoney(accountRe);

		return new ResponseEntity<String>("Deposit Successfull check your balance in db", HttpStatus.OK);

	}

	
	
	/*
	 *this method debits money from bank of that custonmer
	 */
	
	
	@PostMapping("/withdrawMoney")
	public ResponseEntity<?> withdrawMoneyToAccount(@RequestBody final AccountWithdrawDetails accountRe) {

		accountService.withdrawlMoney(accountRe);

		return new ResponseEntity<String>("withdraw Successfull ! check your balance in db", HttpStatus.OK);

	}

}
