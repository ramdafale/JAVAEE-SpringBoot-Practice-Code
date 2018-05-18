package com.example.bank.controller;

/**
 * 
 */


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.exception.ManagedException;
import com.example.bank.model.ATM;
import com.example.bank.request.DetailsBankATM;
import com.example.bank.service.ATMServiceImpl;


/**
 * @author ram dafale  
 * RestEnd controller handles all the request and responce 
 */
@RestController
public class ATMController {

	
	
	//Injects  all the operation of ATMSERVICES
	@Autowired
	ATMServiceImpl atmService;

	
	
	
	/*
	 * this rest end will create instance of ATM associated with BAnk
	 */
	
	@PostMapping(value = "/createATM")
	public ResponseEntity<ATM> createATM(@RequestBody DetailsBankATM wrapperBankATM) throws ManagedException {
		ATM atmData = null;
		try {
			atmData = atmService.createATM(wrapperBankATM);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (atmData == null)
			throw new ManagedException("atm details not added");
		else
			return new ResponseEntity<ATM>(atmData, HttpStatus.OK);
	}

	
	/*
	 * this Rest end will add money to ATM from bank.
	 * 
	 */
	
	
	@PostMapping(value = "/addMoneyFromBank/{atmId}/{bankId}/{moneyToBeAddedToATM}")
	public ResponseEntity<ATM> addMoneyFromBank(@PathVariable Long atmId, Long bankId, BigDecimal moneyToBeAddedToATM)
			throws ManagedException {
		ATM atmData = null;
		try {
			atmData = atmService.addMoneyFromBank(atmId, bankId, moneyToBeAddedToATM);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (atmData == null)
			throw new ManagedException("atm details not added");
		else
			return new ResponseEntity<ATM>(atmData, HttpStatus.OK);
	}
	
	
	
	/*
	 * this Rest end will withdraw money to ATM from bank.
	 * 
	 */
	
	
	@PostMapping(value = "/withdrawMoney/{atmId}/{bankId}/{moneyToBeAddedToATM}")
	public ResponseEntity<ATM> withdrawMoney(@PathVariable Long atmId, Long bankId, BigDecimal moneyToBeAddedToATM)
			throws ManagedException {
		ATM atmData = null;
		try {
			atmData = atmService.addMoneyFromBank(atmId, bankId, moneyToBeAddedToATM);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (atmData == null)
			throw new ManagedException("atm details not added");
		else
			return new ResponseEntity<ATM>(atmData, HttpStatus.OK);
	}
	

}

