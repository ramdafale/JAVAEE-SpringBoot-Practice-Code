/**
 * 
 */
package com.springboot.bank.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bank.dto.AccountDetails;
import com.springboot.bank.dto.WrapperBankCustomerAccount;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Account;
import com.springboot.bank.service.AccountService;

/**
 * @author Ram
 *
 */

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;

	final Logger LOGGER = Logger.getLogger(AccountController.class);

	@PostMapping(value = "/createAccount")
	public ResponseEntity<Account> createAccount(@RequestBody WrapperBankCustomerAccount wrapperBankCustomerAccount)
			throws BankException {
		Account accountData = null;
		try {
			accountData = accountService.createAccount(wrapperBankCustomerAccount);
		} catch (BankException e) {
			LOGGER.error(e.getMessage());
		}
		if (accountData == null)
			throw new BankException("Account details not added");
		else
			return new ResponseEntity<Account>(accountData, HttpStatus.OK);
	}

	@PostMapping(value = "/depositMoney")
	public ResponseEntity<Account> depositMoney(@RequestBody AccountDetails accountDetails) throws BankException {
		Account accountData = null;
		try {
			accountData = accountService.depositMoney(accountDetails);
		} catch (BankException e) {
			LOGGER.error(e.getMessage());
		}
		if (accountData == null)
			throw new BankException("Account details not found");
		else
			return new ResponseEntity<Account>(accountData, HttpStatus.OK);
	}

	@PostMapping(value = "/withdrawMoneyFromAccount")
	public ResponseEntity<Account> withdrawMoney(@RequestBody AccountDetails accountDetails) throws BankException {
		Account accountData = null;
		try {
			accountData = accountService.withdrawMoney(accountDetails);
		} catch (BankException e) {
			LOGGER.error(e.getMessage());
		}
		if (accountData == null)
			throw new BankException("Account details not found");
		else
			return new ResponseEntity<Account>(accountData, HttpStatus.OK);
	}

	@PostMapping(value = "/viewAccount/{accountId}")
	public ResponseEntity<Account> getAccountDetails(@PathVariable Long accountId) throws BankException {
		Account accountData = null;
		try {
			accountData = accountService.getAccountDetails(accountId);
		} catch (BankException e) {
			LOGGER.error(e.getMessage());
		}
		if (accountData == null)
			throw new BankException("Account details not found");
		else
			return new ResponseEntity<Account>(accountData, HttpStatus.OK);
	}
}
