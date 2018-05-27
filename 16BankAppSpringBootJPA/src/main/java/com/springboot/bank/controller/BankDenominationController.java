/**
 * 
 */
package com.springboot.bank.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bank.dto.WrapperATMDenomination;
import com.springboot.bank.dto.WrapperDenomination;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Account;
import com.springboot.bank.model.Denomination;
import com.springboot.bank.service.BankDenominationService;



/**
 * @author Ram
 *
 */
@RestController
public class BankDenominationController {

	
	private static final Logger LOGGER = Logger.getLogger( Account.class.getName() );
	
	
	@Autowired
	BankDenominationService bankDenominationService;

	@PostMapping("/totalDenom")
	public ResponseEntity<Denomination> getTotalDenominationForBank(final @RequestBody WrapperDenomination wrapperDenomination ) throws BankException {
		final Denomination denomination = new Denomination();
		try {
			bankDenominationService.addDenominationNew(wrapperDenomination.getAmount());
		} catch (BankException e) {
			System.out.println(e.getMessage());
			LOGGER.info(e.getMessage());
		}
		if (denomination == null) {
			throw new BankException("not found");
		} else {
			return new ResponseEntity<Denomination>(denomination, HttpStatus.OK);
		}
	}
	
 /**
 * @param wrapperBankDenomination
 * @return
 * @throws BankException
 */
@PostMapping("/") public ResponseEntity<Denomination> getTotalDenominationForATM(final @RequestBody WrapperATMDenomination wrapperATMDenomination ) throws BankException {
	final Denomination bankDenomination = new Denomination();
		try {
			bankDenominationService.addDenominationNew(wrapperATMDenomination.getAmount());
		} catch (BankException e) {
			System.out.println(e.getMessage());
			LOGGER.info(e.getMessage());
		}
		return new ResponseEntity<Denomination>(bankDenomination, HttpStatus.OK);
	}
	





	
}