/**
 * 
 */
package com.springboot.bank.controller;

import java.math.BigDecimal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bank.dto.ATMDetails;
import com.springboot.bank.dto.WrapperBankATM;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.ATM;
import com.springboot.bank.service.ATMService;

/**
 * @author Sumit
 *
 */

@RestController
public class ATMController {

	final Logger LOGGER = Logger.getLogger(ATMController.class);

	@Autowired
	ATMService atmService;

	@PostMapping(value = "/createATM")
	public ResponseEntity<ATM> createATM(@RequestBody WrapperBankATM wrapperBankATM) throws BankException {
		ATM atmData = null;
		try {
			atmData = atmService.createATM(wrapperBankATM);
		} catch (BankException e) {
			LOGGER.error(e.getMessage());
		}
		if (atmData == null)
			throw new BankException("atm details not added");
		else
			return new ResponseEntity<ATM>(atmData, HttpStatus.OK);
	}

	@PostMapping(value = "/addMoney/{atmId}/{bankId}/{moneyToBeAddedToATM}")
	public ResponseEntity<ATM> addMoneyFromBank(@PathVariable Long atmId, @PathVariable Long bankId,
			@PathVariable BigDecimal moneyToBeAddedToATM) throws BankException {
		ATM atmData = null;
		try {
			atmData = atmService.addMoneyFromBank(atmId, bankId, moneyToBeAddedToATM);
		} catch (BankException e) {
			LOGGER.error(e.getMessage());
		}
		if (atmData == null)
			throw new BankException("atm details not added");
		else
			return new ResponseEntity<ATM>(atmData, HttpStatus.OK);
	}

	@PostMapping(value = "/withdrawMoneyFromATM")
	public ResponseEntity<ATM> withdrawMoney(@RequestBody ATMDetails atmDetails) throws BankException {
		ATM atmData = null;
		try {
			atmData = atmService.withdrawMoney(atmDetails);
		} catch (BankException e) {
			LOGGER.error(e.getMessage());
		}
		if (atmData == null)
			throw new BankException("atm details not added");
		else
			return new ResponseEntity<ATM>(atmData, HttpStatus.OK);
	}

}
