package com.bank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.Exception.ManagedException;
import com.bank.dto.AccountRequest;
import com.bank.dto.AccountTransaction;
import com.bank.model.Account;
import com.bank.model.Bank;
import com.bank.model.Customer;
import com.bank.service.IAccountService;
import com.bank.service.IBankService;
import com.bank.service.ICustomerService;

// To call RestFull services 
@RestController
public class AccountController {

	// Injecting properties of IAccountService to use its methods
	@Autowired
	private IAccountService accountService;
	// Injecting properties of ICustomerService to use its methods
	@Autowired
	private ICustomerService customerService;
	// Injecting properties of IBankService to use its methods
	@Autowired
	private IBankService bankService;

	/*
	 * description : this method will return a created account and will give
	 * response in response entity.
	 */
	@PostMapping("/accountCreate")
	public ResponseEntity<?> createAccount(@RequestBody final AccountRequest accountRe) {
		Account response = null;
		Optional<Bank> bank;
		try {
			bank = bankService.getBankDetailsByID(accountRe.getBankId());

			if (bank.isPresent()) {
				Customer cust;
				try {
					cust = customerService.getCustomerDetails(accountRe.getCustomerId());

					final Account acc = accountRe.getAccount();
					acc.setBank(bank.get());
					acc.setCustomer(cust);
					try {
						response = accountService.createAccount(acc);
					} catch (ManagedException e) {
						 e.getMessage();
						return new ResponseEntity<String>("Account not created", HttpStatus.OK);
					}
				} catch (ManagedException e) {
				 e.getMessage();
					return new ResponseEntity<String>("bank not present", HttpStatus.OK);
				}
			}

			return new ResponseEntity<Account>(response, HttpStatus.CREATED);
		} catch (ManagedException e) {
			String message = e.getMessage();
			return new ResponseEntity<String>("id not found", HttpStatus.OK);
		}

	}

	/*
	 * method : getAccount return type : responseEntitiy description : this method
	 * will return a created account of a given input id
	 */

	@GetMapping("/accountDetails/{id}")
	public ResponseEntity<?> getAccount(@PathVariable Long id) {
		Account acc;
		try {
			acc = accountService.getAccountDetails(id);
			return new ResponseEntity<Account>(acc, HttpStatus.OK);
		} catch (ManagedException e) {
			 e.getMessage();
			return new ResponseEntity<String>("account of given id not found", HttpStatus.OK);
		}

	}

	/*
	 * description : this method will return a success String if money is deposited
	 * mapping : post
	 */

	@PostMapping("/accountDeposit")
	public ResponseEntity<?> depositAccount(@RequestBody final AccountTransaction account) {
		try {
			Long custId = accountService.depositMoney(account);
			return new ResponseEntity<Long>(custId, HttpStatus.OK);

		} catch (ManagedException e) {
			e.getMessage();
			return new ResponseEntity<String>("Money Deposited Successfully", HttpStatus.OK);
		}
	}

	/*
	 * description : this method will return a string if amount is withdraw
	 */

	@PostMapping("/accountwithdraw")
	public ResponseEntity<?> withdrawAccount(@RequestBody final AccountTransaction account) {
		try {
			Long customerId = accountService.withdrawlMoney(account);
			return new ResponseEntity<Long>(customerId, HttpStatus.OK);

		} catch (ManagedException e) {
			e.getMessage();
			return new ResponseEntity<String>("Money Deposited Successfully", HttpStatus.OK);
		}
	}

}
