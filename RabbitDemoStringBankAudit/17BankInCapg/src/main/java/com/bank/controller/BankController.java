package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.Exception.ManagedException;
import com.bank.model.Bank;
import com.bank.service.IBankService;

@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	private IBankService bankService;


	/*
	 * description : this method will return  a bank instance
	 */
	@PostMapping("/bankCreate")
	public ResponseEntity<?> createBank(@RequestBody final Bank bank) {

		Bank bank1;
		try {
			bank1 = bankService.createBank(bank);
			return new ResponseEntity<Bank>(bank1, HttpStatus.CREATED);
		} catch (ManagedException e) {
			e.getMessage();
			return new ResponseEntity<String>("Bank not Added ", HttpStatus.OK);
		}

	}

	/*
	 *   description : this method will return a list of banks 
	 */
	@GetMapping("/getBankDetails")
	public ResponseEntity<?> getBankDetails() {
		List<Bank> list;
		try {
			list = bankService.getBankDetails();
			return new ResponseEntity<List<Bank>>(list, HttpStatus.OK);
		} catch (ManagedException e) {
			e.getMessage();
			return new ResponseEntity<String>("bankDetailsnotfound", HttpStatus.OK);
		}

	}

}
