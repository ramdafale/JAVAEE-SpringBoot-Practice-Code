package com.example.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.Bank;
import com.example.bank.service.IBankService;




/*
 * @Class this class handles all the  request from client 
 */
@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	private IBankService bankService;

	@PostMapping("/bankCreate")
	public ResponseEntity<?> createBank(@RequestBody final Bank bank) {

		Bank bank1;
		bank1 = bankService.createBank(bank);
		return new ResponseEntity<Bank>(bank1, HttpStatus.CREATED);

	}

	@GetMapping("/getBankDetails")
	public ResponseEntity<?> retrive() {
		List<Bank> list;
		list = bankService.getBankDetails();
		return new ResponseEntity<List<Bank>>(list, HttpStatus.OK);
	}

}
