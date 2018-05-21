/**
 * 
 */
package com.springboot.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bank.dto.WrapperATMDenomination;
import com.springboot.bank.dto.WrapperAccountDenomination;
import com.springboot.bank.dto.WrapperBankDenomination;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.BankDenomination;
import com.springboot.bank.service.BankDenominationService;



/**
 * @author Ram
 *
 */
@RestController
public class BankDenominationController {

	@Autowired
	BankDenominationService bankDenominationService;

	@PostMapping("/totalDenom")
	public ResponseEntity<BankDenomination> getTotalDenominationForBank(@RequestBody WrapperBankDenomination wrapperBankDenomination ) throws BankException {
		BankDenomination bankDenomination = new BankDenomination();
		try {
			bankDenominationService.addDenominationNew(wrapperBankDenomination.getAmount());
		} catch (BankException e) {
			System.out.println(e.getMessage());
		}
		if (bankDenomination == null) {
			throw new BankException("not found");
		} else {
			return new ResponseEntity<BankDenomination>(bankDenomination, HttpStatus.OK);
		}
	}
	
 /**
 * @param wrapperBankDenomination
 * @return
 * @throws BankException
 */
@PostMapping("/") public ResponseEntity<BankDenomination> getTotalDenominationForATM(@RequestBody WrapperATMDenomination wrapperATMDenomination ) throws BankException {
		BankDenomination bankDenomination = new BankDenomination();
		try {
			bankDenominationService.addDenominationNew(wrapperATMDenomination.getAmount());
		} catch (BankException e) {
			System.out.println(e.getMessage());
		}
		if (bankDenomination == null) {
			throw new BankException("not found");
		} else {
			return new ResponseEntity<BankDenomination>(bankDenomination, HttpStatus.OK);
		}
	}
	




/**
* @param wrapperBankDenomination
* @return
* @throws BankException
*/
@PostMapping("/") public ResponseEntity<BankDenomination> getTotalDenominationForATM(@RequestBody WrapperAccountDenomination wrapperAccountDenomination ) throws BankException {
		BankDenomination bankDenomination = new BankDenomination();
		try {
			bankDenominationService.addDenominationNew(wrapperAccountDenomination.getAmount());
		} catch (BankException e) {
			System.out.println(e.getMessage());
		}
		if (bankDenomination == null) {
			throw new BankException("not found");
		} else {
			return new ResponseEntity<BankDenomination>(bankDenomination, HttpStatus.OK);
		}
	}


	
}