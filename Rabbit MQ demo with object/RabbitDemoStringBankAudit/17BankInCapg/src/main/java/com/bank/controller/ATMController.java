package com.bank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.Exception.ManagedException;
import com.bank.dto.ATMReq;
import com.bank.dto.AddMoneyReq;
import com.bank.dto.WithrawMoneyReq;
import com.bank.model.ATM;
import com.bank.model.Bank;
import com.bank.service.IATMService;
import com.bank.service.IBankService;

//To call Restful services
@RestController
public class ATMController {

	// Injecting properties of IATMService to use its methods
	@Autowired
	private IATMService atmService;
	// Injecting properties of IBankService to use its methods in current class
	@Autowired
	private IBankService bankService;

	/*
	 * description : this method will return a  atm instance
	 */
	@PostMapping("/atmCreate")
	public ResponseEntity<?> createATM(@RequestBody final ATMReq atm) {

		ATM atm1 = null;
		try {
			final Optional<Bank> bank = bankService.getBankDetailsByID(atm.getBankId());
			ATM atm2 = atm.getAtm();
			atm2.setBankId(bank.get().getBankId());
			atm1 = atmService.createATM(atm2);
			return new ResponseEntity<ATM>(atm1, HttpStatus.CREATED);
		} catch (ManagedException e) {
			e.getMessage();
			return new ResponseEntity<String>("Atm not created", HttpStatus.OK);
		}

	}
	/*
	 *  description : this method will return a 
	 * String message if money successfully added from bank
	 */

	@PostMapping("/addMoneyInAtm")
	public ResponseEntity<?> addMoneyToATM(@RequestBody final AddMoneyReq atm) {

		try {
			final String str = atmService.addMoneyFromBank(atm);
			return new ResponseEntity<String>(str, HttpStatus.OK);
		} catch (ManagedException e) {
			e.getMessage();
			return new ResponseEntity<String>("money to atm not deposited succesfully!!!", HttpStatus.OK);
		}

	}

	/*
	 * description : this method will return
	 * String if amount is successfully withdraw
	 */
	@PostMapping("/withdrawAtm")
	public ResponseEntity<?> withdrawFromAtm(@RequestBody final WithrawMoneyReq atm) {

		try {
			final Long id = atmService.withrawMoney(atm);
			return new ResponseEntity<Long>(id, HttpStatus.OK);
		} catch (ManagedException e) {
			 e.getMessage();
			return new ResponseEntity<String>("money from atm not withdrawn", HttpStatus.OK);
		}

	}
}
